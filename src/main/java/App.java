import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;

import java.io.IOException;


public class App {


    protected static HttpServer startServer() throws IOException {
        HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("grizzly2", "localhost", 3388);
        server.addListener(listener);

        // Initialize and add Spring-aware Jersey resource
        WebappContext ctx = new WebappContext("ctx", "/api");
        final ServletRegistration reg = ctx.addServlet("spring", new SpringServlet());
        reg.addMapping("/*");
        ctx.addContextInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.deploy(server);

        return server;

    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        // Add the StaticHttpHandler to serve static resources from the static1 folder
        httpServer.getServerConfiguration().addHttpHandler(
                new StaticHttpHandler("StaticHTML/"), "/test");

        httpServer.start();
        System.out.println("Server started, press enter to stop it.");
        System.in.read();

        httpServer.shutdownNow();

    }
}
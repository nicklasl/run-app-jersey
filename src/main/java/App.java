import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import com.sun.jersey.api.container.grizzly2.GrizzlyWebContainerFactory;

/**
 * Hello world!
 * 
 */
public class App {
	/**
	 * @param args
	 *            the command line arguments
	 */
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(8080).build();
	}

	public static final URI BASE_URI = getBaseURI();

	protected static HttpServer startServer() throws IOException {

		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages",
				"se.nldv.runapp");

		System.out.println("Starting grizzly...");

		return GrizzlyWebContainerFactory.create(BASE_URI, initParams);

	}

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startServer();
		File f = new File("StaticHTML");
		System.out.println(f.getAbsolutePath());
		httpServer.getServerConfiguration().addHttpHandler(
				new StaticHttpHandler(f.getAbsolutePath()), "/test");

		System.out
				.println(String
						.format("Jersey app started with WADL available at "
								+ "%sapplication.wadl\nTry out %smyresources/\nHit enter to stop it...",
								BASE_URI, BASE_URI));
		System.in.read();

		httpServer.stop();

	}
}
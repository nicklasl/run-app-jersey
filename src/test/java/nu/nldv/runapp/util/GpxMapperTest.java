package nu.nldv.runapp.util;

import junit.framework.Assert;
import nu.nldv.runapp.model.Track;
import nu.nldv.runapp.model.gpx.GpxType;
import nu.nldv.runapp.model.gpx.TrkType;
import nu.nldv.runapp.model.gpx.TrksegType;
import nu.nldv.runapp.model.gpx.WptType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class GpxMapperTest {

    @Autowired
    private GpxMapper mapper;

    private GpxType gpx;

    @Before
    public void constructGpx() throws DatatypeConfigurationException {
        gpx = new GpxType();
        TrkType track = new TrkType();
        TrksegType segment1 = new TrksegType();
        TrksegType segment2 = new TrksegType();
        segment1.getTrkpt().addAll(constructPoints(10));
        segment2.getTrkpt().addAll(constructPoints(5));
        track.getTrkseg().add(segment1);
        track.getTrkseg().add(segment2);
        gpx.getTrk().add(track);
        gpx.getTrk().add(track);
    }

    private List<WptType> constructPoints(int num) throws DatatypeConfigurationException {
        List<WptType> points = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            WptType p = new WptType();
            p.setLat(BigDecimal.TEN);
            p.setLon(BigDecimal.TEN);
            p.setEle(BigDecimal.ZERO);
            p.setTime(DatatypeFactory.newInstance().newXMLGregorianCalendar());
            points.add(p);
        }
        return points;
    }

    @Test
    public void testMapping() {
        List<Track> tracks = mapper.map(gpx);
        Assert.assertEquals(2, tracks.size());
        Assert.assertEquals(2, tracks.get(0).getSegments().size());
        Assert.assertEquals(10, tracks.get(0).getSegments().get(0).getPoints().size());
        Assert.assertEquals(5, tracks.get(0).getSegments().get(1).getPoints().size());

    }

}

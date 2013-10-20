package nu.nldv.runapp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Point {

    private String date;
    private double elevation;
    private Coordinates coordinates;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}

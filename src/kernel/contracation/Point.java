package kernel.contracation;

/**
 * Created by Crispher Gu on 8/16/2015.
 */
public class Point {
    public double x, y;
    public Point(double x, double y) {
        this.x = x; this.y = y;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

package kernel.contracation;

/**
 * Created by Crispher Gu on 8/16/2015.
 */
public class AffineContraction implements Contraction {
    public double a11, a12, a21, a22, b1, b2;
    public AffineContraction(double a11, double a12, double a21, double a22, double b1, double b2) {
        this.a11 = a11;
        this.a12 = a12;
        this.a21 = a21;
        this.a22 = a22;
        this.b1 = b1;
        this.b2 = b2;
    }
    public Point map(Point p) {
        double mapx = a11*p.x + a12*p.y + b1;
        return new Point(a11*p.x + a12*p.y + b1,
                         a21*p.x + a22*p.y + b2);
    }
    @Override
    public String toString(){
        return "{" + a11 + " ," + a12 + " / " + a21 + ", " + a22 + "}, " + '{' + b1 + ", " + b2 + "}\n";
    }
}

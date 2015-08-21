package kernel.complex;

/**
 * Created by Crispher Gu on 8/14/2015.
 */
public abstract class ComplexFunction {
    public String expr;
    public abstract Complex func(Complex c);
    public String getExpr() {
        return expr;
    }
}

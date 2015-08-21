package kernel.complex;

/**
 * Created by Crispher Gu on 8/14/2015.
 */
public class F1 extends ComplexFunction {
    Complex c;
    public F1(Complex z) {
        c = z;
        expr = "f(x) = x^2 + " + c.toString();
    }
    public Complex func(Complex z) {
        return z.power(2).add(c);
    }
}


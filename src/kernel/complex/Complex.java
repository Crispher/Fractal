package kernel.complex;

/**
 * Created by Crispher Gu on 8/14/2015.
 * basic arithmetic of complex numbers.
 */
public class Complex {
    public double Re, Im;
    public Complex(double re, double im) {
        Re = re;
        Im = im;
    }
    public Complex(Complex z) {
        Re = z.Re; Im = z.Im;
    }

    public Complex conjugate() {
        return new Complex(Re, -Im);
    }
    public double modulusSquare() {
        return Re*Re + Im*Im;
    }
    public Complex add(Complex b) {
        return new Complex(Re + b.Re, Im + b.Im);
    }
    public Complex subtract(Complex b) {
        return new Complex(Re - b.Re, Im - b.Im);
    }
    public Complex multiply(Complex b) {
        return new Complex(Re*b.Re - Im*b.Im, Re*b.Im + Im*b.Re);
    }
    public Complex divideByReal(double k) {             // todo, throw exception;
        return new Complex(Re/k, Im/k);
    }
    public Complex divideBy(Complex b) {
        return this.multiply(b.conjugate()).divideByReal(b.modulusSquare());
    }
    public Complex power(int n) {
        Complex ans = new Complex(1.0, 0);
        for (int i = 0; i < n; i++) {
            ans = ans.multiply(this);
        }
        return ans;
    }
    public String toString() {
        return new Double(Re).toString() + "+" + new Double(Im) + "i";
    }
}


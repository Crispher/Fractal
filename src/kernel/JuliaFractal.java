package kernel;

import kernel.complex.*;
import java.awt.*;
import static java.lang.Math.*;

/**
 * Created by Crispher Gu on 8/14/2015.
 * In this class rawData gives information of Fatou
 * sets, which can be used during rendering.
 * In fact the current renderFractal() function computes
 * the Julia set and do not provide customizable
 * rendering schemes. // todo;
 */

public class JuliaFractal extends Fractal {
    static double INFINITY = 1024;
    static int maxIterations = 2048;
    /**
     * fatou and julia should be real between 0 & 1;
     * fatou store the attractor;
     * julia stores the maximum difference between two pixels.
     * Attention: they have different dimensions!
     */
    private double[][] fatou;
    private double[][] julia;
    boolean Mandelbrot;
    ComplexFunction f;

    public JuliaFractal(ComplexFunction f) {
        this.f = f;
        Mandelbrot = f.expr.equals("Mandelbrot");
    }

    @Override
    public void renderRegion(double step) {
        width  = (int) ((maxX - minX) / step);
        height = (int) ((maxY - minY) / step);

        if (Mandelbrot) {
            renderMandelbrot(step);
            return;
        }

        fatou = new double[width + 1][height + 1];
        julia = new double[width][height];

        for (int w = 0; w < width + 1; w++) {
            for (int h = 0; h < height + 1; h++) {
                Complex z = new Complex(minX + w*step, minY + h*step);
                fatou[w][h] = escapeTime(f, z);
            }
        }
        double scaleMax = 0, scaleMin = INFINITY, fatouMax = 0;
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                double pmax, pmin;
                pmax = max(max(max(fatou[w][h], fatou[w+1][h]),
                        fatou[w][h+1]), fatou[w+1][h+1]);
                pmin = min(min(min(fatou[w][h], fatou[w+1][h]),
                        fatou[w][h+1]), fatou[w+1][h+1]);
                julia[w][h] = pmax - pmin;
                scaleMax = max(scaleMax, julia[w][h]);
                scaleMin = min(scaleMin, julia[w][h]);
                fatouMax = max(fatouMax, fatou[w][h]);
            }
        }
        double scale = scaleMax - scaleMin;
        if (scale == 0) {
            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    julia[w][h] = 0;
                    fatou[w][h] = 0;
                }
            }
        } else {
            normalize(julia, scale, scaleMin);
            normalize(fatou, fatouMax, 0);
        }
    }

    /**
     * This method returns Mandelbrot fractal.
     * Currently the "Escape time algorithm" is used.
     * In this constructor julia set is not initialized.
     */
    private void renderMandelbrot(double step) {
        fatou = new double[width][height];
        Complex c = new Complex(minX, minY);
        for (int w = 0; w < width; w++) {
            c.Im = minY;
            for (int h = 0; h < height; h++) {
                int iterations = 0;
                Complex z = new Complex(0, 0);
                while ((z = ((z.power(2)).add(c))).modulusSquare() < 16 &&
                        iterations < maxIterations) {
                    iterations++;
                }
                fatou[w][h] = 1 - (double)iterations / maxIterations;
                c.Im += step;
            }
            c.Re += step;
        }
        julia = fatou;
    }

    /**
     * Generate monochromatic image data, may
     * be modified, overloaded in future.
     * @return A array of gray level of rawData.
     */
    @Override
    public Color[][] renderFractal(pixelRenderer renderer) {
        Color[][] color = new Color[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                color[w][h] =  renderer.render(julia[w][h]);
            }
        }
        return color;
    }

    @Override
    double[][] getRawData() {
        return julia;
    }

    void setAutoTuning() { //todo;

    }

    private void normalize(double[][] a, double scale, double offset) {
        int m = a.length, n = a[0].length;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = (a[i][j] - offset) / scale;
            }
        }
    }

    private int escapeTime(ComplexFunction f, Complex z0) {
        int i = 0;
        Complex z = new Complex(z0);
        while (++i < maxIterations) {
            z = f.func(z);
            if (z.modulusSquare() > INFINITY*INFINITY) {
                return i;
            }
        }
        return i;
    }
}


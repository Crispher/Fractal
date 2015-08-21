package kernel;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * Created by Crispher Gu on 8/14/2015.
 */
abstract public class Fractal {
    protected double minX, minY, maxX, maxY;
    protected int width, height;
//    double[][] rawData;

    /**
     * get Julia set in JuliaFractal class;
     * get IFS attractor in IFS fractal.
     * @return
     */
    abstract double[][] getRawData();

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setRegion(double minX, double minY, double maxX, double maxY) {
        this.minX = minX; this.minY = minY;
        this.maxX = maxX; this.maxY = maxY;
    }

    abstract public void renderRegion(double step);

    public void writeToFile(String filename) {
        Charset charset = Charset.forName("US-ASCII");
        Path p = Paths.get(".\\" + filename);
        try {
            BufferedWriter writer = Files.newBufferedWriter(p, charset);
            double[][] image = getRawData();
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    writer.write(image[w][h] + (w == width - 1 ? "\n" : " "));
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }
    public abstract Color[][] renderFractal(pixelRenderer renderer);
}
package kernel;
import kernel.contracation.Contraction;
import kernel.contracation.Point;

import java.awt.*;
import java.util.Random;
import static java.lang.Math.*;
/**
 * Created by Crispher Gu on 8/14/2015.
 */
public class IFSFractal extends Fractal {
    int maxIterations = 100000;
    int dropBefore = 1024;
    double[][] attractor;
    Contraction[] IFS;
    int n;

    public IFSFractal(Contraction[] IFS) {
        this.IFS = IFS;
        n = IFS.length;
    }

    @Override
    public void renderRegion(double step) {
        width  = (int) ((maxX - minX) / step);
        height = (int) ((maxY - minY) / step);
        attractor = new double[width][height];
        Point p = new Point(random(), random());
        Random randInt = new Random();
        for (int i = 0; i < dropBefore; i++) {
            int rand = randInt.nextInt(n);
            p = IFS[rand].map(p);
        }
        for (int i = 0; i < maxIterations; i++) {
            p = IFS[randInt.nextInt(n)].map(p);
            if (minX <= p.x && p.x < maxX && minY <= p.y && p.y < maxY) {
                attractor[(int)((p.x - minX) / step)][(int)((p.y - minY) / step)] += 1;
            }
        }
        double scale = 0;
        for(int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                scale = max(scale, attractor[w][h]);
            }
        }
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                attractor[w][h] = 1 - attractor[w][h] / scale;
            }
        }
    }

    @Override
    double[][] getRawData() {
        return new double[0][];
    }

    @Override
    public Color[][] renderFractal(pixelRenderer renderer) {
        Color[][] image = new Color[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                image[w][h] = renderer.render(attractor[w][h]);
            }
        }
        return image;
    }
}


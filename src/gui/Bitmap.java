package gui;

import kernel.Fractal;
import kernel.pixelRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.min;
/**
 * Created by Crispher Gu on 8/17/2015.
 */
public class Bitmap extends JPanel{
    int width, height;
    Fractal f;
    Color[][] color;
    pixelRenderer renderer;
    volatile Color[][] cacheImage; //todo;

    public Bitmap(final int width, int height, Fractal f, pixelRenderer renderer) {
        this.width = width;
        this.height = height;
        this.f = f; this.renderer = renderer;
        f.setRegion(-1., -1., 1., 1.);
        f.renderRegion(2. / width);
        color = f.renderFractal(renderer);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = getMousePosition();
                System.err.format("get x, y %f, %f\n", p.getX(), p.getY());
                zoomInAtMouse(p.getX(), p.getY());
            }
        });
    }

    @Override
    /**
     * a test version. should be altered to support off set
     * called only after color[][] is ready!
     *  todo;
     */
    protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        if (w < width || h < height) {
            System.err.print("Windows size is too small.\n");
        }
        w = min(width, w);
        h = min(height, h);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g.setColor(color[i][j]);
                g.drawLine(i, h - 1 - j, i, h - 1 - j); // draws a point
            }
        }
    }

    public void zoomIn(double centerX, double centerY) {
        System.err.format("zoom at %f, %f\n", centerX, centerY);
        f.setRegion(centerX - (centerX - f.getMinX()) / 2, centerY - (centerY - f.getMinY()) / 2,
                centerX + (f.getMaxX() - centerX) / 2, centerY + (f.getMaxY() - centerY) / 2);
        f.renderRegion((f.getMaxX() - f.getMinX()) / width);
        color = f.renderFractal(renderer);
        repaint();
    }

    public void zoomOut(double centerX, double centerY) {
        f.setRegion(centerX - (centerX - f.getMinX()) * 2, centerY - (centerY - f.getMinY()) * 2,
                centerX + (f.getMaxX() - centerX) * 2, centerY + (f.getMaxY() - centerY) * 2);
        f.renderRegion((f.getMaxX() - f.getMinX()) / width);
        color = f.renderFractal(renderer);
        repaint();
    }

    public void zoomInAtMouse(double mousePosX, double mousePosY) {
        zoomIn(mousePosX / width * (f.getMaxX() - f.getMinX()) + f.getMinX(),
                (height - mousePosY) / width * (f.getMaxY() - f.getMinY()) + f.getMinY());
    }

    public void zoomOutAtMouse(double mousePosX, double mousePosY) {
        zoomOut(mousePosX / width * (f.getMaxX() - f.getMinX()) + f.getMinX(),
                (height - mousePosY) / width * (f.getMaxY() - f.getMinY()) + f.getMinY());
    }

    public void translate(double shiftX, double shiftY) {
        f.setRegion(f.getMinX() + shiftX, f.getMinY() + shiftY, f.getMaxX() + shiftX, f.getMaxY() + shiftY);
        f.renderRegion((f.getMaxX() - f.getMinX()) / width);
        color = f.renderFractal(renderer);
        repaint();
    }

    public void setRenderer(pixelRenderer renderer) {
        this.renderer = renderer;
        color = f.renderFractal(renderer);
        repaint();
    }
}

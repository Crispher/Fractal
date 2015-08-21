package gui;
import kernel.pixelRenderer;
import kernel.Fractal;
import kernel.JuliaFractal;
import kernel.complex.*;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

/**
 * Created by Crispher Gu on 8/17/2015.
 */
public class MainInterface extends JFrame {
    Bitmap im;

    public static void main(String[] args) {
        MainInterface MI = new MainInterface();
    }

    public MainInterface() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 700);
        Container cp = getContentPane();
        Fractal f = new JuliaFractal(new F1(new Complex(-0.1, 0.651)));
//System.err.format("image: %d, %d\n", image.length, image[0].length);

//        Contraction[] Fern = {
//                new AffineContraction(0, 0, 0, 0.16, 0, 0),
//                new AffineContraction(0.85, 0.04, -0.04, 0.85, 0, 1.60),
//                new AffineContraction(0.20, -0.26, 0.23, 0.22, 0, 1.60),
//                new AffineContraction(-0.15, 0.28, 0.26, 0.24, 0, 0.44)
//        };
//        Contraction[] vonKock = {
//                new AffineContraction(1.0/3, 0, 0, 1.0/3, 0, 0),
//                new AffineContraction(1.0/3, 0, 0, 1.0/3, 2.0/3, 0),
//                new AffineContraction(cos(PI/3)/3, -sin(PI/3)/3, sin(PI/3)/3, cos(PI/3)/3, 1.0/3, 0),
//                new AffineContraction(cos(PI/3)/3, sin(PI/3)/3, -sin(PI/3)/3, cos(PI/3)/3, 1.0/2, sqrt(3)/6),
//        };
//
//        Fractal f = new IFSFractal(Fern, -5, 0, 5, 10, 0.01);
//        Fractal f = new IFSFractal(vonKock, 0, -0.1, 1, 0.5, 0.001);
        pixelRenderer renderer = new pixelRenderer(.7f, 1f, .8f, .5f, 1f, 1f, 14);
        im = new Bitmap(650, 650, f, renderer);
        cp.add(im);
        setVisible(true);
    }
}

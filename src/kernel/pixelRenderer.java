package kernel;

import java.awt.*;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Created by Crispher Gu on 8/20/2015.
 */
public class pixelRenderer {
    float hue0, saturation0, lumination0;
    float hue1, saturation1, lumination1;
    double intensifier = 1;

    public pixelRenderer(float hue0, float saturation0, float lumination0, float hue1, float saturation1, float lumination1, double intensifier) {
        this.hue0 = hue0;
        this.saturation0 = saturation0;
        this.lumination0 = lumination0;
        this.hue1 = hue1;
        this.saturation1 = saturation1;
        this.lumination1 = lumination1;
        this.intensifier = intensifier;
    }

    /**
     * Get the color via linear interpolation.
     * @param data
     * @return the color of a given point according to
     */
    public Color render(double data) {
        if (intensifier > 0) {
            data = (float) (pow(data, 1. / intensifier));
        } else {
            data = 1 / (1 - exp(intensifier * (data - .5)));
        }
        double hue = hue0 + data * (hue1 - hue0),
                saturation = saturation0 + data * (saturation1 - saturation0),
                lumination = lumination0 + data * (lumination1 - lumination0);
        return Color.getHSBColor((float) hue, (float) saturation, (float) lumination);
    }
}

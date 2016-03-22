package process.model;

import java.awt.Color;

public class PixelModel {
    private int x;
    private int y;
    private int red;
    private int green;
    private int blue;
    private int rgb;

    public PixelModel(int x, int y, int rgb){
        Color color = new Color(rgb);
        setX(x);
        setY(y);
        setRgb(rgb);
        setRed(color.getRed());
        setGreen(color.getGreen());
        setBlue(color.getBlue());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}

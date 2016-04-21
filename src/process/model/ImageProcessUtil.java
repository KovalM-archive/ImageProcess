package process.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageProcessUtil implements ImageConstants {
    /**
     * Create copy of input image
     * @param inputImage input image
     * @return output image
     */
    public BufferedImage createImageCopy(BufferedImage inputImage){
        BufferedImage outputImage = null;
        if (inputImage != null){
            outputImage = new BufferedImage(inputImage.getWidth(),
                    inputImage.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < inputImage.getWidth(); i++) {
                for (int j = 0; j < inputImage.getHeight(); j++) {
                    outputImage.setRGB(i, j, inputImage.getRGB(i, j));
                }
            }
        }
        return outputImage;
    }

    /**
     * remove color from input image
     * @param inputImage input image
     * @param colorType type of color
     * @return output image
     */
    public BufferedImage removeColor(BufferedImage inputImage, int colorType){
        BufferedImage outputImage = null;
        if (inputImage != null){
            Color currentPixel;
            int r, g, b;
            outputImage = new BufferedImage(inputImage.getWidth(),
                    inputImage.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < outputImage.getWidth(); i++) {
                for (int j = 0; j < outputImage.getHeight(); j++) {
                    currentPixel = new Color(inputImage.getRGB(i, j));
                    r = (colorType == RED)?0:currentPixel.getRed();
                    g = (colorType == GREEN)?0:currentPixel.getGreen();
                    b = (colorType == BLUE)?0:currentPixel.getBlue();
                    outputImage.setRGB(i, j, new Color(r, g, b).getRGB());
                }
            }
        }
        return outputImage;
    }

    /**
     * create image that is rectangle of inputImage
     * @param pInputImage input image
     * @param pStartX x coordinate of start point
     * @param pStartY y coordinate of start point
     * @param pFinishX x coordinate of finish point
     * @param pFinishY y coordinate of finish point
     * @return output image
     */
    public BufferedImage createRectangleOfImage(BufferedImage pInputImage, int pStartX, int pStartY,
                                             int pFinishX, int pFinishY){
        BufferedImage outputImage = null;
        if (pInputImage != null) {
            if (pStartX >= 0 && pStartY >= 0 && pFinishX <= pInputImage.getWidth() && pFinishY <= pInputImage.getHeight()){
                outputImage = new BufferedImage(pFinishX - pStartX, pFinishY - pStartY, BufferedImage.TYPE_INT_ARGB);
                int x, y;
                x = 0;
                for (int i = pStartX; i < pFinishX; i++) {
                    y = 0;
                    for (int j = pStartY; j < pFinishY; j++) {
                        outputImage.setRGB(x, y, pInputImage.getRGB(i, j));
                        y++;
                    }
                    x++;
                }
            }
        }
        return outputImage;
    }

    /**
     * overlay two images with shift
     * @param pLeftImage left image
     * @param pRightImage right image
     * @param pShift shift between images
     * @return output image
     */
    public BufferedImage overlayImages(BufferedImage pLeftImage, BufferedImage pRightImage, int pShift){
        BufferedImage outputImage = null;
        if (pLeftImage != null && pRightImage != null){
            outputImage = new BufferedImage(pLeftImage.getWidth() + pShift,
                    pLeftImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Color leftPixel, rightPixel;
            int r, g, b;

            for (int i = 0; i < outputImage.getWidth(); i++) {
                for (int j = 0; j < outputImage.getHeight(); j++) {
                    if (i < pShift){
                        rightPixel = new Color(pRightImage.getRGB(i, j));
                        outputImage.setRGB(i, j, rightPixel.getRGB());
                    } else if (i >= pRightImage.getWidth()){
                        leftPixel = new Color(pLeftImage.getRGB(i - pShift, j));
                        outputImage.setRGB(i, j, leftPixel.getRGB());
                    } else{
                        rightPixel = new Color(pRightImage.getRGB(i, j));
                        leftPixel = new Color(pLeftImage.getRGB(i- pShift, j));
                        r = leftPixel.getRed();
                        g = rightPixel.getGreen();
                        b = rightPixel.getBlue();
                        outputImage.setRGB(i, j, new Color(r, g, b).getRGB());
                    }
                }
            }
        }
        return outputImage;
    }

    /**
     * Create scalability image
     * @param inputImage input image
     * @param scalability scalability
     * @return scalability image
     */
    public BufferedImage createScalabilityImage(BufferedImage inputImage, double scalability){
        int width = (int)(inputImage.getWidth()*scalability);
        int height = (int)(inputImage.getHeight()*scalability);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int x, y;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                x = (int)(i/scalability);
                y = (int)(j/scalability);
                outputImage.setRGB(i, j, inputImage.getRGB(x, y));
            }
        }
        return outputImage;
    }

    /**
     * Create rotation image
     * @param inputImage input image
     * @param angle angle
     * @return scalability image
     */
    public BufferedImage createRotationImage(BufferedImage inputImage, double angle){
        int width = (int)(Math.sqrt(Math.pow(inputImage.getWidth(), 2) + Math.pow(inputImage.getHeight(), 2)));
        int height = width;
        int gap = (width - inputImage.getWidth())/2;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int x0, y0, x1, y1, x, y, xx, yy;
        Color whiteColor = new Color(255,255,255);

        angle = Math.toRadians(angle);
        x0 = inputImage.getWidth()/2;
        y0 = inputImage.getHeight()/2;
        x1 = width/2;
        y1 = height/2;
        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                x = i - x1;
                y = j - y1;
                xx = (int) Math.round(x * Math.cos(angle) + y * Math.sin(angle)) + x0;
                yy = (int) Math.round(y * Math.cos(angle) - x * Math.sin(angle)) + y0;
                if (xx>0 && yy>0 && xx<inputImage.getWidth() && yy<inputImage.getHeight()){
                    outputImage.setRGB(i, j, inputImage.getRGB(xx, yy));
                } else {
                    outputImage.setRGB(i, j, whiteColor.getRGB());
                }
            }
        }

        return outputImage;
    }
}

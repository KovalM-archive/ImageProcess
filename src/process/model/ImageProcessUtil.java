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
}

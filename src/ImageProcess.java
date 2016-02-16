import filter.FilterFactory;
import filter.IFilterFactory;
import filter.IImageFilter;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public ImageProcess(String imagePath){
        File file1 = new File(imagePath);
        File file2 = new File("source/image/input/input2.jpg");
        BufferedImage inputImage1, inputImage2;
        IFilterFactory filterFactory = new FilterFactory();

        IImageFilter filter1 = filterFactory.createFilter("source/filters/filter1.txt");
        IImageFilter filter2 = filterFactory.createFilter("source/filters/filter2.txt");
        IImageFilter filter3 = filterFactory.createFilter("source/filters/filter3.txt");

        try {
            inputImage1 = ImageIO.read(file1);
            inputImage2 = ImageIO.read(file2);
//            writeImage("source/image/output/output1_filter1.png", filter1.applyFilter(inputImage1));
//            writeImage("source/image/output/output1_filter2.png", filter2.applyFilter(inputImage1));
//            writeImage("source/image/output/output1_filter3.png", filter3.applyFilter(inputImage1));
            writeImage("source/image/output/anaglyph.png", createAnaglyph(inputImage2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeImage(String outputPath, BufferedImage outputImage){
        try {
            ImageIO.write(outputImage, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage createAnaglyph(BufferedImage inputImage){
        BufferedImage outputImage;
        BufferedImage leftImage = getRectangleOfImage(inputImage, 0, 0,
                inputImage.getWidth()/2, inputImage.getHeight());
        BufferedImage rightImage = getRectangleOfImage(inputImage, inputImage.getWidth()/2, 0,
                inputImage.getWidth(), inputImage.getHeight());
        outputImage = overlayImages(leftImage, rightImage);
        return outputImage;
    }

    public BufferedImage getRectangleOfImage(BufferedImage inputImage, int startX, int startY,
                                             int finishX, int finishY){
        BufferedImage outputImage = null;
        if (startX>=0 && startY>=0 && finishX<=inputImage.getWidth() && finishY<=inputImage.getHeight()){
            outputImage = new BufferedImage(finishX-startX, finishY-startY, BufferedImage.TYPE_INT_ARGB);
            int x, y;
            x=0;
            for (int i = startX; i < finishX; i++) {
                y=0;
                for (int j = startY; j < finishY; j++) {
                    outputImage.setRGB(x, y, inputImage.getRGB(i, j));
                    y++;
                }
                x++;
            }
        }

        return outputImage;
    }

    public BufferedImage overlayImages(BufferedImage leftImage, BufferedImage rightImage){
        BufferedImage outputImage = new BufferedImage(leftImage.getWidth() + ImageConstants.SHIFT,
                leftImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Color leftPixel, rightPixel;
        int r, g, b;

        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                if (i < ImageConstants.SHIFT){
                    rightPixel = new Color(rightImage.getRGB(i, j));
                    outputImage.setRGB(i, j, rightPixel.getRGB());
                } else if (i >= rightImage.getWidth()){
                    leftPixel = new Color(leftImage.getRGB(i - ImageConstants.SHIFT, j));
                    outputImage.setRGB(i, j, leftPixel.getRGB());
                } else{
                    rightPixel = new Color(rightImage.getRGB(i, j));
                    leftPixel = new Color(leftImage.getRGB(i-ImageConstants.SHIFT, j));
                    r = leftPixel.getRed();
                    g = rightPixel.getGreen();
                    b = rightPixel.getBlue();
                    outputImage.setRGB(i, j, new Color(r, g, b).getRGB());
                }
            }
        }
        return outputImage;
    }

    public BufferedImage removeColor(BufferedImage inputImage, String colorType){
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Color currentPixel;
        int r, g, b;

        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                currentPixel = new Color(inputImage.getRGB(i, j));
                r = colorType.equals(ImageConstants.RED)?0:currentPixel.getRed();
                g = colorType.equals(ImageConstants.GREEN)?0:currentPixel.getGreen();
                b = colorType.equals(ImageConstants.BLUE)?0:currentPixel.getBlue();
                outputImage.setRGB(i, j, new Color(r, g, b).getRGB());
            }
        }

        return outputImage;
    }

    public void printMatrix(float [][] matrix, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

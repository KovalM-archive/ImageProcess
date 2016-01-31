import filter.FilterFactory;
import filter.IFilterFactory;
import filter.IImageFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public ImageProcess(String imagePath){
        File f = new File(imagePath);
        BufferedImage inputImage;
        IFilterFactory filterFactory = new FilterFactory();

        IImageFilter filter1 = filterFactory.createFilter("source/filters/filter1.txt");
        IImageFilter filter2 = filterFactory.createFilter("source/filters/filter2.txt");
        IImageFilter filter3 = filterFactory.createFilter("source/filters/filter3.txt");

        try {
            inputImage = ImageIO.read(f);
            writeImage("source/image/output/output1_filter1.png", filter1.applyFilter(inputImage));
            writeImage("source/image/output/output1_filter2.png", filter2.applyFilter(inputImage));
            writeImage("source/image/output/output1_filter3.png", filter3.applyFilter(inputImage));

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

    public void printMatrix(float [][] matrix, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

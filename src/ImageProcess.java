import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public ImageProcess(String imagePath){
        File f = new File(imagePath);
        BufferedImage inputImage;

        ImageFilter filter1 = FilterFactory.createFilter("source/filters/filter1.txt");
        ImageFilter filter2 = FilterFactory.createFilter("source/filters/filter2.txt");
        ImageFilter filter3 = FilterFactory.createFilter("source/filters/filter3.txt");

        filter2.setDivider(1);
        try {
            inputImage = ImageIO.read(f);
            createImageByFilter("source/image/output/output1_filter1.png", inputImage, filter1);
            createImageByFilter("source/image/output/output1_filter2.png", inputImage, filter2);
            createImageByFilter("source/image/output/output1_filter3.png", inputImage, filter3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createImageByFilter(String outputPath, BufferedImage inputImage, ImageFilter filter){
        BufferedImage outputImage = filter.applyFilter(inputImage);
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

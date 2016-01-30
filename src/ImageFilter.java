import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageFilter implements IImageFilter {
    private int [][] matrixFilter;
    private int matrixSize;
    private int divider;

    public ImageFilter(int [][] matrixFilter, int matrixSize){
        setMatrixSize(matrixSize);
        setMatrixFilter(matrixFilter);
        setDivider(matrixSize*matrixSize);
    }

    @Override
    public int processPixel(int i, int j, BufferedImage inputImage) {
        int x, y;
        int answer = 0;
        int row, column, rgb;
        int rSum, gSum, bSum;
        rSum=gSum=bSum=0;
        Color c;

        row = 0;
        x = i - getMatrixSize()/2;
        while (x <=  i + getMatrixSize()/2){
            y = j - getMatrixSize()/2;
            column = 0;
            while (y <= j + getMatrixSize()/2){
                if (inBorder(x, y, inputImage.getWidth(), inputImage.getHeight())){
                    c = new Color(inputImage.getRGB(x, y));
                    rSum += c.getRed()*matrixFilter[row][column];
                    gSum +=c.getGreen()*matrixFilter[row][column];
                    bSum += c.getBlue()*matrixFilter[row][column];
                }
                y++;
                column++;
            }
            x++;
            row++;
        }
        rSum /= getDivider(); gSum /= getDivider(); bSum /= getDivider();
        rSum = clumb(rSum); gSum = clumb(gSum); bSum = clumb(bSum);

        answer = new Color(rSum, gSum, bSum).getRGB();
        return answer;
    }

    @Override
    public BufferedImage applyFilter(BufferedImage inputImage) {
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < inputImage.getWidth(); i++) {
            for (int j = 0; j < inputImage.getHeight(); j++) {
                outputImage.setRGB(i, j, processPixel(i, j, inputImage));
            }
        }
        return outputImage;
    }


    public int clumb(int x){
        if (x<0){
            return 0;
        } else if (x>255){
            return 255;
        } else {
            return x;
        }
    }
    private boolean inBorder(int x, int y, int width, int height){
        return (x>=0 && x<width && y>=0 && y<height);
    }
    public int[][] getMatrixFilter() {
        return matrixFilter;
    }

    public void setMatrixFilter(int[][] matrixFilter) {
        this.matrixFilter = matrixFilter;
    }

    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        if (divider >0){
            this.divider = divider;
        }
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }
}

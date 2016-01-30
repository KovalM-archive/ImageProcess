import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilterFactory {
    public static ImageFilter createFilter(String filePath){
        File f = new File(filePath);
        ImageFilter imageFilter = null;
        try {
            Scanner in = new Scanner(f);
            int matrixSize = Integer.parseInt(in.nextLine());
            int [][] matrixFilter = new int[matrixSize][matrixSize];
            int i, j;
            String line;
            i=0;
            while (in.hasNext()){
                line = in.nextLine();
                j=0;
                while (line.length() > 0){
                    if (!line.contains(" ")){
                        matrixFilter[i][j] = Integer.parseInt(line);
                        break;
                    } else {
                        matrixFilter[i][j] = Integer.parseInt(line.substring(0, line.indexOf(32)));
                    }
                    line = line.substring(line.indexOf(32)+1);
                    j++;
                }
                i++;
            }
            imageFilter = new ImageFilter(matrixFilter, matrixSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageFilter;
    }
}

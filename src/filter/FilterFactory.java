package filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilterFactory implements IFilterFactory {
    public IImageFilter createFilter(String filePath){
        File f = new File(filePath);
        IImageFilter imageFilter = null;
        try {
            Scanner in = new Scanner(f);
            String line;
            int matrixSize, divider, i, j;

            line = in.nextLine();
            matrixSize = getFirstInt(line);
            line = line.substring(line.indexOf(32)+1);
            divider = getFirstInt(line);

            if (matrixSize%2==0) {
                matrixSize++;
            }

            int [][] matrixFilter = new int[matrixSize][matrixSize];
            i=0;
            while (in.hasNext()){
                line = in.nextLine();
                j=0;
                while (line.length() > 0){
                    matrixFilter[i][j] = getFirstInt(line);
                    if (!line.contains(" ")){
                        break;
                    }
                    line = line.substring(line.indexOf(32)+1);
                    j++;
                }
                i++;
            }
            imageFilter = new ImageFilter(matrixFilter, matrixSize, divider);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageFilter;
    }

    private int getFirstInt(String line){
        if (!line.contains(" ")){
            return  Integer.parseInt(line);
        } else {
            return Integer.parseInt(line.substring(0, line.indexOf(32)));
        }
    }
}

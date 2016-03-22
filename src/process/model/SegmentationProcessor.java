package process.model;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Queue;

public class SegmentationProcessor {
    private int[][] distancePixel;
    private int width, height, distance;
    private BufferedImage outputImage;

    private ImageProcessUtil imageProcessUtil = new ImageProcessUtil();

    public BufferedImage createSegmentation(BufferedImage inputImage, int distance){
        this.distance = distance;
        outputImage = null;
        if (inputImage != null){
            outputImage = getImageProcessUtil().createImageCopy(inputImage);
            Queue<PixelModel> queuePixels = new ArrayDeque<>();
            int x, y;

            distancePixel = new int[outputImage.getWidth()][outputImage.getWidth()];
            width = outputImage.getWidth();
            height = outputImage.getHeight();
            PixelModel currentPixel, standardPixel;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    distancePixel[i][j] = 256;
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (distancePixel[i][j]>255){
                        standardPixel = new PixelModel(i, j, outputImage.getRGB(i, j));
                        queuePixels.add(standardPixel);
                        while (queuePixels.size() > 0){
                            currentPixel = queuePixels.poll();
                            x = currentPixel.getX();
                            y = currentPixel.getY();
                            checkNeighbor(x + 1, y, standardPixel, queuePixels);
                            checkNeighbor(x, y+1, standardPixel, queuePixels);
                            checkNeighbor(x-1, y, standardPixel, queuePixels);
                            checkNeighbor(x, y-1, standardPixel, queuePixels);
                            queuePixels.remove(currentPixel);
                        }
                    }
                }
            }
        }
        return outputImage;
    }

    private void checkNeighbor(int x, int y, PixelModel standardPixel, Queue<PixelModel> queuePixels){
        if (inRectangle(x, y, width, height)){
            PixelModel neighborPixel = new PixelModel(x, y, outputImage.getRGB(x, y));
            int dist = pixelDistance(standardPixel, neighborPixel, distance);
            if (dist < 255  && dist < distancePixel[x][y]){
                distancePixel[x][y] = dist;
                outputImage.setRGB(x, y, standardPixel.getRgb());
                neighborPixel.setRgb(standardPixel.getRgb());
                queuePixels.add(neighborPixel);
            }
        }
    }

    private int pixelDistance(PixelModel standardPixel, PixelModel currentPixel, int distance){
        int r1 = standardPixel.getRed(), g1 = standardPixel.getGreen(), b1 = standardPixel.getBlue();
        int r2 = currentPixel.getRed(), g2 = currentPixel.getGreen(), b2 = currentPixel.getBlue();
        int d1 = Math.max(Math.abs(r1-r2), Math.max(Math.abs(g1-g2), Math.abs(b1-b2)));
        return d1<=distance?d1:256;
    }

    private int max(int a, int b){
        return a>b?a:b;
    }
    private boolean inRectangle(int x,int y, int width, int height){
        return x >= 0 && x < width && y >= 0 && y<height;
    }

    public ImageProcessUtil getImageProcessUtil() {
        return imageProcessUtil;
    }
}

package process.model;

import process.model.filter.FilterFactory;
import process.model.filter.IFilterFactory;
import process.model.filter.IImageFilter;

import java.awt.image.BufferedImage;

public class ImageProcess {
    private SegmentationProcessor segmentationProcessor;
    private ImageProcessUtil processUtil;
    public ImageProcess(){
        segmentationProcessor = new SegmentationProcessor();
        processUtil = new ImageProcessUtil();
    }

    public BufferedImage createSegmentation(BufferedImage inputImage, int distance){
        return getSegmentationProcessor().createSegmentation(inputImage, distance);
    }

    public BufferedImage createRotation(BufferedImage inputImage, double distance){
        return getProcessUtil().createRotationImage(inputImage, distance);
    }

    public BufferedImage createScalabilityImage(BufferedImage inputImage, double scalability){
        return getProcessUtil().createScalabilityImage(inputImage, scalability);
    }

    public BufferedImage filterImage(BufferedImage inputImage){
        IFilterFactory filterFactory = new FilterFactory();
        IImageFilter filter = filterFactory.createFilter("source/filters/filter1.txt");
        return filter.applyFilter(inputImage);
    }

    public BufferedImage createAnaglyph(BufferedImage inputImage){
        BufferedImage outputImage;
        BufferedImage leftImage = getProcessUtil().createRectangleOfImage(inputImage, 0, 0,
                inputImage.getWidth() / 2, inputImage.getHeight());
        BufferedImage rightImage = getProcessUtil().createRectangleOfImage(inputImage, inputImage.getWidth() / 2, 0,
                inputImage.getWidth(), inputImage.getHeight());
        outputImage = getProcessUtil().overlayImages(leftImage, rightImage, 10);
        return outputImage;
    }


    public SegmentationProcessor getSegmentationProcessor() {
        return segmentationProcessor;
    }

    public void setSegmentationProcessor(SegmentationProcessor segmentationProcessor) {
        this.segmentationProcessor = segmentationProcessor;
    }

    public ImageProcessUtil getProcessUtil() {
        return processUtil;
    }

    public void setProcessUtil(ImageProcessUtil processUtil) {
        this.processUtil = processUtil;
    }
}

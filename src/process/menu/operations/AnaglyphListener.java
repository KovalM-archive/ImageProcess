package process.menu.operations;

import process.ImageProcessController;
import process.model.ImageProcess;
import process.view.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AnaglyphListener implements ActionListener {
    private ImageProcessController processController;

    public AnaglyphListener(ImageProcessController processController){
        this.processController = processController;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (getProcessController()!=null){
            ImagePanel imagePanel = getProcessController().getImagePanel();
            ImageProcess imageProcess = getProcessController().getImageProcess();
            if (imagePanel != null && imageProcess != null){
                BufferedImage sourceImage = imagePanel.getSourceImage();
                BufferedImage resultImage = imageProcess.createAnaglyph(sourceImage);
                imagePanel.setResultImage(resultImage);
            }
        }
    }

    public ImageProcessController getProcessController() {
        return processController;
    }

    public void setProcessController(ImageProcessController processController) {
        this.processController = processController;
    }
}

package process.menu.operations;

import process.ImageProcessController;
import process.model.ImageProcess;
import process.view.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FilterListener implements ActionListener {
    private ImageProcessController processController;

    public FilterListener(ImageProcessController processController){
        this.processController = processController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getProcessController()!=null){
            ImagePanel imagePanel = getProcessController().getImagePanel();
            ImageProcess imageProcess = getProcessController().getImageProcess();
            if (imagePanel != null && imageProcess != null){
                BufferedImage sourceImage = imagePanel.getSourceImage();
                BufferedImage resultImage = imageProcess.filterImage(sourceImage);
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

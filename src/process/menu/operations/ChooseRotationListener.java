package process.menu.operations;

import process.ImageProcessController;
import process.model.ImageProcess;
import process.view.ImagePanel;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

public class ChooseRotationListener implements ChangeListener{
    private ImageProcessController processController;
    private JSlider slider;

    public ChooseRotationListener(ImageProcessController processController,
                                  JSlider slider){
        setProcessController(processController);
        setSlider(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        double angle = getSlider().getValue();
        if (getProcessController() != null){
            ImagePanel imagePanel = getProcessController().getImagePanel();
            ImageProcess imageProcess = getProcessController().getImageProcess();
            if (imagePanel != null && imageProcess != null){
                BufferedImage sourceImage = imagePanel.getReserveSourceImage();
                BufferedImage resultImage = imageProcess.createRotation(sourceImage, angle);
                imagePanel.setSourceImage(resultImage);
            }
        }
    }

    public ImageProcessController getProcessController() {
        return processController;
    }

    public void setProcessController(ImageProcessController processController) {
        this.processController = processController;
    }

    public JSlider getSlider() {
        return slider;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }
}

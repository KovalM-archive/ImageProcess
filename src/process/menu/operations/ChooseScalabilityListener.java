package process.menu.operations;

import process.ImageProcessController;
import process.model.ImageProcess;
import process.view.ImagePanel;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

public class ChooseScalabilityListener implements ChangeListener {
    private ImageProcessController processController;
    private JSlider slider;

    public ChooseScalabilityListener(ImageProcessController processController,
                                     JSlider slider){
        setProcessController(processController);
        setSlider(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        double scalability = getSlider().getValue()/100.0;
        scalability=scalability>0?scalability:0.01;
        if (getProcessController() != null){
            ImagePanel imagePanel = getProcessController().getImagePanel();
            ImageProcess imageProcess = getProcessController().getImageProcess();
            if (imagePanel != null && imageProcess != null){
                BufferedImage sourceImage = imagePanel.getReserveSourceImage();
                BufferedImage resultImage = imageProcess.createScalabilityImage(sourceImage, scalability);
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

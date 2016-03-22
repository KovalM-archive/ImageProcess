package process.menu.operations;

import process.ImageProcessController;
import process.model.ImageProcess;
import process.view.ImagePanel;

import javax.swing.JDialog;
import javax.swing.JSlider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ChooseSegmetationListener implements ActionListener {
    private ImageProcessController processController;
    private JSlider slider;
    private JDialog dialog;

    public ChooseSegmetationListener(ImageProcessController processController, JSlider slider, JDialog dialog){
        setProcessController(processController);
        setSlider(slider);
        setDialog(dialog);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int distance = getSlider().getValue();
        getDialog().setVisible(false);
        if (getProcessController()!=null){
            ImagePanel imagePanel = getProcessController().getImagePanel();
            ImageProcess imageProcess = getProcessController().getImageProcess();
            if (imagePanel != null && imageProcess != null){
                BufferedImage sourceImage = imagePanel.getSourceImage();
                BufferedImage resultImage = imageProcess.createSegmentation(sourceImage, distance);
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

    public JSlider getSlider() {
        return slider;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }

    public JDialog getDialog() {
        return dialog;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }
}

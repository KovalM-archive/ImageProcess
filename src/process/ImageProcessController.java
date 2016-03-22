package process;

import process.model.ImageProcess;
import process.view.ImagePanel;

public class ImageProcessController {
    private ImagePanel imagePanel;
    private ImageProcess imageProcess;

    public ImageProcessController(ImageProcess imageProcess, ImagePanel imagePanel){
        setImageProcess(imageProcess);
        setImagePanel(imagePanel);
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public ImageProcess getImageProcess() {
        return imageProcess;
    }

    public void setImageProcess(ImageProcess imageProcess) {
        this.imageProcess = imageProcess;
    }
}

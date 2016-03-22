package process.menu;

import process.ImageProcessController;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpenMenuListener implements ActionListener {
    private ImageProcessController processController;
    private String lastDirectory;

    public OpenMenuListener(ImageProcessController processController){
        this.processController = processController;
        this.lastDirectory = System.getProperty("user.dir");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(lastDirectory);
        chooser.showOpenDialog(null);
        if (chooser.isFileSelectionEnabled()) {
            try {
                lastDirectory = chooser.getSelectedFile().getPath();
                BufferedImage sourceImage = ImageIO.read(new File(lastDirectory));
                getProcessController().getImagePanel().setSourceImage(sourceImage);
            } catch (IOException e1) {
                e1.printStackTrace();
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

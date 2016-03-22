package process.menu;

import process.ImageProcessController;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SaveMenuListener implements ActionListener {
    private ImageProcessController processController;
    private String lastDirectory;

    public SaveMenuListener(ImageProcessController processController) {
        this.processController = processController;
        this.lastDirectory = System.getProperty("user.dir");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        if (chooser.isFileSelectionEnabled()){
            try {
                lastDirectory = chooser.getSelectedFile().getPath();
                ImageIO.write(processController.getImagePanel().getResultImage(), "png", new File(lastDirectory));
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

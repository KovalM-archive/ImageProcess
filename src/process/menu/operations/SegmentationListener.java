package process.menu.operations;

import process.ImageProcessController;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SegmentationListener implements ActionListener {
    private ImageProcessController processController;
    private JFrame mainFrame;

    public SegmentationListener(ImageProcessController processController, JFrame mainFrame){
        setProcessController(processController);
        setMainFrame(mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        distance = Integer.parseInt(JOptionPane.showInputDialog("Input segmentation degree:"));
        showHorizontalSliderDialog(0, 250, 100, "Input segmentation degree:");

    }

    public void showHorizontalSliderDialog(int start, int finish, int init, String title){
        JDialog dialog = new JDialog(getMainFrame(),title, false);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setVisible(true);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(mainFrame);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, start, finish, init);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JButton enterButton = new JButton("OK");
        enterButton.addActionListener(new ChooseSegmetationListener(getProcessController(), slider, dialog));
        dialog.add(slider, BorderLayout.CENTER);
        dialog.add(enterButton, BorderLayout.SOUTH);
    }

    public ImageProcessController getProcessController() {
        return processController;
    }

    public void setProcessController(ImageProcessController processController) {
        this.processController = processController;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}

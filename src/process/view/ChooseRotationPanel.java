package process.view;

import process.ImageProcessController;
import process.menu.operations.ChooseRotationListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.BorderLayout;

public class ChooseRotationPanel extends JPanel {
    JSlider slider;

    public ChooseRotationPanel(ImageProcessController processController, int start, int finish, int init){
        setLayout(new BorderLayout());
        setSize(300, 200);
        setVisible(true);

        slider = new JSlider(JSlider.HORIZONTAL, start, finish, init);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChooseRotationListener(processController, slider));
        add(slider, BorderLayout.CENTER);
    }
}

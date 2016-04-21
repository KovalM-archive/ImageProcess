package process.view;

import process.ImageProcessController;
import process.menu.operations.ChooseScalabilityListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.BorderLayout;

public class ChooseScalabilityPanel extends JPanel {
    private JSlider slider;

    public ChooseScalabilityPanel(ImageProcessController processController, int start, int finish, int init){
        setLayout(new BorderLayout());
        setSize(300, 200);
        setVisible(true);

        slider = new JSlider(JSlider.HORIZONTAL, start, finish, init);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChooseScalabilityListener(processController, slider));
        add(slider, BorderLayout.CENTER);
    }

    public JSlider getSlider() {
        return slider;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }
}

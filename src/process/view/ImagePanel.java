package process.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{
    private JLabel sourceImageLabel;
    private JLabel resultImageLabel;
    private BufferedImage sourceImage;
    private BufferedImage resultImage;
    private int imageWidth;
    private int imageHeight;

    public ImagePanel(int imageWidth, int imageHeight){
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSize(imageWidth*2, imageHeight);
        setLayout(new BorderLayout());

        sourceImageLabel = new JLabel();
        sourceImageLabel.setMaximumSize(new Dimension(imageWidth, imageHeight));
        add(sourceImageLabel, BorderLayout.WEST);

        resultImageLabel = new JLabel();
        resultImageLabel.setMaximumSize(new Dimension(imageWidth, imageHeight));
        add(resultImageLabel, BorderLayout.EAST);
    }

    public JLabel getSourceImageLabel() {
        return sourceImageLabel;
    }

    public void setSourceImageLabel(JLabel sourceImageLabel) {
        this.sourceImageLabel = sourceImageLabel;
    }

    public JLabel getResultImageLabel() {
        return resultImageLabel;
    }

    public void setResultImageLabel(JLabel resultImageLabel) {
        this.resultImageLabel = resultImageLabel;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(BufferedImage sourceImage) {
        getSourceImageLabel().setIcon(new ImageIcon(sourceImage));
        this.sourceImage = sourceImage;
    }

    public BufferedImage getResultImage() {
        return resultImage;
    }

    public void setResultImage(BufferedImage resultImage) {
        getResultImageLabel().setIcon(new ImageIcon(resultImage));
        this.resultImage = resultImage;
    }
}

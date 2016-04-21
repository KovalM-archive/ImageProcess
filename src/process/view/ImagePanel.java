package process.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{
    private JLabel sourceImageLabel;
    private JLabel resultImageLabel;
    private BufferedImage sourceImage;
    private BufferedImage reserveResultImage;
    private BufferedImage reserveSourceImage;
    private BufferedImage resultImage;
    private int imageWidth;
    private int imageHeight;

    public ImagePanel(int imageWidth, int imageHeight){
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSize(imageWidth*2, imageHeight);
        setLayout(null);
        sourceImageLabel = new JLabel();
        JScrollPane scrollLeft = new JScrollPane(sourceImageLabel);
        scrollLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollLeft.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollLeft.setBounds(0, 0, imageWidth, imageHeight);
        add(scrollLeft);

        resultImageLabel = new JLabel();
        JScrollPane scrollRight = new JScrollPane(resultImageLabel);
        scrollRight.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollRight.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollRight.setBounds(imageWidth + 1, 0, imageWidth, imageHeight);
        add(scrollRight);
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

    public BufferedImage getReserveResultImage() {
        return reserveResultImage;
    }

    public void setReserveResultImage(BufferedImage reserveResultImage) {
        this.reserveResultImage = reserveResultImage;
    }

    public BufferedImage getReserveSourceImage() {
        return reserveSourceImage;
    }

    public void setReserveSourceImage(BufferedImage reserveSourceImage) {
        this.reserveSourceImage = reserveSourceImage;
    }
}

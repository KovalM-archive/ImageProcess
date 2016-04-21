package process.view;

import process.ImageProcessController;
import process.menu.ExitMenuListener;
import process.menu.OpenMenuListener;
import process.menu.SaveMenuListener;
import process.menu.operations.AnaglyphListener;
import process.menu.operations.FilterListener;
import process.menu.operations.SegmentationListener;
import process.model.ImageProcess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainWindow implements ViewConstants {
    public MainWindow() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame mainFrame = new JFrame(APPLICATION_NAME);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLocationRelativeTo(null);

        JMenuBar mainMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(FILE);
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem openItem = new JMenuItem(OPEN, KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem saveItem = new JMenuItem(SAVE, KeyEvent.VK_S);
        JMenuItem exitItem = new JMenuItem(EXIT, KeyEvent.VK_Q);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        mainMenuBar.add(fileMenu);

        JMenu operationsMenu = new JMenu(OPERATIONS);
        fileMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem filtersItem = new JMenuItem(FILTERS, KeyEvent.VK_R);
        JMenuItem anaglyphItem = new JMenuItem(ANAGLYPH, KeyEvent.VK_A);
        JMenuItem segmentationItem = new JMenuItem(SEGMENTATION, KeyEvent.VK_D);

        operationsMenu.add(filtersItem);
        operationsMenu.add(anaglyphItem);
        operationsMenu.add(segmentationItem);
        mainMenuBar.add(operationsMenu);
        mainFrame.setJMenuBar(mainMenuBar);

        JToolBar fileToolbar = new JToolBar(JToolBar.HORIZONTAL);
        JButton openButton = new JButton(new ImageIcon(OPEN_TOOLBAR_FILE));
        JButton saveButton = new JButton(new ImageIcon(SAVE_TOOLBAR_FILE));

        fileToolbar.add(openButton);
        fileToolbar.add(saveButton);
        fileToolbar.setFloatable(false);
        fileToolbar.setVisible(true);
        mainFrame.add(fileToolbar, BorderLayout.NORTH);

        ImagePanel imagePanel = new ImagePanel(IMAGE_WIDTH, IMAGE_HEIGHT);
        ImageProcessController processController = new ImageProcessController(new ImageProcess(), imagePanel);

        ChooseScalabilityPanel scalabilityPanel = new ChooseScalabilityPanel(processController, 0, 400, 100);
        scalabilityPanel.setBounds(0, IMAGE_HEIGHT+1, 300, 100);
        ChooseRotationPanel rotationPanel = new ChooseRotationPanel(processController, 0, 360, 0);
        rotationPanel.setBounds(301, IMAGE_HEIGHT+1, 200, 100);
        imagePanel.add(rotationPanel);
        imagePanel.add(scalabilityPanel);
        mainFrame.add(imagePanel, BorderLayout.CENTER);

        SaveMenuListener saveTable = new SaveMenuListener(processController);
        saveButton.addActionListener(saveTable);
        saveItem.addActionListener(saveTable);

        OpenMenuListener openTable = new OpenMenuListener(processController);
        openButton.addActionListener(openTable);
        openItem.addActionListener(openTable);

        exitItem.addActionListener(new ExitMenuListener());

        segmentationItem.addActionListener(new SegmentationListener(processController, mainFrame));
        filtersItem.addActionListener(new FilterListener(processController));
        anaglyphItem.addActionListener(new AnaglyphListener(processController));
        mainFrame.setVisible(true);
    }
}

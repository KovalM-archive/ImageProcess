package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
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

        JMenu editMenu = new JMenu(OPERATIONS);
        fileMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem filtersItem = new JMenuItem(FILTERS, KeyEvent.VK_R);
        JMenuItem anaglyphItem = new JMenuItem(ANAGLYPH, KeyEvent.VK_A);
        JMenuItem segmentationItem = new JMenuItem(SEGMENTATION, KeyEvent.VK_D);

        editMenu.add(filtersItem);
        editMenu.add(anaglyphItem);
        editMenu.add(segmentationItem);
        mainMenuBar.add(editMenu);
        mainFrame.setJMenuBar(mainMenuBar);

        JToolBar fileToolbar = new JToolBar(JToolBar.HORIZONTAL);

        JButton openButton = new JButton(new ImageIcon(OPEN_TOOLBAR_FILE));
        openButton.setActionCommand(OPEN);
        JButton saveButton = new JButton(new ImageIcon(SAVE_TOOLBAR_FILE));
        saveButton.setActionCommand(SAVE);

        fileToolbar.add(openButton);
        fileToolbar.add(saveButton);
        fileToolbar.setFloatable(false);
        fileToolbar.setVisible(true);
        mainFrame.add(fileToolbar, BorderLayout.NORTH);

        JTabbedPane tableTab = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
        mainFrame.add(tableTab, BorderLayout.CENTER);

        SaveMenuListener saveTable = new SaveMenuListener(tableTab);
        saveButton.addActionListener(saveTable);
        saveItem.addActionListener(saveTable);

        OpenMenuListener openTable = new OpenMenuListener(tableTab);
        openButton.addActionListener(openTable);
        openItem.addActionListener(openTable);

        exitItem.addActionListener(new ExitMenuListener());

        mainFrame.setVisible(true);
    }
}

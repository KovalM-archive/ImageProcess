package view;

import chooser.FileChooser;

import javax.swing.JTabbedPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenMenuListener implements ActionListener {
    private JTabbedPane tableTab;

    public OpenMenuListener(JTabbedPane tableTab){
        this.tableTab = tableTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileChooser chooser = new FileChooser(System.getProperty("user.dir"));
        chooser.showOpenDialog(null);
        if (chooser.isSelectedFlag()) {
            if (chooser.getSelectedFile().getName().contains(".xml")) {
                //jtpVkladka.setTitleAt(jtpVkladka.getSelectedIndex(), jFileChooser.getSelectedFile().getName());
//                new XMLFile(chooser.getSelectedFile().getPath(), tableTab).readFile(chooser.getSelectedFile().getName());
            }
        }
    }
}

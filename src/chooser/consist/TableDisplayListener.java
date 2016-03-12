package chooser.consist;

import chooser.choose.ChooseFilePanel;

import javax.swing.JToolBar;

public class TableDisplayListener extends ModeListener {
    public TableDisplayListener(ConsistDirectoryPanel consistDirectoryPanel,JToolBar changeConsist,ChooseFilePanel chooseFilePanel){
        super(consistDirectoryPanel,changeConsist,chooseFilePanel);
    }

    protected void changeMode(){
        TableDisplayPanel newTable = new TableDisplayPanel(consistDirectoryPanel,
                consistDirectoryPanel.getCurrentDirectory(),chooseFilePanel);
        consistDirectoryPanel.setDisplayPanel(newTable);
    }
}

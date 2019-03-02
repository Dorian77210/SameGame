package controller;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import ui.Window;

import states.WindowState;;

public class HomeController implements ActionListener {

    private JPanel homeView;

    private static final String CHOOSE_FILE_ACTION_COMMAND = "CHOOSE_FILE_ACTION_COMMAND";

    public HomeController(JPanel homeView, JButton chooseFile) {
        this.homeView = homeView;

        chooseFile.setActionCommand(CHOOSE_FILE_ACTION_COMMAND);
        chooseFile.addActionListener(this);
    }

    @Override 
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if(actionCommand.equals(CHOOSE_FILE_ACTION_COMMAND)) {
            this.manageChoiceOfFile();
        }
    }

    private void manageChoiceOfFile() {
        Window window = (ui.Window)SwingUtilities.getWindowAncestor(this.homeView);
        JFileChooser fileChooser = new JFileChooser(".");
        int chooseState = fileChooser.showOpenDialog(null);
        if(chooseState == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            window.changeView(file);
        }
        
    }
}
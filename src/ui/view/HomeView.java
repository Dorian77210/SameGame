package ui.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.FlowLayout;

import controller.HomeController;

public class HomeView extends JPanel {
    
    private JButton chooseFile;

    private HomeController controller;

    public HomeView() {
        super(new FlowLayout(FlowLayout.CENTER));
        this.chooseFile = new JButton("Choose file");
        this.add(this.chooseFile);

        this.controller = new HomeController(this, this.chooseFile);
    }
}
package ui;

import states.WindowState;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import java.io.File;

import ui.view.HomeView;
import ui.view.GameView;

public class Window extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private WindowState state;

    private JPanel currentView;

    public Window() {
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.currentView = new JPanel(); //default value
        this.add(this.currentView, BorderLayout.CENTER);

        this.changeView(WindowState.HOME);
    }

    public void changeView(WindowState state) {
        this.remove(this.currentView);
        this.state = state;

        if(this.state.equals(WindowState.HOME)) {
            this.currentView = new HomeView();
        }

        this.revalidate();
    }

    public void changeView(File file) {
        this.remove(this.currentView);
        this.state = WindowState.IN_GAME;

        this.currentView = new GameView(file);
        this.revalidate();
        
    }

    ///////////////////////////////////////:
    @Override 
    public void revalidate() {
        this.add(this.currentView, BorderLayout.CENTER);
        super.revalidate();
    }
}
package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import ui.view.GameView;
import ui.Cell;

public class CellController implements MouseListener {

    private Cell cell;

    public CellController(Cell cell) {
        this.cell = cell;
        this.cell.addMouseListener(this);
    }

    @Override 
    public void mouseEntered(MouseEvent event) {
        GameView view = (GameView)this.cell.getParent();
        if(this.cell.getItem() != null) {
            view.setSelectedCell(this.cell);
        }
    }

    @Override 
    public void mouseExited(MouseEvent event) {
        
    }

    @Override 
    public void mouseClicked(MouseEvent event) {
        GameView view = (GameView)this.cell.getParent();
        view.reveal();
    }

    @Override 
    public void mouseReleased(MouseEvent event) {

    }

    @Override 
    public void mousePressed(MouseEvent event) {

    }
}
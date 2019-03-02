package ui;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Point;

import model.Item;

import controller.CellController;

import helper.Palette;

public class Cell extends JLabel {

    private Item item;

    private CellController controller;

    public Cell(Item item) {
        super();
        this.item = item;

        //ui configuration
        this.setIcon(new ImageIcon(this.item.getFilename()));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBackground(Palette.BASIC_CELL_COLOR); //default color

        this.controller = new CellController(this);
    }

    public Item getItem() {
        return this.item;
    }

    public void changeItem(Item item) {
        this.item = item;
        ImageIcon icon = this.item == null ? null : new ImageIcon(this.item.getFilename());
        this.setIcon(icon);
    }

    public void deleteItem() {
        this.changeItem(null);
    }
}
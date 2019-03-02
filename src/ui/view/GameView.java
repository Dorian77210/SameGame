package ui.view;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Point;

import java.io.File;

import parser.FileParser;

import ui.Cell;

import model.Item;

import helper.Palette;

import java.util.List;
import java.util.ArrayList;

public class GameView extends JPanel {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 10;

    private Cell[][] cells;

    private List<Cell> selectedCells;

    private Cell selectedCell;

    public GameView(File file) {
        super(new GridLayout(HEIGHT, WIDTH));
        this.cells = new Cell[HEIGHT][WIDTH];
        this.generate(file);

        this.selectedCells = new ArrayList<Cell>();
    }

    private void generate(File file) {
        //retrieve items
        Item[][] items = FileParser.fileToGame(file);

        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                this.cells[y][x] = new Cell(items[y][x]);
                this.add(this.cells[y][x]);
            }
        }
    }

    public void setSelectedCell(Cell base) {
        if(this.selectedCell == null) this.selectedCell = base;

        Item baseItem = base.getItem();
        Item item = this.selectedCell.getItem();

        if(item != null) {
            if(!item.equals(baseItem)) {
                this.removeSelectedCells();
                base.setBackground(Palette.SELECTED_CELL_COLOR);
                this.addNeightbour(base);
                this.showSelectedCells();
            }
        }
        

        this.selectedCell = base;
    }

    private void showSelectedCells() {
        for(Cell cell : this.selectedCells) {
            cell.setBackground(Palette.SELECTED_CELL_COLOR);
        }
    }

    private void addNeightbour(Cell cell) {
        this.selectedCells.add(cell);
        //recursive algorithm
        Item item = cell.getItem();
        Point position = item.getPosition();

        Cell topCell = this.getTopCell(position);
        if(topCell != null) {
            Item topItem = topCell.getItem();
            if(topItem != null && topItem.equals(item)) {
                if(!this.selectedCells.contains(topCell)) {
                    this.selectedCells.add(topCell);
                    this.addNeightbour(topCell);
                }
            }
        }

        Cell leftCell = this.getLeftCell(position);
        if(leftCell != null) {
            Item leftItem = leftCell.getItem();
            if(leftItem != null && leftItem.equals(item)) {
                if(!this.selectedCells.contains(leftCell)) {
                    this.selectedCells.add(leftCell);
                    this.addNeightbour(leftCell);
                }
            }
        }
        
        Cell bottomCell = this.getBottomCell(position);
        if(bottomCell != null) {
            Item bottomItem = bottomCell.getItem();
            if(bottomItem != null && bottomItem.equals(item)) {
                if(!this.selectedCells.contains(bottomCell)) {
                    this.selectedCells.add(bottomCell);
                    this.addNeightbour(bottomCell);                
                }
            }
        }
        
        Cell rightCell = this.getRightCell(position);
        if(rightCell != null) {
            Item rightItem = rightCell.getItem();
            if(rightItem != null && rightItem.equals(item)) {
                if(!this.selectedCells.contains(rightCell)) {
                    this.selectedCells.add(rightCell);
                    this.addNeightbour(rightCell);                
                }
            }
        }
    }

    public void removeSelectedCells() {
        for(Cell cell : this.selectedCells) {
            cell.setBackground(Palette.BASIC_CELL_COLOR);
        }

        this.selectedCells.clear();
    }

    public void reveal() {
        if(this.selectedCells.size() > 2) {
            List<Cell> topestCells = this.findTopestCells();
            for(Cell cell : this.selectedCells) {
                cell.deleteItem();
                cell.setBackground(Palette.BASIC_CELL_COLOR);
            }

            this.selectedCells.clear();

            //down the items
            for(Cell cell : topestCells) {
                int count = this.findDeepByCell(cell);
            }
        }
    }

    private int findDeepByCell(Cell cell) {
        int deep = 0;
        Cell c = cell;

        while(true) {
            if(c.getItem() == null) return deep;
            else {
                deep++;
            }

            c = this.getBottomCell(c.getItem().getPosition());
        }
    }

    private List<Cell> findTopestCells() {
        List<Cell> list = new ArrayList<Cell>();
        Cell topCell;

        for(Cell cell : this.selectedCells) {
            topCell = this.getTopCell(cell.getItem().getPosition());
            if(topCell != null && !this.selectedCells.contains(topCell)) {
                list.add(topCell);
            }
        }

        return list;
    }

    public Cell getCell(Point position) {
        int x = position.x, y = position.y;
        return ((x < 0 || x >= WIDTH) || (y < 0 || y >= HEIGHT))
               ? null 
               : this.cells[y][x];

    }

    public Cell getTopCell(Point position) {
        return (position.x > 0) ? this.getCell(new Point(position.x, position.y - 1)) : null;
    }

    public Cell getBottomCell(Point position) {
        return (position.y < (HEIGHT - 1)) ? this.getCell(new Point(position.x, position.y + 1)) : null;
    }

    public Cell getRightCell(Point position) {
        return (position.x < (WIDTH - 1)) ? this.getCell(new Point(position.x + 1, position.y)) : null;
    }

    public Cell getLeftCell(Point position) {
        return (position.x > 0) ? this.getCell(new Point(position.x - 1, position.y)) : null;
    }
}
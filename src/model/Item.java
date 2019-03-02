package model;

import java.awt.Point;

import states.ItemType;

public class Item {

    private Point position;

    private String filename;

    private ItemType type;

    public Item(Point position, ItemType type) {
        this.position = position;
        this.type = type;

        this.typeToFilename();
    }

    public static final ItemType charToItemType(char c) {
        if(c == 'R') {
            return ItemType.RED;
        } else if(c == 'B') {
            return ItemType.BLUE;
        } else if(c == 'V') {
            return ItemType.GREEN;
        }

        return null;
    }

    private final void typeToFilename() {
        this.filename = "rsc/images/";
        if(this.type.equals(ItemType.BLUE)) {
            this.filename += "blue.png";
        } else if(this.type.equals(ItemType.RED)) {
            this.filename += "red.png";
        } else if(this.type.equals(ItemType.GREEN)) {
            this.filename += "green.png";
        }
    }

    public Point getPosition() {
        return this.position;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean equals(Item item) {
        return this.filename.equals(item.filename);
    }
}
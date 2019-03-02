package parser;

import model.Item;

import reader.GameReader;

import ui.view.GameView;

import java.awt.Point;

import java.io.File;

import states.ItemType;

public class FileParser {

    public static final Item[][] fileToGame(File file) {
        Item[][] items = new Item[GameView.HEIGHT][GameView.WIDTH];
        String content = GameReader.readGameFile(file);
        Point position = null;
        char c;
        ItemType type;

        for(int i = 0; i < content.length(); i++) {
            c = content.charAt(i);
            type = Item.charToItemType(c);
            position = new Point(i % GameView.WIDTH, i / GameView.WIDTH);

            items[position.y][position.x] = new Item(position, type);
        }

        return items;
    }

}   
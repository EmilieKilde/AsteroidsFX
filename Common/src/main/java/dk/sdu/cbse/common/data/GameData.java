package dk.sdu.cbse.common.data;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private int displayWidth = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private final Pane gameWindow = new Pane();


    public GameKeys getKeys() {
        return keys;
    }

    public Pane getGameWindow() {
        return gameWindow;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


}
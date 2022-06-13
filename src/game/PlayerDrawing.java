package game;

import java.util.ArrayList;
import java.util.List;
import drawings.Drawing;
import graphics.graphics;

public class PlayerDrawing {
    private static List<Drawing> drawings = new ArrayList<>();

    public static void addDrawing(Drawing drawing){
        drawings.add(drawing);
    }
    public static void drawDrawings(){

        graphics.setColor(0.5f, 0, 0, 1);
        for (Drawing drawing : drawings) {
            graphics.setColor(0, 0, 1, 1);
            drawing.drawToScreen();
        }
    }
}

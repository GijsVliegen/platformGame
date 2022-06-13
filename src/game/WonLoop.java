package game;

import drawings.*;
import graphics.Renderer;
import resource.ImageResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WonLoop extends Loop{
    static Square background = null;
    public static List<Image> images = new ArrayList<>();
    public static List<Button> buttons = new ArrayList<>();
    public static List<Drawing> drawings = new ArrayList<>();
    public static List<Text> texts = new ArrayList<>();
    public WonLoop(){

        background = new Square(-Renderer.unitsWide/2f, 0, Renderer.unitsWide, Renderer.getUnitsTall());
        background.setColor(0, 0, 0, 0.3f);
        ImageResource backRes = null;
        ImageResource wonRes = null;
        ImageResource opnieuwRes = null;
        try {
            backRes = new ImageResource("x.png");
            wonRes = new ImageResource("GameWon.png");
            opnieuwRes = new ImageResource("opnieuw.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wonRes == null || backRes == null || opnieuwRes == null){
            System.out.println("er is een image null in WonLoop");
        }
        images.add(new Image(wonRes, 0, 3, 4, 2.5f));
        buttons.add(new Button(Renderer.unitsWide/2-.25f, Renderer.getUnitsTall()-0.25f,  0.5f, "beginGame", backRes));
        buttons.add(new Button(-0, 1.8f,0.5f, "restartGame", opnieuwRes));
        buttons.get(0).setColor(1, 1, 1, 0);
        buttons.get(1).setColor(1, 1, 1, 0);


        Square canvas = new Square(-3, 1, 6, Renderer.getUnitsTall()-2);
        canvas.setColor(0.5f, 0.2f, 0.2f, 1);
        drawings.add(canvas);
    }
    public void updateScreen(float previousHeight, float newHeight){
        for (Text text: texts){
            text.addHeight(newHeight - previousHeight);
        }
        for (Button button: buttons){
            button.addHeight(newHeight - previousHeight);
        }
    }
    public void loop(){
        Renderer.setLowerLeftPos(0, -5);
        background.drawToScreen();
        for(Drawing draw: drawings){
            draw.drawToScreen();
        }
        for (Button b: buttons){
            b.drawToScreen();
        }
        for(Image im: images){
            im.drawToScreen();
        }

    }
}

package game;

import drawings.Button;
import drawings.Square;
import drawings.Text;
import game.player.PlayerMovement;
import graphics.Renderer;
import java.util.ArrayList;
import java.util.List;

public class OverlayLoop extends Loop{


    //test square om movement van speler na te kijken private static Square test = null;

    public void updateScreen(float previousHeight, float newHeight){
        for (Text text: texts){
            text.addHeight(newHeight - previousHeight);
        }
        for (Button button: buttons){
            button.addHeight(newHeight - previousHeight);
        }
    }
    public OverlayLoop(){
        System.out.println("woensel");
        Button toSettings = new Button(Renderer.unitsWide/2-.25f, Renderer.getUnitsTall()-0.25f, 0.5f, "beginSettings", null);
        toSettings.setColor(1, 0, 0,1);
        buttons.add(toSettings);
        texts.add(Text.textFromMiddleLeft("XSpeed = " + PlayerMovement.getSpeedX(), -5+0.2f, Renderer.getUnitsTall()-.2f, 0.3f));
        texts.add(Text.textFromMiddleLeft("YSpeed = " + PlayerMovement.getSpeedY(), -5+0.2f, Renderer.getUnitsTall()-.6f, 0.3f));
        texts.add(Text.textFromMiddleLeft("posX = " + PlayerMovement.getXPos(), -5+0.2f, Renderer.getUnitsTall()-1, 0.3f));
        texts.add(Text.textFromMiddleLeft("posY = " + PlayerMovement.getYPos(), -5+0.2f, Renderer.getUnitsTall()-1.4f, 0.3f));
        //test = new Square(-.5f, .5f, 1, 1);
        //test.setColor(0, 0, 0, .5f);
    }

    public void loop(){
        texts.get(0).setText("XSpeed = " + PlayerMovement.getSpeedX());
        texts.get(1).setText("YSpeed = " + PlayerMovement.getSpeedY());
        texts.get(2).setText("posX = " + PlayerMovement.getXPos());
        texts.get(3).setText("posY = " + PlayerMovement.getYPos());
        Renderer.setLowerLeftPos(0, -5);
        //test.drawToScreen();
        buttons.get(0).drawToScreen();
        for (Text text: texts){
            text.drawText();
        }
    }
}

package game;

import drawings.Button;
import drawings.Square;
import drawings.Text;
import graphics.Renderer;
import resource.ImageResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsLoop extends Loop{
    static Square background = null;

    public SettingsLoop(){
        background = new Square(-Renderer.unitsWide/2f, 0, Renderer.unitsWide, Renderer.getUnitsTall());
        background.setColor(0, 0, 0, 0.3f);
        ImageResource backRes = null;
        try {
            backRes = new ImageResource("x.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (backRes == null){
            System.out.println("er is een image null in SettingsLoop");
        }
        buttons.add(new Button(Renderer.unitsWide/2-.25f, Renderer.getUnitsTall()-0.25f, 0.5f, "beginGame", backRes));
        buttons.get(0).setColor(1, 1, 1, 0);
    }
    public void updateScreen(float previousHeight, float newHeight){
        System.out.println("increased height by: " + (newHeight - previousHeight));
        background.addHeight(newHeight - previousHeight);
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
        for (Button button: buttons){
            button.drawToScreen();
        }

    }
}

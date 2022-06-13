package game;

import com.jogamp.opengl.GL2;
import drawings.Button;
import drawings.Image;
import drawings.Text;
import graphics.Renderer;
import resource.ImageResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeginLoop extends Loop{
    static Image backImage = null;
    static Image startImage = null;
    public static Text randText;
    public BeginLoop(){
        ImageResource backgroundRes = null;
        ImageResource startImageRes = null;
        ImageResource startRes = null;
        try{
            backgroundRes = new ImageResource("hackerman Background.png");
            startImageRes = new ImageResource("WoenselWapenschild2.png");
            startRes = new ImageResource("start.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (backgroundRes == null || startImageRes == null || startRes == null){
            System.out.println("er is een image null in BeginLoop");
        }
        startImage = new Image(startImageRes, 0, Renderer.getUnitsTall()/2, 0.2f, 0.2f);
        backImage = new Image(backgroundRes, 0, Renderer.getUnitsTall()/2, Renderer.unitsWide, Renderer.getUnitsTall());
        buttons.add(new Button(0, 0.45f, 0.5f, "beginGame", startRes));
        buttons.get(0).setColor(1, 1, 1, 0);
        randText = Text.textFromMiddle("Woensel", 0, 1, .4f);
        randText.setColor(0,1 ,0 ,1);
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

        //float operaties zijn niet altijd volledig correct
        if (Math.abs(startImage.getSizeMultiplier() - 20) > 0.0001f)
            startImage.sizeMultiplier += 0.5f;
            //duurt 19*2 stappen om 20 te bereiken
            //dus best ook 360*nrSpins graden bereiken op 38 stappen

        if (!(Math.abs(startImage.getSizeMultiplier() - 20) < 0.0001f && startImage.getRotation() == 0f)) {
            startImage.addRotation((360/(float)38)*5);
        }
        PlayerDrawing.drawDrawings();
        backImage.drawToScreen();
        startImage.drawToScreen();
        if ((Math.abs(startImage.getSizeMultiplier() - 20) < 0.0001f && startImage.getRotation() == 0f)) {
            buttons.get(0).drawToScreen();
        }
        randText.drawText();
    }
}

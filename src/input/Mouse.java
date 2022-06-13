package input;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import drawings.Button;
import game.*;
import game.player.Audio;
import graphics.EventListener;
import graphics.Renderer;
import java.util.List;

public class Mouse implements MouseListener {

    private void checkButtonClicked(float x, float y, List<Button> buttonList){
        float normalisedX = x/(float)Renderer.getWindowWidth()*Renderer.unitsWide-Renderer.unitsWide/2;
        float normalisedY = Renderer.getUnitsTall()-y/(float)Renderer.getWindowHeight()*Renderer.getUnitsTall();
        System.out.println("x = " + normalisedX + ", y = "+ normalisedY);
        for (Button but: buttonList){
            if (but.inBox(normalisedX, normalisedY)){
                but.clicked();
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent){
        checkButtonClicked(mouseEvent.getX(), mouseEvent.getY(), Renderer.currentLoop.buttons);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //EventListener.setFollowLocation(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseEvent mouseEvent) {

    }
}

package input;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import game.player.PlayerMovement;
import graphics.Renderer;

public class KeyBoard implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        //149 is pijl naar links
        //150 is pijl naar boven
        //151 is pijl naar rechts
        //152 is pijl naar onder
        if (Renderer.state.equals("game")){
            switch (e.getKeyCode()) {
                case 151 -> PlayerMovement.moveRight();
                case 149 -> PlayerMovement.moveLeft();
                case 150 -> PlayerMovement.jump();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (Renderer.state.equals("game")){
            switch (e.getKeyCode()) {
                case 151 -> PlayerMovement.stopMoveRight();
                case 149 -> PlayerMovement.stopMoveLeft();
            }
        }
    }

}

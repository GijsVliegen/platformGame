package game.player;

import drawings.Background;
import drawings.Drawing;

import drawings.Square;
import game.Collision;
import game.Loop;
import game.OverlayLoop;
import graphics.graphics;
import graphics.Renderer;
import graphics.EventListener;
import resource.ImageResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerMovement {

    static float speedY = 0;
    static float speedX = 0;
    static float xPos = -Renderer.unitsWide/2+1;
    static float yPos = .5f;
    static float width = 1f;
    static float height = 1f;
    static int inverted = 0;
    static boolean moveLeft = false;
    static boolean moveRight = false;
    static ImageResource Head = null;
    private static String state = "ground";
    //0 voor stilstaan, 1 voor links, 2 voor rechts
    private static int movingDirX = 0;

    public static void reset(){
        speedX = 0;
        speedY = 0;
        xPos = -Renderer.unitsWide/2+1;
        yPos = .5f;
        width = Head.getWidthImage()/ Head.getHeightImage();
        height = 1;
        inverted = 0;
        moveLeft = false;
        moveRight = false;
        movingDirX = 0;
        state = "ground";
        Renderer.switchLoop("game");
    }
    public static float getSpeedX(){
        return speedX;
    }
    public static float getSpeedY(){
        return speedY;
    }
    public static float getXPos(){
        return xPos;
    }
    public static float getYPos(){
        return yPos;
    }
    public static void initPlayer(){
        try{
            Head = new ImageResource("Tijs.png");
        }catch (Exception e) {
            e.printStackTrace();
        }
        //bedoeling is dat height 1 is, en width genormaliseerd naar height
        width = Head.getWidthImage()/ Head.getHeightImage();
    }
    public static void jump(){
        if (state.equals("ground")){
            state = "jump";
        }
    }
    public static void moveSide(float XDirection){
        float normalisedX = XDirection/(float)Renderer.getWindowWidth()*Renderer.unitsWide-Renderer.unitsWide/2;
        System.out.println(normalisedX);
        if (normalisedX < 0)
            moveLeft();
        if (normalisedX >= 0){
            moveRight();
        }
    }
    public static void moveLeft(){
        moveLeft = true;
        movingDirX = 1;
        updateXSpeed();
    }
    public static void moveRight(){
        moveRight = true;
        movingDirX = 2;
        updateXSpeed();
    }
    public static void stopMoveLeft(){
        moveLeft = false;
        if (moveRight){movingDirX = 2;}
        else{movingDirX = 0;}
        updateXSpeed();
    }
    public static void stopMoveRight(){
        moveRight = false;
        if (moveLeft){movingDirX = 1;}
        else{movingDirX = 0;}
        updateXSpeed();
    }
    public static void XStill(){
        stopMoveLeft();
        stopMoveRight();
    }
    private static void updateXSpeed(){
        switch (movingDirX){
            case 0 -> speedX = 0f;
            case 1 -> speedX = -0.2f;
            case 2 -> speedX = 0.2f;
        }
    };
    public static String getState(){
        return state;
    }
    public static void setState(String newState){
        state = newState;
    }
    public static void setSpeedY(float ySpeed){
        speedY = ySpeed;
    }

    public static void addGravity(float gravity){
        speedY -= gravity;
    }
    public static void updatePosition(){

        if (yPos < -1f && Renderer.state.equals("game")){
            Renderer.switchLoop("lost");
        }
        else {
            float earlierPosX = xPos;
            float earlierPosY = yPos;
            if (speedX < 0) {
                inverted = 1;
            } else if (speedX > 0) {
                inverted = 0;
            }
            xPos += speedX;
            yPos += speedY;
            //System.out.println(Renderer.leftBound + " <- leb, rib -> " + Renderer.rightBound + ", " + xPos + " <- xpos, uw -> " + Renderer.unitsWide);

            state = "airborn";
            //en als het niet blijkt dat ie airborn is, (door collision met bodem)
            //state terug naar grond zetten
            for (Drawing collision : Collision.collisions) {
                if (collision.checkCollision(xPos, yPos, width, height)) {
                    float[] newPos = collision.resolveCol(xPos, yPos, width, height, earlierPosX, earlierPosY);
                    xPos = newPos[0];
                    yPos = newPos[1];
                }
            }
            for (Square winSq : GameLoop.winSquares) {
                if (winSq.checkCollision(xPos, yPos, width, height)) {
                    GameLoop.removeWinSq(winSq);
                }
            }
            GameLoop.leftPos = Math.max(Renderer.leftBound, Math.min(Renderer.rightBound - Renderer.unitsWide, xPos + width / 2 - Renderer.unitsWide / 2));
            GameLoop.lowPos = Math.max(Renderer.lowerBound, Math.min(Renderer.upperBound - Renderer.getUnitsTall(), yPos - Renderer.getUnitsTall() / 2));

            //zorgt ervoor dat screen mee verplaatst met speler
            EventListener.updateScreenDisplacement();
        }

    }
    public static void drawPosition(){
        graphics.setColor(1f, 1, 1, 1f);
        graphics.drawImage(Head, xPos+width/(float)2, yPos+height/(float)2, width, height, inverted);
    }


}

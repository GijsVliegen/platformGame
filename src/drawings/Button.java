package drawings;

import game.player.GameLoop;
import game.player.PlayerMovement;
import graphics.Renderer;
import graphics.graphics;
import resource.ImageResource;

public class Button extends Drawing{

    private float[] button;
    private String action;
    private Image image;

    public float getX(){
        return button[0];
    }
    public float getY(){
        return button[1];
    }
    public float getWidth(){
        return button[2];
    }
    public float getHeight(){
        return button[3];
    }
    public void setY(float newHeight){
        button[1] = newHeight;
    }
    public void setAction(String newAction){action = newAction;}
    public void addHeight(float heightIncrease){button[1] += heightIncrease;}
    public void setColor(float r, float g, float b, float a){
        color = new float[]{r, g, b, a};
    }
    public Button(float midX, float midY, float height, String action, ImageResource imageRes){
        float width;
        if (imageRes != null){

            width = imageRes.getWidthImage()/imageRes.getHeightImage()*height;
        }
        else{
            width = height;
        }
        button = new float[]{midX-width/2f, midY-height/2f, width, height};
        super.color = new float[]{0, 0, 1, 1};
        this.action = action;
        if (imageRes != null)
            //width wordt toch gezet door Image()
            image = new Image(imageRes, midX, midY, 0, height);
    }
    public void backDark(){

    }
    public void LightUp(){

    }
    public void invokeByName(String methName) {
        System.out.println(methName);
        try { getClass().getDeclaredMethod(methName).invoke(this); }
        catch (RuntimeException e) { throw e; }
        catch (Exception e) { throw new RuntimeException(e); }
    }
    public void clicked(){
        System.out.println("button geklikt");
        invokeByName(action);
    }
    public void beginSettings(){
        Renderer.switchLoop("settings");
    }
    public void beginGame(){
        System.out.println("beginGame");
        Renderer.switchLoop("game");
    }
    public void restartGame(){ GameLoop.reset(); }
    @Override
    public void drawToScreen() {

        graphics.setColor(color[0], color[1], color[2], color[3]);
        graphics.fillRect(button[0], button[1], button[2], button[3]);
        if (image != null)
            image.drawToScreen();
    }

    public boolean inBox(float x, float y){
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }
    @Override
    public boolean checkCollision(float x, float y, float w, float h) {
        return false;
    }

    @Override
    public float[] resolveCol(float x, float y, float w, float h, float sX, float sY) {
        return new float[0];
    }
}

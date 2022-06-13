package drawings;

import graphics.graphics;
import graphics.Renderer;
import resource.ImageResource;

public class Background extends Drawing{

    int inverted = 0;
    public float rotation = 0;
    ImageResource image;
    public float getRotation(){
        return rotation;
    }
    public void addRotation(float newRot){
        rotation += newRot;
        if (rotation >= 360){
            rotation = 0;
        }
    }
    public Background(ImageResource imagee){
        image = imagee;
        color = new float[]{1, 1, 1 ,1};
    }
    @Override
    public void drawToScreen() {
        graphics.setColor(color[0], color[1], color[2], color[3]);
        graphics.setRotation(rotation);
        /*float leP = Renderer.leftPos;
        float uw = Renderer.unitsWide;
        float uh = Renderer.getUnitsTall();
        float loP = Renderer.lowPos;*/
        graphics.drawImage(image, 0, (Renderer.upperBound-Renderer.lowerBound)/2, Renderer.rightBound-Renderer.leftBound, Renderer.upperBound - Renderer.lowerBound, inverted);
        //graphics.drawImage(image, leP+uw/2, loP+uh/2, uw+2, uh+2, inverted);
        graphics.setRotation(0);
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

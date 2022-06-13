package drawings;

import graphics.graphics;
import resource.ImageResource;

public class Image extends Drawing{

    float x;
    float y;
    float width;
    float height;
    int inverted = 0;
    public float rotation = 0;
    public float sizeMultiplier = 1;
    public float getX(){ return x; }
    public float getY(){
        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    ImageResource image;
    public float getSizeMultiplier(){
        return sizeMultiplier;
    }
    public float getRotation(){
        return rotation;
    }
    public void addRotation(float newRot){
        rotation += newRot;
        if (rotation >= 360){
            rotation = 0;
        }
    }
    public Image(ImageResource imagee, float xx, float yy, float widthh, float heightt){
        image = imagee;
        this.x = xx;
        y = yy;
        width = widthh;
        height = imagee.getHeightImage()/imagee.getWidthImage()*widthh;
        if (widthh == 0){
            height = heightt;
            width = imagee.getWidthImage()/imagee.getHeightImage()*heightt;
        }
        color = new float[]{1, 1, 1 ,1};
    }
    @Override
    public void drawToScreen() {
        graphics.setColor(color[0], color[1], color[2], color[3]);
        graphics.setRotation(rotation);
        graphics.drawImage(image, x, y, width*sizeMultiplier, height*sizeMultiplier, inverted);
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

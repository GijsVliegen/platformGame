package drawings;
import graphics.graphics;
import game.player.Audio;
import game.player.PlayerMovement;

public class Square extends Drawing{
    private float[] square;
    private boolean collided = false;


    public Square(float x, float y, float width, float height){
        square = new float[]{x, y, width, height};
        super.color = new float[]{0, 0, 1, 1};
    }
    public boolean hasCollided(){return collided;}
    public void setCollided(boolean b){collided = b;}
    public void addHeight(float heightIncrease){square[1] += heightIncrease;}
    public float getX(){
        return square[0];
    }
    public float getY(){
        return square[1];
    }
    public float getWidth(){
        return square[2];
    }
    public float getHeight(){
        return square[3];
    }
    public void setColor(float r, float g, float b, float a){
        color = new float[]{r, g, b, a};
    }

    @Override
    public void drawToScreen() {
        graphics.setColor(color[0], color[1], color[2], color[3]);
        graphics.fillRect(square[0], square[1], square[2], square[3]); }

    @Override
    public boolean checkCollision(float x, float y, float width, float height){
        // collision x-axis?
        boolean collisionX = (x + width > getX() && x + width < getX() + getWidth())
                || (x > getX() && x < getX() + getWidth())
                || (x <= getX() && x + width >= getX() + getWidth());

        // collision y-axis?
        boolean collisionY = (y + height > getY() && y + height < getY() + getHeight())
                || (y > getY() && y < getY() + getHeight())
                || (y <= getY() && y + height >= getY() + getHeight());
        // collision only if on both axes
        return collisionX && collisionY;
    }

    @Override
    public float[] resolveCol(float x, float y, float width, float height, float earlierPosX, float earlierPosY){


        //System.out.println("earlier x = " + earlierPosX + ", earlier Y = " + earlierPosY);
        //in principe gaat het fout als ie op een hoek komt, maar gewoon negeren voorlopig

        //van links
        //y returnen klopt niet volledig
        if(earlierPosX+width <= getX()) {
            if (Math.abs(earlierPosX+width - getX()) > 0.001f)Audio.bunk();
            //System.out.println("lanks links");
            return new float[]{getX() - width, y};
        }
        //van rechts
        if(earlierPosX >= getX() + getWidth()) {
            if (Math.abs(earlierPosX - getX() - getWidth()) > 0.001f)Audio.bunk();
            //System.out.println("lanks rechts");
            return new float[]{getX() + getWidth(), y,};
        }
        //van boven
        if(earlierPosY >= getY() + getHeight()) {
            PlayerMovement.setState("ground");
            PlayerMovement.setSpeedY(0);
            return new float[]{x, getY() + getHeight()};
        }
        //van onder
        if(earlierPosY + height <=  getY()) {
            Audio.bunk();

            PlayerMovement.setSpeedY(0);
            return new float[]{x, getY() - height};
        }

        System.out.println("crazyCollisionStuff!");
        return new float[]{x, y};
    }
}

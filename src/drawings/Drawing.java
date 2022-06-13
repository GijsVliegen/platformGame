package drawings;

abstract public class Drawing {
    public float[] color;
    abstract public void drawToScreen();
    abstract public boolean checkCollision(float x, float y, float w, float h);
    abstract public float[] resolveCol(float x, float y, float w, float h, float sX, float sY);
    public void setColor(float r, float g, float b, float a){color = new float[]{r, g, b, a};}

}


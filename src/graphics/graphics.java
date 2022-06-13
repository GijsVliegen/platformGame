package graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import drawings.Glyph;
import resource.ImageResource;

public class graphics {

    static float red = 0;
    static float green = 0;
    static float blue = 1;
    static float alpha = 1;
    static float rotation = 0;
    //x is links, y is boven, enzv.

    /*public static void addRectBigMouse(int x, int y, int width, int height){
        float xFloat = (float)x/(float)Renderer.getWindowWidth()*Renderer.unitsWide-Renderer.unitsWide/2;
        float yFloat =(float)y/(float)Renderer.getWindowHeight()*Renderer.getUnitsTall()-Renderer.getUnitsTall()/2;
        float widthFloat = (float)width/(float)Renderer.getWindowWidth()*Renderer.unitsWide;
        float heightFloat = (float)height/(float)Renderer.getWindowHeight()*Renderer.getUnitsTall();

        System.out.println("addRectBigMouse: 1-> " + xFloat + "2-> " + yFloat + "3-> " + widthFloat + "4-> " + heightFloat);
        Renderer.addSquare(new float[]{xFloat, yFloat, widthFloat, heightFloat});
    }*/
    public static void fillRect(float x, float y, float width, float height) {
        GL2 gl = EventListener.gl;
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
        //volgorde van vertices maakt uit!, met klok mee of tegen klok in
        //maar niet door elkaar
            gl.glVertex2f(x, y); //linksboven
            gl.glVertex2f(x+width, y); //linksonder
            gl.glVertex2f(x+width, y+height); //rechtsonder
            gl.glVertex2f(x, y+height); //rechtsboven
        gl.glEnd();
    }

    //eventueel draaien nog implementeren, maar dan moeten er ook aanpassingen gebeuren in TextMaker
    //glyphs worden getekent van de rechterbovenhoek en coords worden gegeven in pixelaantallen
    public static void drawGlyph(Glyph glyph, float leftX, float topY, float factor){

        //text momenteel nog niet spiegelen
        int inverted = 0;

        float width = glyph.getDefaultwidth()*factor;
        float height = glyph.getDefaultheight()*factor;

        GL2 gl = EventListener.gl;
        Texture tex = glyph.getImageResource().getTexture();
        if (tex != null){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, tex.getTextureObject());
        }

        gl.glColor4f(red, green, blue, alpha);

        //heen draaaien
        //gl.glTranslatef(x, y, 0);
        //rotate around z axis
        //gl.glRotatef(rotation,0, 0, 1);

        //volgorde van vertices maakt uit!, met klok mee of tegen klok in
        //maar niet door elkaar
        gl.glBegin(GL2.GL_QUADS);
        //texCoords zijn genormalised
            gl.glTexCoord2f(inverted, 1);
            gl.glVertex2f(leftX, topY - height); //linksonder

            gl.glTexCoord2f(1-inverted, 1);
            gl.glVertex2f(leftX + width, topY - height); //rechtsonder

            gl.glTexCoord2f(1-inverted, 0);
            gl.glVertex2f(leftX + width, topY); //rechtsboven

            gl.glTexCoord2f(inverted, 0);
            gl.glVertex2f(leftX , topY); //linksboven
        gl.glEnd();
        //terug draaien
        //gl.glRotatef(-rotation,0, 0, 1);
        //gl.glTranslatef(-x, -y, 0);

        //stop binding

        gl.glBindTexture(gl.GL_TEXTURE_2D, 0);
        //System.out.println("getekend op x = " + leftX + " en y = " + topY + " met width = " + width + " en height = " + height);
    }

    //x en y zijn het midden van de image
    public static void drawImage(ImageResource image, float x, float y, float width, float height, int inverted) {

        GL2 gl = EventListener.gl;
        Texture tex = image.getTexture();
        if (tex != null){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, tex.getTextureObject());
        }

        gl.glColor4f(red, green, blue, alpha);

        //heen draaaien
        gl.glTranslatef(x, y, 0);
        //rotate around z axis en het middelpunt (x, y)
        gl.glRotatef(rotation,0, 0, 1);

        //volgorde van vertices maakt uit!, met klok mee of tegen klok in
        //maar niet door elkaar
        gl.glBegin(GL2.GL_QUADS);
            //texCoords zijn genormalised
            gl.glTexCoord2f(inverted, 1);
            gl.glVertex2f(-width/(float)2, -height/(float)2); //linksonder

            gl.glTexCoord2f(1-inverted, 1);
            gl.glVertex2f(width/(float)2, -height/(float)2); //rechtsonder

            gl.glTexCoord2f(1-inverted, 0);
            gl.glVertex2f(width/(float)2, height/(float)2); //rechtsboven

            gl.glTexCoord2f(inverted, 0);
            gl.glVertex2f(-width/(float)2, height/(float)2); //linksboven
        gl.glEnd();
        //terug draaien
        gl.glRotatef(-rotation,0, 0, 1);
        gl.glTranslatef(-x, -y, 0);

        //stop binding

        gl.glBindTexture(gl.GL_TEXTURE_2D, 0);
    }
    public static void setRotation(float newRot){
        rotation = newRot;
    }

    public static void fillTriangle(float topY, float baseX, float baseY, float baseLength){
        GL2 gl = EventListener.gl;
        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glVertex2f(baseX, baseY); //linksonder
            gl.glVertex2f(baseX+baseLength, baseY); //rechtsonder
            gl.glVertex2f(baseX+baseLength/2, topY); //rechtsboven
        gl.glEnd();
    }

    public static void setColor(float r, float g, float b, float a){
        red = r;
        green = g;
        blue = b;
        alpha = a;
    }
}

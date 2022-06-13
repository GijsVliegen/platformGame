package drawings;

import graphics.Renderer;
import resource.ImageResource;

public class Glyph {
    float defaultheight;
    float defaultwidth;
    static float defaultHeight = (float)57/Renderer.getWindowHeight()*Renderer.getUnitsTall();
    ImageResource imageRes;
    public Glyph(ImageResource imageRessource){
        imageRes = imageRessource;
        defaultheight = imageRessource.getHeightImage()/Renderer.getWindowHeight()*Renderer.getUnitsTall();//57
        defaultwidth = imageRessource.getWidthImage()/Renderer.getWindowWidth()*Renderer.unitsWide;
        //System.out.println(defaultwidth + " = width");
    }
    public ImageResource getImageResource(){
        return imageRes;
    }
    public float getDefaultheight() {
        return defaultheight;
    }
    public float getDefaultwidth(){
        return defaultwidth;
    }
}

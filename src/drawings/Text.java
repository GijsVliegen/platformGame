package drawings;

import resource.TextMaker;
import graphics.graphics;
import java.util.List;

public class Text {
    float midY;
    float midX;
    float factorDefaultSize;
    List<Integer> glyphNrs;
    float[] color;
    float totalWidth;

    public Text(float XValue, float YValue, float factorDefaultSize, List<Integer> glyphNrs, float totalWidth) {
        this.midY = YValue;
        this.midX = XValue;
        this.factorDefaultSize = factorDefaultSize;
        this.glyphNrs = glyphNrs;
        this.totalWidth = totalWidth;
        color = new float[]{0, 1, 0, 1};
    }
    public static Text textFromMiddle(String text, float midX, float midY, float factorDefaultSize){
        List<Integer> glyphNrs = TextMaker.getGlyphNrs(text);
        float totalWidth = TextMaker.getTotalWidth(glyphNrs);
        return new Text(midX, midY, factorDefaultSize, glyphNrs, totalWidth);
    }
    public static Text textFromUpperLeft(String text, float leftX, float topY, float factorDefaultSize){
        List<Integer> glyphNrs = TextMaker.getGlyphNrs(text);
        float totalWidth = TextMaker.getTotalWidth(glyphNrs);
        return new Text(leftX + totalWidth*factorDefaultSize/2f, topY - Glyph.defaultHeight*factorDefaultSize/2f, factorDefaultSize, glyphNrs, totalWidth);
    }
    public static Text textFromUpperLeftWithHeight(String text, float leftX, float topY, float height){
        float factorDefaultSize = height/Glyph.defaultHeight;
        List<Integer> glyphNrs = TextMaker.getGlyphNrs(text);
        float totalWidth = TextMaker.getTotalWidth(glyphNrs);
        return new Text(leftX + totalWidth*factorDefaultSize/2f, topY - height/2f, factorDefaultSize, glyphNrs, totalWidth);
    }
    public static Text textFromMiddleWithHeight(String text, float midX, float midY, float height){
        float factorDefaultSize = height/Glyph.defaultHeight;
        List<Integer> glyphNrs = TextMaker.getGlyphNrs(text);
        float totalWidth = TextMaker.getTotalWidth(glyphNrs);
        return new Text(midX, midY, factorDefaultSize, glyphNrs, totalWidth);
    }
    public static Text textFromMiddleLeft(String text, float leftX, float midY, float factorDefaultSize){
        List<Integer> glyphNrs = TextMaker.getGlyphNrs(text);
        float totalWidth = TextMaker.getTotalWidth(glyphNrs);
        return new Text(leftX + totalWidth*factorDefaultSize/2f, midY, factorDefaultSize, glyphNrs, totalWidth);
    }
    public void setColor(float r, float g, float b, float a){
        color = new float[]{r, g, b, a,};
    }
    public void setFactorDefaultSize(float factorDefaultSize) {
        this.factorDefaultSize = factorDefaultSize;
    }
    public void addHeight(float heightIncrease){this.midY += heightIncrease;}
    public void setHeight(float height) {
        this.midY= height;
    }
    public void setText(String text){
        glyphNrs = TextMaker.getGlyphNrs(text);
    }

    public void drawText() {
        graphics.setColor(color[0], color[1], color[2], color[3]);
        TextMaker.drawText(glyphNrs, midX, midY, totalWidth, factorDefaultSize);
    }
}

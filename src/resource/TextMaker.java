package resource;

import drawings.Glyph;
import graphics.graphics;
import graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class TextMaker {
    //default is 57 als height, "," en "q" komen iets onder de rest uit dus iets grotere height (62 en 64)
    //deze waarden worden, naast default width opgeslagen in Glyph class
    //nadat ze zijn omgevormd naar spelwindow coordinaten

    static Glyph[] charGlyphs = new Glyph[26+10+5];//abcde...xyz012...789,.:=(space)
    static float spaceBetweenChars; //4 pixels

    public static void init(){
        spaceBetweenChars = 4/Renderer.getWindowWidth()*Renderer.unitsWide;
        charGlyphs[0] = new Glyph(new ImageResource("BeornHeardGlyphs/AGlyph.png"));
        //werkt niet om een of andere reden, volgende keer nog eens proberen charGlyphs[1] = new Glyph(new ImageResource("BeornHeardGlyphs/BGlyph.png"));
        charGlyphs[2] = new Glyph(new ImageResource("BeornHeardGlyphs/CGlyph.png"));
        charGlyphs[3] = new Glyph(new ImageResource("BeornHeardGlyphs/DGlyph.png"));
        charGlyphs[4] = new Glyph(new ImageResource("BeornHeardGlyphs/EGlyph.png"));
        charGlyphs[5] = new Glyph(new ImageResource("BeornHeardGlyphs/FGlyph.png"));
        charGlyphs[6] = new Glyph(new ImageResource("BeornHeardGlyphs/GGlyph.png"));
        charGlyphs[7] = new Glyph(new ImageResource("BeornHeardGlyphs/HGlyph.png"));
        charGlyphs[8] = new Glyph(new ImageResource("BeornHeardGlyphs/IGlyph.png"));
        charGlyphs[9] = new Glyph(new ImageResource("BeornHeardGlyphs/JGlyph.png"));
        charGlyphs[10] = new Glyph(new ImageResource("BeornHeardGlyphs/KGlyph.png"));
        charGlyphs[11] = new Glyph(new ImageResource("BeornHeardGlyphs/LGlyph.png"));
        charGlyphs[12] = new Glyph(new ImageResource("BeornHeardGlyphs/MGlyph.png"));
        charGlyphs[13] = new Glyph(new ImageResource("BeornHeardGlyphs/NGlyph.png"));
        charGlyphs[14] = new Glyph(new ImageResource("BeornHeardGlyphs/OGlyph.png"));
        charGlyphs[15] = new Glyph(new ImageResource("BeornHeardGlyphs/PGlyph.png"));
        charGlyphs[16] = new Glyph(new ImageResource("BeornHeardGlyphs/QGlyph.png"));
        charGlyphs[17] = new Glyph(new ImageResource("BeornHeardGlyphs/RGlyph.png"));
        charGlyphs[18] = new Glyph(new ImageResource("BeornHeardGlyphs/SGlyph.png"));
        charGlyphs[19] = new Glyph(new ImageResource("BeornHeardGlyphs/TGlyph.png"));
        charGlyphs[20] = new Glyph(new ImageResource("BeornHeardGlyphs/UGlyph.png"));
        charGlyphs[21] = new Glyph(new ImageResource("BeornHeardGlyphs/VGlyph.png"));
        charGlyphs[22] = new Glyph(new ImageResource("BeornHeardGlyphs/WGlyph.png"));
        charGlyphs[23] = new Glyph(new ImageResource("BeornHeardGlyphs/XGlyph.png"));
        charGlyphs[24] = new Glyph(new ImageResource("BeornHeardGlyphs/YGlyph.png"));
        charGlyphs[25] = new Glyph(new ImageResource("BeornHeardGlyphs/ZGlyph.png"));
        charGlyphs[26] = new Glyph(new ImageResource("BeornHeardGlyphs/ZeroGlyph.png"));
        charGlyphs[27] = new Glyph(new ImageResource("BeornHeardGlyphs/OneGlyph.png"));
        charGlyphs[28] = new Glyph(new ImageResource("BeornHeardGlyphs/TwoGlyph.png"));
        charGlyphs[29] = new Glyph(new ImageResource("BeornHeardGlyphs/ThreeGlyph.png"));
        charGlyphs[30] = new Glyph(new ImageResource("BeornHeardGlyphs/FourGlyph.png"));
        charGlyphs[31] = new Glyph(new ImageResource("BeornHeardGlyphs/FiveGlyph.png"));
        charGlyphs[32] = new Glyph(new ImageResource("BeornHeardGlyphs/SixGlyph.png"));
        charGlyphs[33] = new Glyph(new ImageResource("BeornHeardGlyphs/SevenGlyph.png"));
        charGlyphs[34] = new Glyph(new ImageResource("BeornHeardGlyphs/EightGlyph.png"));
        charGlyphs[35] = new Glyph(new ImageResource("BeornHeardGlyphs/NineGlyph.png"));
        charGlyphs[36] = new Glyph(new ImageResource("BeornHeardGlyphs/CommaGlyph.png"));
        charGlyphs[37] = new Glyph(new ImageResource("BeornHeardGlyphs/PointGlyph.png"));
        charGlyphs[38] = new Glyph(new ImageResource("BeornHeardGlyphs/SemicolonGlyph.png")); //eigenlijk dubbele punt
        charGlyphs[39] = new Glyph(new ImageResource("BeornHeardGlyphs/EqualsGlyph.png"));
        charGlyphs[40] = new Glyph(new ImageResource("BeornHeardGlyphs/SpaceGlyph.png"));
    }
    public static List<Integer> getGlyphNrs(String text){
        List<Integer> nrs = new ArrayList<>();
        for (int i = 0; i < text.length(); i++){
            switch (text.toLowerCase().charAt(i)){
                case 'a' -> nrs.add(0);
                case 'b' -> nrs.add(1);
                case 'c' -> nrs.add(2);
                case 'd' -> nrs.add(3);
                case 'e' -> nrs.add(4);
                case 'f' -> nrs.add(5);
                case 'g' -> nrs.add(6);
                case 'h' -> nrs.add(7);
                case 'i' -> nrs.add(8);
                case 'j' -> nrs.add(9);
                case 'k' -> nrs.add(10);
                case 'l' -> nrs.add(11);
                case 'm' -> nrs.add(12);
                case 'n' -> nrs.add(13);
                case 'o' -> nrs.add(14);
                case 'p' -> nrs.add(15);
                case 'q' -> nrs.add(16);
                case 'r' -> nrs.add(17);
                case 's' -> nrs.add(18);
                case 't' -> nrs.add(19);
                case 'u' -> nrs.add(20);
                case 'v' -> nrs.add(21);
                case 'w' -> nrs.add(22);
                case 'x' -> nrs.add(23);
                case 'y' -> nrs.add(24);
                case 'z' -> nrs.add(25);
                case '0' -> nrs.add(26);
                case '1' -> nrs.add(27);
                case '2' -> nrs.add(28);
                case '3' -> nrs.add(29);
                case '4' -> nrs.add(30);
                case '5' -> nrs.add(31);
                case '6' -> nrs.add(32);
                case '7' -> nrs.add(33);
                case '8' -> nrs.add(34);
                case '9' -> nrs.add(35);
                case ',' -> nrs.add(36);
                case '.' -> nrs.add(37);
                case ':' -> nrs.add(38);
                case '=' -> nrs.add(39);
                case ' ' -> nrs.add(40);
            }
        }
        return nrs;
    }
    public static float getTotalWidth(List<Integer> glyphNrs){
        float totalWidth = 0;
        for (int nr: glyphNrs){
            totalWidth += charGlyphs[nr].getDefaultwidth() + spaceBetweenChars;
        }
        return totalWidth;
    }
    public static void drawText(List<Integer> glyphNrs, float midX, float midY, float totalWidth, float factorDefaultSize){

        totalWidth *= factorDefaultSize;
        float leftOfText = midX - totalWidth/2f; //het is het handigste om glyphs te tekenen vanuit de linkerbovenhoek wegens dat ze soms langs onder uitsteken
        float topOfText = midY + charGlyphs[0].getDefaultheight()*factorDefaultSize/2; //glyph a heeft net als de meeste glyphs als defaultheight 57

        //het drawen zelf
        for (int nr: glyphNrs){
            graphics.drawGlyph(charGlyphs[nr], leftOfText, topOfText, factorDefaultSize);
            leftOfText += (charGlyphs[nr].getDefaultwidth() +  spaceBetweenChars)*factorDefaultSize;
        }
    }
}

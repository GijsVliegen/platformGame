package resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import game.Collision;


public class ImageToMatrix {
    public static List<String> levelNames = new ArrayList<>();
    public static List<byte[]> byteArrays = new ArrayList<>();
    public static List<Collision[]> levels = new ArrayList<>();
    public static void init() throws IOException{
        levelNames.add("level1.jpg");
        for (String s: levelNames){
            byteArrays.add(toByteArray(s));
        }
        for (int k = 0; k < byteArrays.get(0).length; k++){
            System.out.println(byteArrays.get(0)[k]);
        }
        for(byte[] b: byteArrays){
            levels.add(toCollisions(b));
        }
        //BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
        //ImageIO.write(imag, "jpg", new File(dirName,"snap.jpg"));
    }
    private static Collision[] toCollisions(byte[] b){
        return null;
    }
    public static byte[] toByteArray(String s) throws IOException {

        String dirName="C:\\";
        ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
        BufferedImage img=ImageIO.read(new File(dirName,s));
        ImageIO.write(img, "jpg", baos);
        baos.flush();

        String base64String=Base64.encode(baos.toByteArray());
        baos.close();

        return Base64.decode(base64String);
    }
}

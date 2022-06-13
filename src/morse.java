
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class morse {

    public static void main(String[] args) {
        BufferedReader reader;
        int height = 0;
        int width = 0;
        char a[][] = new char[400][40];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag3.txt"));

            String line = reader.readLine();
            int i = 0;
            width = line.length();
            while (line != null) {
                a = convert(a, line, i);
                // read next line
                line = reader.readLine();
                i++;
            }
            height = i;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger one = BigInteger.valueOf(solve(a, height, width, 1, 1));
        BigInteger two = BigInteger.valueOf(solve(a, height, width, 1, 3));
        BigInteger thr = BigInteger.valueOf(solve(a, height, width, 1, 5));
        BigInteger fou = BigInteger.valueOf(solve(a, height, width, 1, 7));
        BigInteger fiv = BigInteger.valueOf(solve(a, height, width, 2, 1));
        System.out.println(one.multiply(two).multiply(thr).multiply(fou).multiply(fiv));

    }
    public static char[][] convert(char[][] d, String a, int c){
        char b[] = new char[a.length()];
        for(int i = 0; i < a.length(); i++){
            d[c][i] = a.charAt(i);
        }
        return d;
    }
    public static int solve(char[][] a, int h, int w, int slopeY, int slopeX){
        int x = 0;
        int y = 0;
        int nr = 0;
        while(y < h){
            if (a[y][x] == '#'){
                nr++;
            }
            y = y + slopeY;
            x = (x + slopeX)%w;
        }
        System.out.println(nr);
        return nr;
    }
}

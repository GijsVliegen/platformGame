package aoc;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class aocDag9{

    public static void main(String[] args) {
        BufferedReader reader;
        long lines[] = new long[1000];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag9.txt"));
            String line = reader.readLine();
            int lr = 0;
            //for(int z = 0; z < 20; z++){
            while (line != null && !line.trim().isEmpty()) {
                lines[lr++] = Long.parseLong(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long find = 0;
        boolean bool = false;
        for (int i = 25;i < 1000; i++){
            bool = adds(lines, i);
            if(!bool){
                find = lines[i];
                break;
            }
        }
        find(lines, find);
        System.out.println("hoi");
    }
    public static void find(long[] a, long find){
        int sum = 0;
        for (int i = 0; i < a.length; i++){
            for (int b = i+2; b < a.length; b++){
                sum = 0;
                for (int c = i; c < b; c++){
                    sum += a[c];
                }
                if (sum == find){
                    long min = a[i];
                    long max = a[i];
                    for (int c = i; c < b; c++){
                        if (a[c] < min)
                            min = a[c];
                        if (a[c] > max)
                            max = a[c];
                    }
                    System.out.println(min+max);
                }
            }
        }
    }
    public static boolean adds(long[] a, int b){
        for (int i = b-25; i < b; i++) {
            for (int j = i; j < b; j++) {
                if (a[i] + a[j] == a[b])
                    return true;
            }
        }
        return false;
    }
}
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

public class aocDag11{

    public static void main(String[] args) {
        BufferedReader reader;
        char grid[][] = new char[96][101];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag11.txt"));
            String line = reader.readLine();

            //for(int z = 0; z < 20; z++){
            int i = 0;
            while (line != null && !line.trim().isEmpty()) {
                grid[i++] = line.toCharArray();
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printgrid(grid);
        solveOne(grid);
        System.out.println("hoi");
    }
    public static void printgrid(char[][] a){
        StringBuilder b;
        for (int i = 0; i < a.length; i++) {
            b = new StringBuilder();
            for (int j = 0; j < a[i].length; j++) {
                b.append(a[i][j]);
            }
            System.out.println(b.toString());
        }
    }
    public static void solveOne(char[][] a){
        int nrSteps = 0;
        int change = 1;
        while(change > 0) {
            nrSteps++;
            change = 0;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    if ((a[i][j] == '#' && shouldLeave(a, i ,j)) || (a[i][j] == 'L' && shouldSit(a, i, j))){
                        change(a, i, j);
                        change++;
                    }
                }
            }
            changeDef(a);
        }

        System.out.println("nr of steps = " + nrSteps);
    }
    public static int boolToInt(Boolean b) {
        return b.compareTo(false);
    }

    public static boolean shouldSit(char[][] a, int x, int y){
        int i, j;
        for (i = -boolToInt(x != 0); i < 1 + boolToInt(x != a.length-1); i++){
            for (j = -boolToInt(y != 0); j < 1 + boolToInt(y != a[x].length -1); j++){
                if (a[x+i][y+j] == '#' || a[x+i][y+j] == 'P'){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean shouldLeave(char[][] a, int x, int y) {
        int count = 0;
        for (int i = -boolToInt(x != 0); i < 1 + boolToInt(x != a.length-1); i++){
            for (int j = -boolToInt(y != 0); j < 1 + boolToInt(y != a[x].length-1); j++){
                if (a[x+i][y+j] == '#' || a[x+i][y+j] == 'P'){
                    count++;
                }
            }
        }
        return count > 4;
    }
    public static void change(char[][] a, int i, int j){
        if (a[i][j] == '#'){
            a[i][j] = 'P';
        }
        else if (a[i][j] == 'L'){
            a[i][j] = 'D';
        }
    }
    public static void changeDef(char[][] a){
        for (int i = 0; i < a.length;i++){
            for (int j = 0; j < a[0].length; j++){
                if (a[i][j] == 'P') {
                    a[i][j] = 'L';
                }
                else if (a[i][j] == 'D'){
                    a[i][j] = '#';
                }
            }
        }
    }
}
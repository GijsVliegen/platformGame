package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aocDag5 {

    public static void main(String[] args) {
        BufferedReader reader;

        int max = 0;
        int allSeats[] = new int[920];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag5.txt"));

            String line = reader.readLine();
            int row = 0;
            int column = 0;
            int i = 0;
            //for(int z = 0; z < 40; z++){
            while (line != null && !line.trim().isEmpty()) {
                System.out.println(line);
                row = getRow(line);
                column = getColumn(line);
                allSeats[i] = row*8 + column;
                if (row*8 + column > max){
                    System.out.println("row = " + String.valueOf(row));
                    System.out.println("column = " + String.valueOf(column));
                    max = row*8 + column;
                }
                i++;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.sort(allSeats);
        findSeat(allSeats);
        System.out.println(max);
    }
    public static void findSeat(int[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i] - a[i-1] != 1){
                System.out.println(a[i]);
            }
        }
    }
    public static int getRow(String a){
        int row = 0;
        for (int i = 0; i < 7; i++){
            if (a.charAt(i) == 'B'){
                row += java.lang.Math.pow(2, 6-i);
            }
        }
        return row;
    }
    public static int getColumn(String a){
        int column = 0;
        for (int i = 7; i < 10; i++){
            if (a.charAt(i) == 'R'){
                column += java.lang.Math.pow(2, 9-i);
            }
        }
        return column;
    }
}
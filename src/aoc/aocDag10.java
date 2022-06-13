package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class aocDag10{

    public static void main(String[] args) {
        BufferedReader reader;
        List a = new ArrayList<Integer>();
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag10.txt"));
            a = reader.lines().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Comparator<Integer> c = new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a-b;
            }
        };
        a.sort(c);
        //solveOne(a);
        solveTwo(a);
    }

    public static void solveTwo(List a){
        Object values[] = new Object[a.size() + 2];
        values[0] = 0;
        values[a.size() + 1] = (int)a.get(a.size()-1) + 3;
        System.arraycopy(a.toArray(), 0, values, 1, a.size());
        long Dynamic[] = new long[values.length];
        Dynamic[values.length-1] = 1;
        for (int i = values.length-2; i > -1; i--){
            long nr = 0;
            if ((int)values[i+1] - (int)values[i] < 4){
                nr += Dynamic[i+1];
                if (i < values.length - 3 && (int)values[i+2] - (int)values[i] < 4){
                    nr += Dynamic[i+2];
                    if (i < values.length - 4 && (int)values[i+3] - (int)values[i] < 4){
                        nr += Dynamic[i+3];
                    }
                }
            }
            Dynamic[i] = nr;
        }

        for(int i = 0;i < Dynamic.length; i++){

            System.out.println(Dynamic[i]);
        }
        System.out.println(Dynamic[0]);

    }
    public static void solveOne(List a){
        Iterator i = a.iterator();
        int one = 0, three = 0;
        int previous = 0;   //moet 0 zijn, is van de outlet, dus eerste verschil van 1 telt ook mee
        while(i.hasNext()){
            int now = (int)i.next();

            System.out.println(now);
            if (now - previous == 1) {
                one++;
            }
            else if (now - previous == 3){
                three++;
            }
            previous = now;
        }
        three++; //van laatste adaptor naar device is een verschil van 3, dus nog eentje optellen
        System.out.println("een = " + one);
        System.out.println("drie = "  + three);
        System.out.println(one*three);
    }
}
package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aocDag8{

    public static void main(String[] args) {
        BufferedReader reader;
        String lines[] = new String[620];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag8.txt"));

            String line = reader.readLine();
            int lr = 0;
            //for(int z = 0; z < 20; z++){
            while (line != null && !line.trim().isEmpty()) {
                lines[lr++] = line;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean bool = false;
        for (int i = 0; i < lines.length; i++){
            if (lines[i] != null){
                String instruction = lines[i].substring(0, 3);
                if (instruction.equals("jmp")){
                    lines[i] = "nop" + lines[i].substring(3);
                    bool = operatingSystem(lines);
                    lines[i] = "jmp" + lines[i].substring(3);
                }
                else if (instruction.equals("nop")) {
                    lines[i] = "jmp" + lines[i].substring(3);
                    bool = operatingSystem(lines);
                    lines[i] = "nop" + lines[i].substring(3);
                }
            }
            if(bool){
                System.out.println(i);
                break;
            }
        }
        System.out.println("hoi");
    }
    public static boolean operatingSystem(String a[]){
        int ctr = 0;
        int glob = 0;
        boolean loop = false;
        ArrayList<Integer> visited = new ArrayList<Integer>();
        while(!(loop || a[ctr] == null || a[ctr].trim().isEmpty())){
            if (visited.contains(ctr)){
                loop = true;
            }
            visited.add(ctr);
            System.out.println("ctr : " + ctr);
            System.out.println("glob : " + glob);
            System.out.println(a[ctr]);
            String instruction = a[ctr].substring(0, 3);
            if (instruction.equals("acc")) {
                if (a[ctr].charAt(4) == '+') {
                    glob += Integer.parseInt(a[ctr].substring(5));

                } else {
                    glob -= Integer.parseInt(a[ctr].substring(5));
                }
                ctr++;
            }
            if (instruction.equals("jmp")) {

                if (a[ctr].charAt(4) == '+') {
                    ctr += Integer.parseInt(a[ctr].substring(5));
                } else {
                    ctr -= Integer.parseInt(a[ctr].substring(5));
                }
            }
            if(instruction.equals("nop"))
                ctr++;
        }
        if (a[ctr] == null ||a[ctr].trim().isEmpty()){
            System.out.println(glob);
        }
        return a[ctr] == null || a[ctr].trim().isEmpty();
    }
}
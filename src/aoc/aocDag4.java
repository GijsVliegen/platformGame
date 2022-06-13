package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aocDag4{

    public static void main(String[] args) {
        BufferedReader reader;
        int validInt = 0;
        char a[][] = new char[400][40];
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag4.txt"));

            String line = reader.readLine();
            Hashtable<String, String> passport = new Hashtable<String, String>();
            String EachLine[];
            String Eachkey[];
            //for(int z = 0; z < 40; z++){
            while (line != null) {
                if (line.trim().isEmpty()){
                    if (passport.size() == 8 || (passport.size() == 7 && !passport.containsKey("cid"))) {
                        if (second_val(passport)) {
                            validInt++;
                            printPassport(passport);
                        }
                    }
                    passport = new Hashtable<String, String>();
                }
                else {
                    EachLine = line.split(" ");
                    for (int j = 0; j < EachLine.length; j++) {
                        Eachkey = EachLine[j].split(":");
                        passport.put(Eachkey[0], Eachkey[1]);
                    }
                }
                line = reader.readLine();
            }
            if (passport.size() == 8 || (passport.size() == 7 && !passport.containsKey("cid"))) {
                if (second_val(passport)) {
                    validInt++;
                    printPassport(passport);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(validInt);

    }
    public static void printPassport(Hashtable<String, String> a){
        Enumeration names = a.keys();
        while(names.hasMoreElements()) {
            String key = (String) names.nextElement();
            System.out.println(key + ": " + a.get(key));
        }
        System.out.println("");
    }
    /* a heeft fields:
        byr (tussen 1920 en 2002)
        iyr (tussen 2010 en 2020)
        eyr (tussen 2020 en 2030)
        hgt (tussen 150cm en 193cm of tussen 59in en 76 in)
        hcl (een # gevolgd door 6 karakters 0-9 a-f)
        ecl (een van volgende: amb blu brn gry grn hzl oth)
        pid (9 nummers)
        cid (ignored)
     */
    public static boolean second_val(Hashtable<String, String> a){
        int byr = Integer.parseInt(a.get("byr"));
        int iyr = Integer.parseInt(a.get("iyr"));
        int eyr = Integer.parseInt(a.get("eyr"));
        if (byr >= 1920 && byr <= 2020 && iyr >= 2010
            && iyr <= 2020 && eyr >= 2020 && eyr <= 2030){
            if(checkhgt(a.get("hgt"))) {
                if (checkhcl(a.get("hcl"))) {
                    if (checkecl(a.get("ecl"))) {
                        System.out.println("till ecl");
                        if (checkpid(a.get("pid"))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkpid(String a){
        ArrayList<String> validCh = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6",
                "7" ,"8", "9"));
        System.out.println(a);
        int i;
        for (i = 0; i < a.length(); i++){
            if (i > 8){
                System.out.println("to high i");
                return false;
            }
            if (!validCh.contains(String.valueOf(a.charAt(i)))){
                System.out.println("wrong char at " + String.valueOf(i));
                return false;
            }
        }
        return i == 9;
    }
    public static boolean checkecl(String a){
        return a.equals("amb") || a.equals("blu") || a.equals("gry")
                || a.equals("hzl") || a.equals("grn") || a.equals("brn") || a.equals("oth");
    }
    public static boolean checkhcl(String a){
        ArrayList<String> validCh = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6",
                "7" ,"8", "9", "a", "b", "c", "d", "e", "f"));
        if (a.charAt(0) == '#'){
            for (int i = 1; i < a.length(); i++){
                if (i > 6){
                    System.out.println("to high i");
                    return false;
                }
                if (!validCh.contains(String.valueOf(a.charAt(i))))
                    return false;
            }
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean checkhgt(String a){
        if (a.endsWith("cm")){
            int hgt = Integer.parseInt(a.substring(0, a.length() - 2));
            return hgt >= 150 && hgt <= 193;
        }
        else if (a.endsWith("in")){

            int hgt = Integer.parseInt(a.substring(0, a.length() - 2));
            return hgt >= 59 && hgt <= 76;

        }
        return false;
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

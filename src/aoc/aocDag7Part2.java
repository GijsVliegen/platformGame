package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aocDag7Part2 {

    public static void main(String[] args) {
        BufferedReader reader;
        Dictionary<String, ArrayList<String>> d = new Hashtable<String, ArrayList<String>>();
        try {
            reader = new BufferedReader(new FileReader(
                    "/home/gijs/Downloads/inputAocDag7.txt"));

            String line = reader.readLine();
            String parts[];
            String bag;
            String otherBags[];
            //for(int z = 0; z < 20; z++){
            while (line != null && !line.trim().isEmpty()) {
                parts = line.split("contain");
                bag = parts[0];
                bag = bag.substring(0, bag.length() - 1);
                otherBags = parts[1].split(",");
                String nr;
                for (int i = 0; i < otherBags.length; i++) {
                    otherBags[i] = otherBags[i].substring(1);
                    if (otherBags[i].endsWith(".")){
                        otherBags[i] = otherBags[i].substring(0, otherBags[i].length() - 1);
                    }
                    if (otherBags[i].endsWith("g")) {
                        otherBags[i] += "s";
                    }
                    if (otherBags[i].startsWith("n")) {
                        otherBags[i] = "0 " + otherBags[i];
                    }
                }
                ArrayList<String> bags = new ArrayList<String>();
                for (int i = 0; i < otherBags.length; i++){
                    bags.add(otherBags[i]);
                }
                d.put(bag, bags);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(d.size());
        long solution = solve(d, "shiny gold bags");
        //printEverything(d);
        System.out.println(solution);
    }
    private static ArrayList<String> getDoublesWay(ArrayList<String> a){
        Iterator i = a.iterator();
        ArrayList<String> value = new ArrayList<>();
        while(i.hasNext()){
            String current = (String)i.next();
            if (!value.contains(current)){
                value.add(current);
            }
        }
        return value;
    }
    private static long solve(Dictionary<String, ArrayList<String>> a, String b){
        long output = 0;
        ArrayList<String> innerBags = a.get(b);
        if (innerBags == null){
            System.out.println(b);
            return 0;
        }
        int nr;
        Iterator i = innerBags.iterator();
        while(i.hasNext()){
            String g = (String)i.next();
            nr = Integer.parseInt(String.valueOf(g.charAt(0)));
            long solve = solve(a, g.substring(2));
            //System.out.println(solve);
            //System.out.println(nr);

            output += nr + nr * solve;
        }
        return output;
    }
    private static void printEverything(Dictionary<String, ArrayList<String>> a){
        Enumeration e = a.keys();
        while(e.hasMoreElements()){
            String key = (String)e.nextElement();
            String output = key + " contains : ";
            ArrayList<String> otherbagContainer = a.get(key);
            Iterator i = otherbagContainer.iterator();
            while(i.hasNext()){
                String bag = (String) i.next();
                output += bag + ", ";
            }
            System.out.println((output));
        }

    }

}
package aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aocDag7 {

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
                for (int i = 0; i < otherBags.length; i++) {
                    otherBags[i] = otherBags[i].substring(3);
                    if (otherBags[i].endsWith(".")){
                        otherBags[i] = otherBags[i].substring(0, otherBags[i].length() - 1);
                    }
                    if (otherBags[i].endsWith("g")) {
                        otherBags[i] += "s";
                    }
                }
                for (int i = 0; i < otherBags.length; i++){
                    ArrayList<String> otherbagContainer = d.get(otherBags[i]);
                    if (otherbagContainer == null)
                        otherbagContainer = new ArrayList<String>();
                    otherbagContainer.add(bag);
                    d.put(otherBags[i], otherbagContainer);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(d.size());
        ArrayList<String> solution = solve(d, "shiny gold bags");
        printEverything(d);
        ArrayList<String> betterSol = getDoublesWay(solution);
        System.out.println(betterSol.size());
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
    private static ArrayList<String> solve(Dictionary<String, ArrayList<String>> a, String b){
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> otherbagContainer = a.get(b);
        if (otherbagContainer == null){
            System.out.println(b);
            return new ArrayList<>();
        }
        Iterator i = otherbagContainer.iterator();
        while(i.hasNext()){
            String g = (String)i.next();
            output.add(g);
            output.addAll(solve(a, g));
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
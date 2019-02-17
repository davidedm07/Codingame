package java;

import java.util.HashMap;
import java.util.Scanner;

public class ConwaySequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // original number of the sequence
        int L = in.nextInt(); // number of the line to display

        String currentLine = R + "";
        boolean multipleCiphers = false;
        int numCiphers = currentLine.length();
        if (currentLine.length() > 1)
            multipleCiphers = true;

        for (int i = 1; i < L; i++) {
            System.err.println("LINE " + i + " : " + currentLine);
            currentLine = generateLine(currentLine, multipleCiphers, R, numCiphers);

        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(currentLine);
    }

    private static String generateLine(String l, boolean multipleCiphers, int n, int ciphers) {

        if (l.isEmpty())
            return "";

        if (l.length() == 1 && !multipleCiphers)
            return "1 " + l.charAt(0);
        else if (multipleCiphers && l.length() == ciphers)
            return "1 " + n;


        if (multipleCiphers) {
            l = l.replace(n + "", "");
        }

        String toProcess = l.replaceAll(" ", "");
        //System.err.println("To Process: " + toProcess);
        StringBuilder sb = new StringBuilder();
        char previous = toProcess.charAt(0);
        HashMap<Character, Integer> charCounts = new HashMap<>();
        charCounts.put(previous, 1);
        for (int i = 1; i < toProcess.length(); i++) {
            char x = toProcess.charAt(i);
            //System.err.println("X " + x);
            if (x != previous) {
                sb.append(charCounts.get(previous) + " " + previous + " ");
                charCounts.put(previous, 0);
                charCounts.put(x, 1);
            } else {
                if (charCounts.containsKey(x))
                    charCounts.put(x, charCounts.get(x) + 1);
                else
                    charCounts.put(x, 1);
            }
            previous = x;
        }
        //System.err.println("MAP: "+ charCounts);
        String result = sb.append(charCounts.get(previous) + " " + previous).toString();

        if (multipleCiphers)
            result += " 1 " + n;
        return result;

    }
}
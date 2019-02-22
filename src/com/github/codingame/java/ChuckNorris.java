package com.github.codingame.java;

import java.util.Scanner;

public class ChuckNorris {

    public static void main(String[] args) {

        //get data
        Scanner in = new Scanner(System.in);
        char[] message = in.nextLine().toCharArray();

        //convert data into binary format
        StringBuilder binary = new StringBuilder();
        for (char c : message) {
            String res = Integer.toBinaryString(c);

            //for non-letters
            while (res.length() < 7) res = '0' + res;

            binary.append(res);
        }

        //convert to unary and print to console
        int i = 0;
        char currentChar;
        while (i < binary.length()) {
            if (binary.charAt(i) == '0') {
                System.out.print("00 ");
                currentChar = '0';
            } else {
                System.out.print("0 ");
                currentChar = '1';
            }
            while (binary.charAt(i) == currentChar) {
                System.out.print("0");
                i++;
                if (i >= binary.length()) break;
            }
            if (i < binary.length()) System.out.print(" ");
        }
        in.close();
    }
}

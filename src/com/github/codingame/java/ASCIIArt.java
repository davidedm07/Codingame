package com.github.codingame.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ASCIIArt {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        in.nextLine();
        int H = in.nextInt();
        in.nextLine();
        String T = in.nextLine();
        List<String> ascii = new ArrayList<>();
        int group = 0;

        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            int beginIndex = 0;
            int endIndex = L - 1;
            group = ROW.length() / L;
            for (int j = 0; j < group; j++) {
                ascii.add(ROW.substring(beginIndex, endIndex + 1));
                beginIndex = endIndex + 1;
                endIndex = beginIndex + (L - 1);
            }
        }

        char[] cArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?".toCharArray();
        List<Character> letters = new ArrayList<>();
        for (char c : cArray) {
            letters.add(c);
        }
        T = T.toUpperCase();
        char[] ch = T.toCharArray();
        List<Integer> indexes = new ArrayList<>();
        for (char c : ch) {
            if (!letters.contains(c))
                indexes.add(26);
            else
                indexes.add(letters.indexOf(c));
        }
        printSolution(indexes, H, ascii, group);
    }

    private static void printSolution(List<Integer> indexes, int charHigth, List<String> ascii, int group) {
        int pointer = 0;
        for (int i = 0; i < charHigth; i++) {
            StringBuilder answer = new StringBuilder();
            for (int index : indexes) {
                index += pointer;
                answer.append(ascii.get(index));
            }
            pointer = pointer + group;
            System.out.println(answer.toString());
        }
    }
}

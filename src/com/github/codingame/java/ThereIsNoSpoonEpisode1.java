package com.github.codingame.java;

import java.util.Scanner;

public class ThereIsNoSpoonEpisode1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.err.print(grid[i][j] + " ");
            }
            System.err.println();
        }
        System.err.println(width + " " + height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '0') {
                    String result = "" + (j) + " " + (i) + " ";
                    int i1 = i + 1;
                    int j1 = j + 1;
                    while (i1 < height) {
                        if (grid[i1][j] == '0') {
                            break;
                        }
                        i1++;
                    }
                    while (j1 < width) {
                        if (grid[i][j1] == '0') {
                            break;
                        }
                        j1++;
                    }
                    if (j1 >= width) {
                        result = result + "-1 -1 ";
                    } else {
                        result = result + (j1) + " " + (i) + " ";
                    }
                    if (i1 >= height) {
                        result = result + "-1 -1";
                    } else {
                        result = result + (j) + " " + (i1) + "";
                    }

                    System.out.println(result);
                }
            }
        }
    }
}

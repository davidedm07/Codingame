package com.github.codingame.java;

import java.util.Scanner;

public class ShadowsOfTheNightEpisode1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        int xMax = W;
        int yMax = H;
        int xMin = -1;
        int yMin = -1;

        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.err.println(bombDir);

            if (bombDir.equals("UR")) {
                xMin = X0;
                yMax = Y0;
                X0 = xMin + (xMax - xMin) / 2;
                Y0 = yMax - (yMax - yMin) / 2;
            }
            if (bombDir.equals("R")) {
                xMin = X0;
                X0 = xMin + (xMax - xMin) / 2;
            }

            if (bombDir.equals("U")) {
                yMax = Y0;
                Y0 = yMax - (yMax - yMin) / 2;
            }

            if (bombDir.equals("DR")) {
                yMin = Y0;
                xMin = X0;
                X0 = xMin + (xMax - xMin) / 2;
                Y0 = yMin + (yMax - yMin) / 2;
            }
            if (bombDir.equals("DL")) {
                xMax = X0;
                yMin = Y0;
                X0 = xMax - (xMax - xMin) / 2;
                Y0 = yMin + (yMax - yMin) / 2;
            }
            if (bombDir.equals("L")) {
                xMax = X0;
                X0 = xMax - (xMax - xMin) / 2;
            }

            if (bombDir.equals("D")) {
                yMin = Y0;
                Y0 = yMin + (yMax - yMin) / 2;
            }

            if (bombDir.equals("UL")) {
                yMax = Y0;
                xMax = X0;
                X0 = xMax - (xMax - xMin) / 2;
                Y0 = yMax - (yMax - yMin) / 2;
            }


            // the location of the next window Batman should jump to.
            System.out.println(X0 + " " + Y0);
        }
    }
}

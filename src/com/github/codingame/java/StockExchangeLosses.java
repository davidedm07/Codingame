package com.github.codingame.java;

import java.util.Scanner;

public class StockExchangeLosses {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean maxSet = false;
        int max = 0;
        int currentDrop = 0;
        boolean isDropping;
        int previous = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (!maxSet) {
                max = v;
                maxSet = true;
                previous = v;
            } else {

                isDropping = v < previous;

                previous = v;
                if (v < max && isDropping) {
                    int diff = max - v;
                    if (diff > currentDrop)
                        currentDrop = diff;
                } else if (v > max && !isDropping)
                    max = v;

            }

        }

        System.out.println((currentDrop > 0) ? "-" + currentDrop : 0);

    }
}

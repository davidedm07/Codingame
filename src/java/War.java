package java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class War {

    /**
     * Player 1 : 10D 9S 8D KH 7D 5H 6S
     * Player 2 : 10H 7H 5C QC 2C 4H 6D
     * Then after one game turn, it will be:
     * Player 1 : 5H 6S 10D 9S 8D KH 7D 10H 7H 5C QC 2C
     * Player 2 : 4H 6D
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int n = in.nextInt(); // the number of cards for player 1
        ArrayDeque<String> q1 = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            q1.add(cardp1);
        }
        int m = in.nextInt(); // the number of cards for player 2
        ArrayDeque<String> q2 = new ArrayDeque<>(m);
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            q2.add(cardp2);
        }

        System.err.println("Q1 " + q1.toString());
        System.err.println("Q2 " + q2.toString());
        System.err.println();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int numbOfRounds = 0;
        boolean draw = false;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            draw = play(q1, q2, new ArrayList<>(), new ArrayList<>());

            if (draw) {
                System.out.println("PAT");
                break;
            }

            numbOfRounds++;
        }
        if (!draw) {
            if (q1.isEmpty() && !q2.isEmpty())
                System.out.println("2 " + numbOfRounds);
            else if (!q1.isEmpty())
                System.out.println("1 " + numbOfRounds);
            else
                System.out.println("PAT");
        }
    }

    private static boolean play(ArrayDeque<String> q1, ArrayDeque<String> q2, ArrayList<String> c1Cards, ArrayList<String> c2Cards) {
        String c1 = q1.poll();
        String c2 = q2.poll();

        c1Cards.add(c1);
        c2Cards.add(c2);

        int winner = compareCards(c1, c2);
        ArrayDeque<String> winnerQueue;
        if (winner == 1)
            winnerQueue = q1;
        else if (winner == 2)
            winnerQueue = q2;
        else {
            for (int i = 0; i < 3; i++) {
                c1Cards.add(q1.poll());
                c2Cards.add(q2.poll());
            }
            String c14 = q1.peek();
            String c24 = q2.peek();
            if (c1Cards.contains(null) || c2Cards.contains(null) || c14 == null || c24 == null)
                return true;
            return play(q1, q2, c1Cards, c2Cards);
        }

        winnerQueue.addAll(c1Cards);
        winnerQueue.addAll(c2Cards);

        return false;
    }


    private static int compareCards(String c1, String c2) {
        int c1Val;
        int c2Val;

        HashMap<String, Integer> otherValues = new HashMap<>();
        otherValues.put("10", 10);
        otherValues.put("J", 11);
        otherValues.put("Q", 12);
        otherValues.put("K", 13);
        otherValues.put("A", 14);

        // removing type of the card
        c1 = c1.substring(0, c1.length() - 1);
        c2 = c2.substring(0, c2.length() - 1);

        if (c1.compareTo("2") >= 0 && c1.compareTo("9") <= 0)
            c1Val = Integer.parseInt(c1);
        else
            c1Val = otherValues.get(c1);

        if (c2.compareTo("2") >= 0 && c2.compareTo("9") <= 0)
            c2Val = Integer.parseInt(c2);
        else
            c2Val = otherValues.get(c2);

        if (c1Val > c2Val)
            return 1;
        else if (c1Val < c2Val)
            return 2;
        else return 0;
    }
}


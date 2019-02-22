package com.github.codingame.java;

import java.util.*;

public class DwarfsStandingOnTheShouldersOfGiants {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence

        HashMap<Integer, List<Integer>> nodeInfluences = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();

            if (!nodeInfluences.containsKey(x)) {
                List<Integer> arcs = new ArrayList<>();
                arcs.add(y);
                nodeInfluences.put(x, arcs);
            } else
                nodeInfluences.get(x).add(y);
        }

        List<Integer> distances = new ArrayList<>();

        for (int node : nodeInfluences.keySet()) {
            List<Integer> visited = new ArrayList<>();
            DFS(node, visited, 1, distances, nodeInfluences);
        }

        int maxLength = Collections.max(distances);
        // The number of people involved in the longest succession of influences
        System.out.println(maxLength);
    }

    private static void DFS(int node, List<Integer> visited, int length,
                            List<Integer> distances, HashMap<Integer, List<Integer>> arcs) {

        visited.add(node); //current node that has been visited
        // if I reach node pit add distance to array
        if (!arcs.containsKey(node)) {
            if (!distances.contains(length))
                distances.add(length);
            return;
        }

        // visit next neighbor
        for (int neighbor : arcs.get(node)) {
            if (!visited.contains(neighbor))
                DFS(neighbor, visited, length + 1, distances, arcs);
        }

    }
}

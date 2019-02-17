package java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SkynetRevolutionEpisode1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        HashMap<Integer, List<Integer>> arcsForNodes = new HashMap<>();
        List<Integer> gateways = new LinkedList<>();

        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            addToMap(arcsForNodes, N1, N2);
            addToMap(arcsForNodes, N2, N1);

        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(EI);
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            int nearestGatewayIndex = closeToGateway(arcsForNodes, gateways, SI);

            if (nearestGatewayIndex != -1)
                System.out.println(SI + " " + gateways.get(nearestGatewayIndex));
            else
                System.out.println(SI + " " + arcsForNodes.get(SI).get(0));
        }
    }

    private static void addToMap(HashMap<Integer, List<Integer>> arcsForNodes, int origin, int destination) {

        if (arcsForNodes.containsKey(origin))
            arcsForNodes.get(origin).add(destination);
        else {
            arcsForNodes.put(origin, new LinkedList<>());
            arcsForNodes.get(origin).add(destination);
        }
    }

    private static int closeToGateway(HashMap<Integer, List<Integer>> arcsForNodes, List<Integer> gateways, int currentPosition) {

        List<Integer> currentArcs = arcsForNodes.get(currentPosition);
        for (int arc : currentArcs) {
            int position = gateways.indexOf(arc);
            if (position != -1)
                return position;
        }
        return -1;

    }
}

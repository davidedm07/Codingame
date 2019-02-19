package java;

import java.util.*;

/**
 * The 9 rules of the new Bender system:
 * <p>
 * 1. Bender starts from the place indicated by the @ symbol on the map and heads SOUTH.
 * 2. Bender finishes his journey and dies when he reaches the suicide booth marked $.
 * 3. Obstacles that Bender may encounter are represented by # or X.
 * 4. When Bender encounters an obstacle, he changes direction using the following priorities: SOUTH, EAST, NORTH and WEST. So he first tries to go SOUTH, if he cannot, then he will go EAST, if he still cannot, then he will go NORTH, and finally if he still cannot, then he will go WEST.
 * 5. Along the way, Bender may come across path modifiers that will instantaneously make him change direction. The S modifier will make him turn SOUTH from then on, E, to the EAST, N to the NORTH and W to the WEST.
 * 6. The circuit inverters (I on map) produce a magnetic field which will reverse the direction priorities that Bender should choose when encountering an obstacle. Priorities will become WEST, NORTH, EAST, SOUTH. If Bender returns to an inverter I, then priorities are reset to their original state (SOUTH, EAST, NORTH, WEST).
 * 7. Bender can also find a few beers along his path (B on the map) that will give him strength and put him in “Breaker” mode. Breaker mode allows Bender to destroy and automatically pass through the obstacles represented by the character X (only the obstacles X). When an obstacle is destroyed, it remains so permanently and Bender maintains his course of direction. If Bender is in Breaker mode and passes over a beer again, then he immediately goes out of Breaker mode. The beers remain in place after Bender has passed.
 * 8. 2 teleporters T may be present in the city. If Bender passes over a teleporter, then he is automatically teleported to the position of the other teleporter and he retains his direction and Breaker mode properties.
 * 9. Finally, the space characters are blank areas on the map (no special behavior other than those specified above).
 */

public class BenderEpisode1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        //initial Bender's coordinates
        Point start = null;
        Point end = null;

        List<Point> teleporters = new ArrayList<>();
        BenderState benderState = new BenderState();

        //creation of the maze
        char[][] maze = new char[L][C];

        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            maze[i] = row.toCharArray();
            if (row.contains("@"))
                start = new Point(i, row.indexOf('@'));

            if (row.contains("T"))
                teleporters.add(new Point(i, row.indexOf('T')));

            if (row.contains("$"))
                end = new Point(i, row.indexOf('$'));
        }

        if (start == null)
            throw new RuntimeException("Missing start point!");

        if (end == null)
            throw new RuntimeException("Missing end point!");

        benderState.position = start;
        //starting exploration of the maze
        List<BenderState> states = new ArrayList<>();
        states.add(new BenderState(benderState)); // adding start state to the list of states
        StringBuilder result = new StringBuilder();
        boolean isLooping = false;

        while (!benderState.position.equals(end)) {

            benderState.position = move(benderState, maze);

            if (teleporters.contains(benderState.position))
                benderState.position = (teleporters.indexOf(benderState.position) == 0) ? teleporters.get(1) : teleporters.get(0);

            result.append(benderState.currentDirection);
            result.append("\n");

            if (maze[benderState.position.x][benderState.position.y] == 'S')
                benderState.currentDirection = "SOUTH";
            else if (maze[benderState.position.x][benderState.position.y] == 'N')
                benderState.currentDirection = "NORTH";
            else if (maze[benderState.position.x][benderState.position.y] == 'E')
                benderState.currentDirection = "EAST";
            else if (maze[benderState.position.x][benderState.position.y] == 'W')
                benderState.currentDirection = "WEST";

            if (benderState.changedMap)
                states = new ArrayList<>();

            if (states.contains(benderState)) {
                isLooping = true;
                break;
            }

            states.add(new BenderState(benderState));
        }

        if (!isLooping)
            System.out.println(result);
        else
            System.out.println("LOOP");
    }

    private static Point move(BenderState benderState, char[][] maze) {

        Point destination;
        destination = moveInDirection(benderState.currentDirection, benderState, maze);

        if (destination == null)
            for (String next : benderState.directions) {
                if (!next.equals(benderState.currentDirection))
                    destination = moveInDirection(next, benderState, maze);

                if (destination != null) {
                    benderState.currentDirection = next; //modify current direction
                    break;
                }
            }
        if (destination == null)
            throw new RuntimeException("No Possible destinations");


        if (maze[destination.x][destination.y] == 'B')
            benderState.breakerMode = !benderState.breakerMode;

        else if (maze[destination.x][destination.y] == 'I') {
            benderState.invertedCircuit = !benderState.invertedCircuit;
            Collections.reverse(benderState.directions);
        }

        return destination;
    }

    private static Point moveInDirection(String direction, BenderState benderState, char[][] maze) {
        Point destination;
        switch (direction) {
            case "SOUTH":
                destination = new Point(benderState.position.x + 1, benderState.position.y);
                break;
            case "EAST":
                destination = new Point(benderState.position.x, benderState.position.y + 1);
                break;
            case "WEST":
                destination = new Point(benderState.position.x, benderState.position.y - 1);
                break;
            case "NORTH":
                destination = new Point(benderState.position.x - 1, benderState.position.y);
                break;
            default:
                throw new RuntimeException("Not recognized direction");
        }

        if (maze[destination.x][destination.y] == 'X') {
            if (benderState.breakerMode) {
                maze[destination.x][destination.y] = ' ';
                benderState.changedMap = true;
                return destination;
            } else return null;
        }

        if (maze[destination.x][destination.y] == '#')
            return null;

        return destination;
    }

}

class BenderState {
    boolean breakerMode = false;
    boolean invertedCircuit = false;
    boolean changedMap = false;
    String currentDirection = "SOUTH";
    Point position;
    private String[] directionsPriorities = new String[]{"SOUTH", "EAST", "NORTH", "WEST"};
    List<String> directions = Arrays.asList(directionsPriorities);

    BenderState() {
    }

    BenderState(BenderState b) {
        breakerMode = b.breakerMode;
        invertedCircuit = b.invertedCircuit;
        currentDirection = b.currentDirection;
        directions = b.directions;
        position = b.position;
        changedMap = b.changedMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenderState that = (BenderState) o;
        return breakerMode == that.breakerMode &&
                changedMap == that.changedMap &&
                invertedCircuit == that.invertedCircuit &&
                Objects.equals(currentDirection, that.currentDirection) &&
                Objects.equals(position, that.position);
    }

    @Override
    public String toString() {
        return "BenderState{" +
                "breakerMode=" + breakerMode +
                ", invertedCircuit=" + invertedCircuit +
                ", currentDirection='" + currentDirection + '\'' +
                ", position=" + position +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(breakerMode, invertedCircuit, currentDirection, position);


    }
}


class Point {
    int x;
    int y;

    Point(int xCord, int yCord) {
        x = xCord;
        y = yCord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

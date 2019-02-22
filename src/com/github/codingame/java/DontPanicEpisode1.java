package com.github.codingame.java;

import java.util.HashMap;
import java.util.Scanner;

public class DontPanicEpisode1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        HashMap<Integer, Integer> elevatorForFloor = new HashMap<>();
        HashMap<Integer, Integer> exitFloorPos = new HashMap<>();
        exitFloorPos.put(exitFloor, exitPos);

        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevatorForFloor.put(elevatorFloor, elevatorPos);
        }

        int wrongDirection = 0;

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            System.err.println("Exit Pos = " + exitPos);
            System.err.println("Exit Floor = " + exitFloor);
            System.err.println("Clone Floor = " + cloneFloor);
            System.err.println("Clone Pos = " + clonePos);
            System.err.println("Direction = " + direction);

            if (cloneFloor == exitFloor && cloneFloor != -1) {
                if (clonePos <= exitPos && direction.equals("RIGHT")
                        || clonePos >= exitPos && direction.equals("LEFT"))
                    System.out.println("WAIT");

                else if (clonePos <= exitPos && direction.equals("LEFT")
                        || clonePos >= exitPos && direction.equals("RIGHT")) {
                    if (wrongDirection < 1 && cloneFloor == 0) {
                        System.out.println("WAIT");
                        wrongDirection++;
                    } else {
                        System.out.println("BLOCK");
                        wrongDirection = 0;
                    }
                }
            } else if (cloneFloor != exitFloor && cloneFloor != -1) {
                int elevatorPos = elevatorForFloor.get(cloneFloor);
                if (clonePos <= elevatorPos && direction.equals("RIGHT")
                        || clonePos >= elevatorPos && direction.equals("LEFT"))
                    System.out.println("WAIT");

                else if (clonePos <= elevatorPos && direction.equals("LEFT") ||
                        clonePos >= elevatorPos && direction.equals("RIGHT")) {
                    if (wrongDirection < 1 && cloneFloor == 0) {
                        System.out.println("WAIT");
                        wrongDirection++;
                    } else {
                        System.out.println("BLOCK");
                        wrongDirection = 0;
                    }
                }
            }

            if (direction.equals("NONE"))
                System.out.println("WAIT");

        }
    }
}

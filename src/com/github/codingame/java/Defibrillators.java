package com.github.codingame.java;

import java.util.HashMap;
import java.util.Scanner;

public class Defibrillators {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        String LAT = in.next();
        double userLon = Double.valueOf(LON.replace(",", "."));
        double userLat = Double.valueOf(LAT.replace(",", "."));
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        double minDistance = Double.MAX_VALUE;
        HashMap<Double, String> distances = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            String[] fields = DEFIB.split(";");
            String currentDefibrillator = fields[1];
            String currentLongitude = fields[4];
            String currentLatitude = fields[5];
            currentLongitude = currentLongitude.replace(",", ".");
            currentLatitude = currentLatitude.replace(",", ".");
            double latitude = Double.valueOf(currentLatitude);
            double longitude = Double.valueOf(currentLongitude);
            double x = Math.abs(userLon - longitude) * Math.cos((userLat + latitude) / 2);
            double y = Math.abs(latitude - userLat);
            double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371;
            distances.put(distance, currentDefibrillator);
            if (distance < minDistance)
                minDistance = distance;

        }

        System.out.println(distances.get(minDistance));
    }
}

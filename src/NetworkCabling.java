import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class House {

    int x;
    int y;

    House(int xC, int yC) {
        x = xC;
        y = yC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return x == house.x &&
                y == house.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "House{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class EnigmaMachine {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<House> houses = new ArrayList<>();
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        HashMap<Integer, Integer> moreFrequentY = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            House current = new House(X, Y);
            houses.add(current);
            xs.add(current.x);
            ys.add(current.y);

            if (moreFrequentY.containsKey(Y))
                moreFrequentY.put(Y, moreFrequentY.get(Y) + 1);
            else
                moreFrequentY.put(Y, 1);

        }
        System.err.println(houses.toString());
        int median;
        if (houses.size() == 1)
            System.out.println(0);
        else {
            long minDist = Collections.max(xs) - Collections.min(xs); // main cable length;
            Collections.sort(ys);
            if (ys.size() % 2 == 1)
                median = ys.get((ys.size() - 1) / 2);
            else
                median = (ys.get(ys.size() / 2) + ys.get(ys.size() / 2) - 1) / 2;
            Map.Entry<Integer, Integer> min = null;
            for (Map.Entry<Integer, Integer> entry : moreFrequentY.entrySet()) {

                if (min == null)
                    min = entry;
                else if (min.getValue() < entry.getValue())
                    min = entry;
            }
            int yMc = 0;
            if (min != null)
                yMc = (min.getValue() > 1) ? min.getKey() : median;
            for (House h : houses)
                if (h.y != yMc)
                    minDist += Math.abs(h.y - yMc);

            System.out.println(minDist);
        }

    }
}
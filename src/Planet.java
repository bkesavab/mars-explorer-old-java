import java.util.ArrayList;
import java.util.Random;

public class Planet {
    private ArrayList<ArrayList<Position>> map;
    private int rockCount;
    private int size;
    private Random rng = new Random();

    /**
     * Creates a square map of size * size. Randomly decides
     * whether to place ROCK, FREE or OBSTACLE.
     * OBSTACLEs are never neighbors to other OBSTACLEs.
     * @param size length of the side of the square map
     */
    public Planet(int size) {
        this.size = size;

        rockCount = 0;
        map = new ArrayList<ArrayList<Position>>();
        for (int i = 0; i < size; ++i) {
            ArrayList<Position> line = new ArrayList<Position>();
            map.add(line);
            for (int j = 0; j < size; ++j) {
                Position p = genPosition(i, j);
                line.add(p);
                if (p == Position.ROCK) {
                    rockCount += 1;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder repr = new StringBuilder();
        for (ArrayList<Position> line: map) {
            for (Position p: line) {
                repr.append(p);
                repr.append(" ");
            }
            repr.append("\n");
        }
        return repr.toString();
    }

    public int getRockCount() {
        return rockCount;
    }

    public int getSize() {
        return size;
    }

    private Position genPosition(int i, int j) {
        Position[] positions = Position.values();
        int max = canBeObstacle(i, j) ? positions.length : (positions.length - 1);
        int idx = rng.nextInt(max);
        return positions[idx];
    }

    /**
     * Look at map and decide if current position can be obstacle.
     */
    private boolean canBeObstacle(int i, int j) {
        int[] diffs = {-1, 0, 1};
        for (int x: diffs) {
            for (int y: diffs) {
                if (x == 0 && y == 0) {
                    continue;
                }
                try {
                    Position p = map.get(i + x).get(j + y);
                    if (p == Position.OBSTACLE) {
                        return false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    // Nothing.
                }
            }
        }
        return true;
    }
}

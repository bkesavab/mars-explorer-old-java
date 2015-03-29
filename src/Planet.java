import java.util.ArrayList;

public class Planet {
    private ArrayList<ArrayList<Position>> map;
    private int rockCount;
    private int size;

    /**
     * Creates a square map of size * size. Randomly decides
     * whether to place ROCK or FREE.
     * @param size length of the side of the square map
     */
    public Planet(int size) {
        this.size = size;

        rockCount = 0;
        map = new ArrayList<ArrayList<Position>>();
        for (int i = 0; i < size; ++i) {
            ArrayList<Position> line = new ArrayList<Position>();
            for (int j = 0; j < size; ++j) {
                Position p = Position.choice();
                line.add(p);
                if (p == Position.ROCK) {
                    rockCount += 1;
                }
            }
            map.add(line);
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
}

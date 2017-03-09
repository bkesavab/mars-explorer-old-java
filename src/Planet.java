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

        rockCount = 0;//Rocks on the planet
        map = new ArrayList<ArrayList<Position>>();
        //This for loop runs the size of the map
        for (int i = 0; i < size; ++i) {
            ArrayList<Position> line = new ArrayList<Position>();
            map.add(line);
            //This for loop also runs for the size of the map
            for (int j = 0; j < size; ++j) {
                //the i variable is the x, and the j variable is the y coordinate
                Position p = genPosition(i, j);
                line.add(p);//We add the generated object in the string line of the map
                if (p == Position.ROCK) {//If there is a rock on the position we got above then we add it to the counter
                    rockCount += 1;
                }
            }
        }
    }

    //We create the viewable map in the following method using strings
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

    //This method outputs the rockcount
    public int getRockCount() {
        return rockCount;
    }
    
    //This method outputs the size
    public int getSize() {
        return size;
    }

    //This method clears the corresponding x and y position on the map
    public void clearPosition(int x, int y) {
        map.get(x).set(y, Position.FREE);
    }

    //This method outputs what is in the corresponding x and y coridinates on the map
    public Position inspect(int x, int y) {
        return map.get(x).get(y);
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
        //The following for loops iterate for all x and y coordinates
        for (int x: diffs) {
            for (int y: diffs) {
                i//checking for displacement
                f (x == 0 && y == 0) {
                    continue;
                }
                try {
                    //The following statements check whether the inputted coordinates could be an obstacle
                    Position p = map.get(i + x).get(j + y);
                    if (p == Position.OBSTACLE) {
                        return false;
                           //We return false if the position has an object
                    }
                } catch (IndexOutOfBoundsException e) {
                    // Nothing.
                }
            }
        }
        return true;
    }
}

import java.util.ArrayList;
import java.util.Random;

public class ReactiveAgent {
    //Here we create the variables that will be used throughout the followinig methods
    private int x, y;//variables for coordinates
    private Planet planet;//planet object
    private int score = 0;//score keeping variable
    private boolean carrying = false;//This boolean variable will be used to determinte if the player is carrying an object or not
    private Random rng = new Random();//Here we create a random variable
    
    //Here we create a random variable
    public ReactiveAgent(Planet planet) {
        x = 0;
        y = 0;
        this.planet = planet;
    }

    //This method lets the agent pickup and object
    private void pickUp() {
        carrying = true;//Sets carrying boolean variable to true
        planet.clearPosition(x, y);//Removes the object from the corresponding position
    }

    //This method moves the planet along the Y axis
    private int getDiffX(Direction direction) {
        int diffX = 0;
        if (direction == Direction.LEFT) {
            diffX = 0;
        }
        if (direction == Direction.RIGHT) {
            diffX = 0;
        }
        if (direction == Direction.UP) {
            diffX = -1;
        }
        if (direction == Direction.DOWN) {
            diffX = 1;
        }
        return diffX;
    }

    //This method moves the planet along the X axis
    private int getDiffY(Direction direction) {
        int diffY = 0;
        if (direction == Direction.LEFT) {
            diffY = -1;
        }
        if (direction == Direction.RIGHT) {
            diffY = 1;
        }
        if (direction == Direction.UP) {
            diffY = 0;
        }
        if (direction == Direction.DOWN) {
            diffY = 0;
        }
        return diffY;
    }

    //This method updates the planets coordinates
    private void applyMove(Direction direction) {
        x += getDiffX(direction);
        y += getDiffY(direction);
    }

    //This method checks whether the player can move in the corresponding direction or not
    private boolean canMoveTo(int x, int y) {
        return !(x >= planet.getSize() || y >= planet.getSize() ||
                 x < 0 || y < 0 ||
                 planet.inspect(x, y) == Position.OBSTACLE);//We check this by seeing if there is an obstacle in the players path and by checking the borders of the map
    }

    public void play() {
        Position currentPos = planet.inspect(x, y);
        // If on a rock, pick it up.
        if (currentPos == Position.ROCK) {
            pickUp();
            return;
        }

        // What are the possible Positions I can go to?
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction direction: Direction.values()) {
            int nextX = x + getDiffX(direction);
            int nextY = y + getDiffY(direction);
            if (!canMoveTo(nextX, nextY)) {
                continue;
            }
            possibleDirections.add(direction);//Adds the possible movements into possible directions array
        }

        // Choose a Position at random and move there.
        Direction move = possibleDirections.get(rng.nextInt(possibleDirections.size()));
        applyMove(move);
    }
}

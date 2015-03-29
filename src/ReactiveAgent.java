import java.util.ArrayList;
import java.util.Random;

public class ReactiveAgent {
    private int x, y;
    private Planet planet;
    private int score = 0;
    private boolean carrying = false;
    private Random rng = new Random();

    public ReactiveAgent(Planet planet) {
        x = 0;
        y = 0;
        this.planet = planet;
    }

    private void pickUp() {
        carrying = true;
        planet.clearPosition(x, y);
    }

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

    private void applyMove(Direction direction) {
        x += getDiffX(direction);
        y += getDiffY(direction);
    }

    private boolean canMoveTo(int x, int y) {
        return !(x >= planet.getSize() || y >= planet.getSize() ||
                 x < 0 || y < 0 ||
                 planet.inspect(x, y) == Position.OBSTACLE);
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
            possibleDirections.add(direction);
        }

        // Choose a Position at random and move there.
        Direction move = possibleDirections.get(rng.nextInt(possibleDirections.size()));
        applyMove(move);
    }
}

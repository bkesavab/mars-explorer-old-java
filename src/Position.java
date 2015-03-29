import java.util.Random;

public enum Position {
    // The ordering is important. See Planet.genPosition.
    FREE, ROCK, OBSTACLE;

    public static Position choice() {
        Random rng = new Random();
        int index = rng.nextInt(Position.values().length);
        return Position.values()[index];
    }

    @Override
    public String toString() {
        if (this == FREE) {
            return " ";
        }
        if (this == ROCK) {
            return "*";
        }
        if (this == OBSTACLE) {
            return "X";
        }
        return "?";
    }
}

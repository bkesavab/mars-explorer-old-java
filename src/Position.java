import java.util.Random;

public enum Position {
    FREE, ROCK;

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
        return "?";
    }
}

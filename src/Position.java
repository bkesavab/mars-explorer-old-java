import java.util.Random;

public enum Position {
    // The ordering is important. See Planet.genPosition.
    FREE, ROCK, OBSTACLE;// Here we create objects that will be placed in the planet
    
    //This method chooses where the objectes created above should be placed
    public static Position choice() {
        Random rng = new Random();//Here we create a random value, to place the object in
        int index = rng.nextInt(Position.values().length);//Here we assign the value of the random value we created above to the index 
        return Position.values()[index];//We return the position coordinate to the callling statement
    }

    @Override//The following method returns the corresponding symbol for it's corresponding object in the planet.
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

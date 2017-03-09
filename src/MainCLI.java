public class MainCLI {
    public static void main(String[] args) {
        //Here we create a new Planet Object
        Planet planet = new Planet(10);
        ReactiveAgent agent = new ReactiveAgent(planet);

        int steps = 0;//This is a counter
        
        //This while loop runs as long as the steps varuable is under 100, and then quits the game
        while (steps < 100) {
            agent.play();//This command allows the player to play the game
            steps += 1;//This is a counter for steps taken
            System.out.println(planet);//Output return statement from Planet Class
        }
    }
}

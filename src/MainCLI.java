public class MainCLI {
    public static void main(String[] args) {
        Planet planet = new Planet(10);
        ReactiveAgent agent = new ReactiveAgent(planet);

        int steps = 0;
        while (steps < 100) {
            agent.play();
            steps += 1;
            System.out.println(planet);
        }
    }
}

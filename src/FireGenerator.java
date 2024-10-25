import java.util.Random;

public class FireGenerator implements Runnable {
    private char[][] forest;

    public FireGenerator(char[][] forest) {
        this.forest = forest;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }

            this.generateFire();
        }
    }

    public void generateFire() {
        Random rand = new Random();
        int firePosX = rand.nextInt(30);
        int firePosY = rand.nextInt(30);
        if (this.forest[firePosX][firePosY] != 'T') {
            this.forest[firePosX][firePosY] = '@';
            System.out.println("\nSTARTED FIRE AT: [" + firePosY + ", " + firePosX + "]\n");
        }

    }
}
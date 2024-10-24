import java.util.Random;

public class FireGenerator implements Runnable {
    private char[][] forest;

    public FireGenerator(char[][] forest) {
        this.forest = forest;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(6000L);
            } catch (InterruptedException var2) {
                InterruptedException e = var2;
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
            System.out.println("\nFIRE AT: [" + firePosY + ", " + firePosX + "]\n");
        }

    }
}
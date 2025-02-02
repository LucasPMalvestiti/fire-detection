public class Main {
    public static Forest forest = new Forest();
    public static ControlCenter controlCenter;
    public static SensorNode[][] sensorNodes = new SensorNode[Forest.SIZE][Forest.SIZE];

    public Main() {
    }

    public static void main(String[] args) {
        controlCenter = new ControlCenter(sensorNodes);
        forest.initializeForest(controlCenter);
        startFire();
    }

    private static synchronized void displayForest() {
        displayColumns();

        for(int i = 0; i < 30; ++i) {
            if (String.valueOf(i).length() == 1) {
                System.out.print("0" + i + "  ");
            } else {
                System.out.print("" + i + "  ");
            }

            for(int j = 0; j < 30; ++j) {
                System.out.print("  " + Forest.getForest()[i][j]);
            }

            System.out.println();
        }

    }

    private static void displayColumns() {
        System.out.print("    ");

        int i;
        for(i = 0; i < 10; ++i) {
            System.out.printf("  " + i);
        }

        for(i = 10; i < 30; ++i) {
            System.out.printf(" " + i);
        }

        System.out.println();
        System.out.println();
    }

    private static void startFire() {
        FireGenerator fireGenerator = new FireGenerator(Forest.getForest());
        (new Thread(fireGenerator)).start();

        while(true) {
            displayForest();
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
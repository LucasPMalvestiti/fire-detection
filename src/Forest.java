public class Forest {
    public static final int SIZE = 30;
    public static final char EMPTY = '-';
    public static final char SENSOR = 'T';
    public static final char FIRE = '@';
    public static final char BURNED = '/';
    public static char[][] forest = new char[30][30];
    private SensorNode[][] sensor = new SensorNode[30][30];

    public Forest() {
    }

    public void initializeForest(ControlCenter controlCenter) {
        for(int i = 0; i < 30; ++i) {
            for(int j = 0; j < 30; ++j) {
                if (i % 3 == 1 && j % 3 == 1) {
                    forest[i][j] = 'T';
                    boolean isBorder = i == 1 || i == 28 || j == 1 || j == 28;
                    this.sensor[i][j] = new SensorNode(i, j, forest, isBorder, controlCenter, this.sensor);
                    (new Thread(this.sensor[i][j])).start();
                } else {
                    forest[i][j] = '-';
                }
            }
        }

    }

    public static char[][] getForest() {
        return forest;
    }
}
public class ControlCenter {
    public ControlCenter() {
    }

    public synchronized void fightFire(int firePosX, int firePosY) {
        System.out.println("FIRE FOUND AT: [" + firePosX + ", " + firePosY + "], PUTTING IT OUT");
        Forest.getForest()[firePosX][firePosY] = '/';
    }
}

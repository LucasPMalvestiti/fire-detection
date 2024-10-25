public class ControlCenter {

    private SensorNode[][] sensors;  // Referência para os sensores

    public ControlCenter(SensorNode[][] sensors) {
        this.sensors = sensors;
    }

    public synchronized void fightFire(int firePosX, int firePosY) {
        System.out.println("CENTRAL RECEIVED FIRE ALERT AT: [" + firePosX + ", " + firePosY + "], PUTTING IT OUT");
        Forest.getForest()[firePosX][firePosY] = Forest.BURNED;
        notifySensorsToReset();
    }

    private void notifySensorsToReset() {
        for (int i = 0; i < Forest.SIZE; i++) {
            for (int j = 0; j < Forest.SIZE; j++) {
                if (sensors[i][j].hasReceivedFireAlert) {
                    sensors[i][j].resetAlertStatus();
                }
            }
        }
        System.out.println("Sensors reset to ready state.");
    }
}

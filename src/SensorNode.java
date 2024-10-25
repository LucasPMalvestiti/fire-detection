public class SensorNode implements Runnable {
    private int nodePosX;
    private int nodePosY;
    private char[][] forest;
    boolean isBorder;
    private ControlCenter controlCenter;
    private SensorNode[][] sensor;
    public boolean hasReceivedFireAlert = false;

    public SensorNode(int nodePosX, int nodePosY, char[][] forest, boolean isBorder, ControlCenter controlCenter, SensorNode[][] sensor) {
        this.nodePosX = nodePosX;
        this.nodePosY = nodePosY;
        this.forest = forest;
        this.isBorder = isBorder;
        this.controlCenter = controlCenter;
        this.sensor = sensor;
    }

    public void run() {
        while(true) {
            synchronized(this) {
                lookForFire();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private synchronized void lookForFire() {
        for(int i = this.nodePosX - 1; i <= this.nodePosX + 1; ++i) {
            for(int j = this.nodePosY - 1; j <= this.nodePosY + 1; ++j) {
                if (this.forest[i][j] == '@' && !this.hasReceivedFireAlert) {
                    this.hasReceivedFireAlert = true;
                    if (!this.isBorder) {
                        System.out.println("CENTER SENSOR [" + this.nodePosY + ", " + this.nodePosX + "] DETECTED FIRE AT [" + i + ", " + j + "], PROPAGATING FIRE ALERT");
                        this.propagateFireAlert(new FireAlert(i, j, this));
                        return;
                    }
                    System.out.println("BORDER SENSOR [" + this.nodePosY + ", " + this.nodePosX + "] DETECTED FIRE AT [" + i + ", " + j + "], CALLING CENTRAL");
                    this.controlCenter.fightFire(i, j);
                }
            }
        }

    }

    public synchronized void receiveMessage(FireAlert fireAlert) {
        if (this.isBorder) {
            this.controlCenter.fightFire(fireAlert.firePosX, fireAlert.firePosY);
        } else {
            this.propagateFireAlert(fireAlert);
        }

    }

    private void propagateFireAlert(FireAlert fireAlert) {
        if (fireAlert.sender == this) {
            if (this.nodePosX > 1 && this.sensor[this.nodePosX - 3][this.nodePosY] != null) {
                this.sensor[this.nodePosX - 3][this.nodePosY].receiveMessage(fireAlert);
                System.out.println("TOP - [" + this.nodePosY + ", " + (this.nodePosX - 3) + "] RECEIVED FIRE ALERT");
            }

            if (this.nodePosX < 28 && this.sensor[this.nodePosX + 3][this.nodePosY] != null) {
                this.sensor[this.nodePosX + 3][this.nodePosY].receiveMessage(fireAlert);
                System.out.println("BOTTOM - [" + this.nodePosY + ", " + (this.nodePosX + 3)+ "] RECEIVED FIRE ALERT");
            }

            if (this.nodePosY > 1 && this.sensor[this.nodePosX][this.nodePosY - 3] != null) {
                this.sensor[this.nodePosX][this.nodePosY - 3].receiveMessage(fireAlert);
                System.out.println("LEFT - [" + (this.nodePosY - 3) + ", " + this.nodePosX + "] RECEIVED FIRE ALERT");
            }

            if (this.nodePosY < 28 && this.sensor[this.nodePosX][this.nodePosY + 3] != null) {
                this.sensor[this.nodePosX][this.nodePosY + 3].receiveMessage(fireAlert);
                System.out.println("RIGHT - [" + (this.nodePosY + 3) + ", " + this.nodePosX + "] RECEIVED FIRE ALERT");
            }
        }
    }

    public void resetAlertStatus() {
        this.hasReceivedFireAlert = false;
    }
}
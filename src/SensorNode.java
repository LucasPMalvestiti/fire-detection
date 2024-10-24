public class SensorNode implements Runnable {
    private int nodePosX;
    private int nodePosY;
    private char[][] forest;
    boolean isBorder;
    private ControlCenter controlCenter;
    private SensorNode[][] sensor;

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
                this.lookForFire();
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var3) {
                InterruptedException e = var3;
                System.out.println(e.getMessage());
            }
        }
    }

    private synchronized void lookForFire() {
        for(int i = this.nodePosX - 1; i <= this.nodePosX + 1; ++i) {
            for(int j = this.nodePosY - 1; j <= this.nodePosY + 1; ++j) {
                if (this.forest[i][j] == '@') {
                    if (!this.isBorder) {
                        this.propagateFireAlert(new FireAlert(i, j, this));
                        System.out.println("PROP sensor" + this.nodePosY + ", " + this.nodePosX + " fire at [" + i + ", " + j + "]");
                        return;
                    }

                    System.out.println("sensor" + this.nodePosY + ", " + this.nodePosX + " fire at [" + i + ", " + j + "]");
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
                System.out.println("cima");
                this.sensor[this.nodePosX - 3][this.nodePosY].receiveMessage(fireAlert);
            }

            if (this.nodePosX < 28 && this.sensor[this.nodePosX + 3][this.nodePosY] != null) {
                System.out.println("baixo");
                this.sensor[this.nodePosX + 3][this.nodePosY].receiveMessage(fireAlert);
            }

            if (this.nodePosY > 1 && this.sensor[this.nodePosX][this.nodePosY - 3] != null) {
                System.out.println("esquerda");
                this.sensor[this.nodePosX][this.nodePosY - 3].receiveMessage(fireAlert);
            }

            if (this.nodePosY < 28 && this.sensor[this.nodePosX][this.nodePosY + 3] != null) {
                System.out.println("direita");
                this.sensor[this.nodePosX][this.nodePosY + 3].receiveMessage(fireAlert);
            }
        }

    }
}
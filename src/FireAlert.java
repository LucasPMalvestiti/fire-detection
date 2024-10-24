public class FireAlert {
    public int firePosX;
    public int firePosY;
    public SensorNode sender;

    public FireAlert(int firePosX, int firePosY, SensorNode sender) {
        this.firePosX = firePosX;
        this.firePosY = firePosY;
        this.sender = sender;
    }
}

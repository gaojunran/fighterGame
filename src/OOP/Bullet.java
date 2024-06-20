package OOP;

public class Bullet {
    BulletStatus status;
    int x;
    int y;

    public Bullet(BulletStatus status, int x, int y) {
        this.status = status;
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (status == BulletStatus.FOE) {
            y--;
        } else if (status == BulletStatus.ENEMY) {
            y++;
        }
    }

}

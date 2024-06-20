package OOP;

public class Fighter {
    int x;
    int y;
    int hp;
    Bullet bullet;

    public Fighter() {
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }




}

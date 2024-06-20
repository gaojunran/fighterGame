package OOP;

import java.util.Random;

import static OOP.Game.WIDTH;

public class EnemyFighter extends Fighter {
    public EnemyFighter() {
        super();
        this.hp = 1;
        this.x = new Random().nextInt(1, WIDTH - 1);
        this.y = 0;
        shoot();
    }

    public void shoot() {
        this.bullet = new Bullet(BulletStatus.ENEMY, this.x, this.y + 1);
    }


}

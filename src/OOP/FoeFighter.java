package OOP;

import static OOP.Game.*;

public class FoeFighter extends Fighter {



    public FoeFighter()
    {
        super();
        this.hp = MAX_HP;
        this.x = WIDTH / 2;
        this.y = HEIGHT / 2;
    }

    public void shoot() {
        this.bullet = new Bullet(BulletStatus.FOE ,this.x, this.y - 1);
    }


}

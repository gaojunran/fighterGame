package OOP;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static OOP.Game.*;

public class FoeFighter extends Fighter implements KeyListener {



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


    @Override
    public void keyReleased(KeyEvent e) {
        // ignore
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                move(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                move(0, 1);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                move(1, 0);
                break;
            case KeyEvent.VK_SPACE:
                shoot();
                break;
            default:
                break;
        }
    }
}

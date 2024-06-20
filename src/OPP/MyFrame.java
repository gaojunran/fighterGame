package OPP;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {
    public MyFrame() {
        this.setUndecorated(true); // 去掉窗口装饰
        this.setSize(0, 0); // 尺寸为0
        this.setFocusable(true); // 确保窗体是可聚焦的
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);
        this.setVisible(true); // 虽然设置为可见，但实际上是不可见的
        this.requestFocusInWindow(); // 请求焦点
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
                moveFighter(0, -1);
//                System.out.println("up");
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                moveFighter(0, 1);
//                System.out.println("down");
                break;


            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                moveFighter(-1, 0);
//                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                moveFighter(1, 0);
                break;
            case KeyEvent.VK_SPACE:
                shoot();
            default:
                break;
        }
    }

    private static void moveFighter(int x, int y) {
        Fighter.fighterX += x;
        Fighter.fighterY += y;
    }

    private static void shoot() {
        Fighter.bulletX = Fighter.fighterX;
        Fighter.bulletY = Fighter.fighterY - 1;
    }
}

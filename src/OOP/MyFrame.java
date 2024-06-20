package OOP;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static OOP.Main.game;

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
                game.foeFighter.move(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                game.foeFighter.move(0, 1);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                game.foeFighter.move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                game.foeFighter.move(1, 0);
                break;
            case KeyEvent.VK_SPACE:
                game.foeFighter.shoot();
                break;
            default:
                break;
        }
    }

//    private static void moveFighter(int x, int y) {
//        FighterUtil.fighterX += x;
//        FighterUtil.fighterY += y;
//    }
//
//    private static void shoot() {
//        FighterUtil.bulletX = FighterUtil.fighterX;
//        FighterUtil.bulletY = FighterUtil.fighterY - 1;
//    }
}

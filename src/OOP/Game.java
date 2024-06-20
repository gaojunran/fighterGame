package OOP;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static final int WIDTH = 30, HEIGHT = 30;
    public static final int MAX_HP = 5, MAX_SCORE = 5;
    public static final int ENEMY_NUM = 3;
    public static final int FLUSH_MILLIS = 50 ;
    public static final int ENEMY_MOVE_INTERVAL = 20;
    public static final int ENEMY_BULLET_INTERVAL = 10;

    public static int flushCount;

    public GameStatus gameStatus;
    int score;
    FoeFighter foeFighter;
    ArrayList<EnemyFighter> enemyFighters;


    public Game() throws InterruptedException {
        init();
        drawInterface();
        while (true) {
            stateUpdate();
            flush();
            drawInterface();
        }
    }

    private void paintGame() {
        for (int y = 0; y < HEIGHT + 1; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (x == foeFighter.x && y == foeFighter.y) {
                    System.out.print("\033[34m*\033[0m");
                } else if (foeFighter.bullet != null && x == foeFighter.bullet.x && y == foeFighter.bullet.y) {
                    System.out.print("\033[34m|\033[0m");
                } else if (meetEnemyFighter(enemyFighters, x, y) != null) {
                    System.out.print("\033[31m@\033[0m");
                } else if (meetEnemyBullet(enemyFighters, x, y) != null) {
                    System.out.print("\033[31m|\033[0m");
                } else if (x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT) {
                    System.out.print("#");  // add border
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private void drawInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Score: " + score + "\n" + "HP: " + foeFighter.hp);
        switch (gameStatus) {
            case NORMAL -> paintGame();
            case WIN -> {
                System.out.println("You Win!");
                System.out.println("...");
                scanner.nextLine();
            }
            case LOSE -> {
                System.out.println("You Lose!");
                System.out.println("...");
                scanner.nextLine();
            }
        }
    }


    private static void flush() throws InterruptedException {
        Thread.sleep(FLUSH_MILLIS);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        flushCount++;
    }


    private void handleBulletMoving() {
        for (EnemyFighter enemyFighter : enemyFighters) {
            if (enemyFighter.bullet != null && flushCount % ENEMY_BULLET_INTERVAL == 0) {
                enemyFighter.bullet.move();
            }
        }
        if (foeFighter.bullet != null) {
            foeFighter.bullet.move();
        }
    }

    private void handleEnemyMoving() {
        if (flushCount % ENEMY_MOVE_INTERVAL == 0) {
            for (EnemyFighter enemyFighter : enemyFighters) {
                enemyFighter.move(0, 1);
            }
        }
    }

    private void stateUpdate() {
        handleBulletMoving();
        handleEnemyMoving();
        handleCheckStatus();
    }

    private void handleCheckStatus() {
        EnemyFighter beHit = null;
        // hp--
        if (meetEnemyFighter(enemyFighters, foeFighter.x, foeFighter.y) != null) {
            beHit = meetEnemyFighter(enemyFighters, foeFighter.x, foeFighter.y);
        }
        if (meetEnemyBullet(enemyFighters, foeFighter.x, foeFighter.y) != null) {
            beHit = meetEnemyBullet(enemyFighters, foeFighter.x, foeFighter.y);
        }
        if (beHit != null) {
            foeFighter.hp--;
            enemyFighters.remove(beHit);
            if (foeFighter.hp == 0) {
                gameStatus = GameStatus.LOSE;
                return;
            }
        }

        // score++ & update enemy
        if (foeFighter.bullet != null) {
            score++;
            enemyFighters.remove(meetEnemyFighter(enemyFighters, foeFighter.bullet.x, foeFighter.bullet.y));
            enemyFighters.add(new EnemyFighter());
            if (score == MAX_SCORE) {
                gameStatus = GameStatus.WIN;
            }
        }
}


    private void init() {
        foeFighter = new FoeFighter();
        enemyFighters = new ArrayList<>(ENEMY_NUM);
        for (int i = 0; i < ENEMY_NUM; i++) {
            enemyFighters.add(new EnemyFighter());
        }
        new MyFrame();
        gameStatus = GameStatus.NORMAL;
        score = 0;
        flushCount = 0;
    }




    private static EnemyFighter meetEnemyFighter(ArrayList<EnemyFighter> enemyFighters, int x, int y) {
        for (EnemyFighter enemyFighter : enemyFighters) {
            if (enemyFighter.x == x && enemyFighter.y == y) {
                return enemyFighter;
            }
        }
        return null;
    }

    private static EnemyFighter meetEnemyBullet(ArrayList<EnemyFighter> enemyFighters, int x, int y) {
        for (EnemyFighter enemyFighter : enemyFighters) {
            if (enemyFighter.bullet.x == x && enemyFighter.bullet.y == y) {
                return enemyFighter;
            }
        }
        return null;
    }
}

package OPP;

import java.util.Random;
import java.util.Scanner;

public class Fighter {
    public static final int WIDTH = 30, HEIGHT = 10;
    public static final int MAX_HP = 5, MAX_SCORE = 5;
    public static final int flushInterval = 10;
    public static final int enemyMoveInterval = 50;
    static int fighterX, fighterY;
    static int bulletX, bulletY;
    static int enemyX, enemyY;
    static int enemyMoveCount;
    static int score;
    static int hp;
    static Status status;

    public static void init() {
        fighterX = WIDTH / 2;
        fighterY = HEIGHT / 2;
        enemyX = new Random().nextInt(1, WIDTH - 1);
        enemyY = 0;
        bulletX = -1;  // by default, no bullet
        bulletY = -1;
        enemyMoveCount = 0;
        status = Status.NORMAL;
        score = 0;
        hp = MAX_HP;

        new MyFrame(); // for keyboard listener
    }


    public static void drawPicture() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Score: " + score + "\n" + "HP: " + hp);
        switch (status) {
            case NORMAL -> paintGame();
            case WIN -> {
                System.out.println("You Win!");
                System.out.println("Press any key to exit...");
                scanner.nextLine();
                System.exit(0);
            }
            case LOSE -> {
                System.out.println("You Lose!");
                System.out.println("Press any key to exit...");
                scanner.nextLine();
                System.exit(0);
            }
        }
    }

    private static void paintGame() {
        for (int y = 0; y < HEIGHT + 1; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (x == fighterX && y == fighterY) {
                    System.out.print("*");
                } else if (x == bulletX && y == bulletY && bulletX != -1) {
                    System.out.print("|");
                } else if (x == enemyX && y == enemyY) {
                    System.out.print("@");
                } else if (x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT) {
                    System.out.print("#");  // add border
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public static void reCalc() {
        handleBulletMoving();
        handleEnemyMoving();
        handleCheckStatus();
    }

    private static void handleCheckStatus() {
        if (bulletX == enemyX && bulletY == enemyY) {
            score++;
            if (score == MAX_SCORE) {
                status = Status.WIN;
                return;
            }
            enemyX = new Random().nextInt(1, WIDTH - 1);
            enemyY = 0;
        }
        if (enemyX == fighterX && enemyY == fighterY || enemyY == HEIGHT) {
            hp--;
            if (hp == 0) {
                status = Status.LOSE;
                return;
            }
            enemyX = new Random().nextInt(1, WIDTH - 1);
            enemyY = 0;
        }
    }


    private static void handleEnemyMoving() {
        enemyMoveCount++;
        if (enemyMoveCount % enemyMoveInterval == 0) {
            enemyY++;
        }
    }

    private static void handleBulletMoving() {
        bulletY--;
    }

    public static void rePaint() throws InterruptedException {
        // sleep for 0.1 secs
        Thread.sleep(flushInterval);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        drawPicture();
    }
}


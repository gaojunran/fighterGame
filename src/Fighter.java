import java.util.Random;

public class Fighter {
    public static final int WIDTH = 30, HEIGHT = 10;
    static int fighterX, fighterY;
    static int bulletX, bulletY;
    static int enemyX, enemyY;
    static int enemyMoveCount;
    static int score;
    static int hp;


    public static void init() {
        fighterX = WIDTH / 2;
        fighterY = HEIGHT / 2;
        enemyX = new Random().nextInt(1, WIDTH);
        enemyY = 0;
        bulletX = -1;  // by default, no bullet
        bulletY = -1;
        enemyMoveCount = 0;
        score = 0;
        hp = 5;

        new MyFrame(); // for keyboard listener
    }


    public static void drawPicture() {
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

    public static void pcOperate() {
        handleBulletMoving();
        handleEnemyMoving();

    }

    private static void handleCheckScoring() {
        if (bulletX == enemyX && bulletY == enemyY) {
            score++;
            enemyX = new Random().nextInt(1, WIDTH);
        }
    }

    private static void handleEnemyMoving() {
        enemyMoveCount++;
        if (enemyMoveCount % 10 == 0) {
            enemyY++;
        }
    }

    private static void handleBulletMoving() {
        bulletY--;
    }
}


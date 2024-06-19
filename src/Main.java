public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fighter.init();
        Fighter.drawPicture();
        while (true) {
            Fighter.reCalc();
            Fighter.rePaint();
        }
    }


}





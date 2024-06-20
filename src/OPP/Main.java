package OPP;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Fighter.init();
        Fighter.drawPicture();
        while (true) {
            Fighter.reCalc();
            Fighter.rePaint();
        }
    }


}





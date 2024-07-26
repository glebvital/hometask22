import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero extends JLabel {
    int w =200;
    int h =220;
    int currentX = 0;
    int currentY = 0;
    private Image originalImage = null;
    public Hero() {

        try {
            originalImage = ImageIO.read(new File("src/images/deerclops.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setSize(w, h);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(originalImage, currentX - 30, currentY -20, this);
    }


    public void move(int x, int y) {
        setBounds(x, y, w, h);
        new WalkAnim().start();
    }

    public void jump() {
        new Jump().start();
        new JumpAnim().start();
    }

    class Jump extends Thread{
        int [] yJump = {2, 12, 15, -1, -3, -7, -15, -3,-1};
        int [] xJump = {3, 3, 2, 2, 1, 2, 3, 4, 5};
        @Override
        public void run() {
            for (int i = 0; i < 8; i++) {
                setBounds(getX()+xJump[i], getY()-yJump[i], w, h);
                try {
                    sleep(1000/25);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    class WalkAnim extends Thread{
        int count = 0;
        @Override
        public void run() {
            while (count<3){
                count++;
                if (count==3){
                    currentX=0;
                }
                else
                    currentX-=w;
                repaint();
                try {
                    sleep(1000/6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    class JumpAnim extends Thread{
        int count = 0;
        @Override
        public void run() {
            while(count<2){
                count++;
                if (count==2){
                    currentY=0;
                }else
                    currentY-=h+20;
                    repaint();
                    try {
                        sleep(1000/3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}


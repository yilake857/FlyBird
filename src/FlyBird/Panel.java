package FlyBird;

import javax.swing.*;
import java.awt.*;

/**
 *绘制
 */
public class Panel extends JPanel implements Runnable{
    private final MainGame game;
    //生成Panel
    public Panel() {
        game = new MainGame();
        new Thread(this).start();
    }

    ////更新数值+重绘Panel
    public void update(){
        //更新数值
        game.update();
        repaint();
    }

    public void paintComponent(Graphics g) {
        //开始重绘
        //绘制所有图像
        Graphics2D g2D = (Graphics2D) g;
        for(Drawer r :game.getDrawers()){
            if(r.transform != null){//无改变的对象
                //渲染图像，在绘制之前将图像空间的变换应用到用户空间中。
                g2D.drawImage(r.image,r.transform,null);
            }else {//有改变的对象
                g.drawImage(r.image, r.x, r.y, null);
            }

        }
        //初版本绘制小鸟时所用
        g2D.setColor(Color.BLACK);
        if(!game.started){
            g2D.setFont(new Font("幼圆", Font.BOLD, 20));
            g2D.drawString("按空格键开始游戏", 150, 240);
        }else {
            g2D.setFont(new Font("幼圆", Font.BOLD, 20));
            g2D.drawString("分数:", 2, 20);
            g2D.drawString(Integer.toString(game.score), 58, 21);
            g2D.drawString("按P键暂停游戏", 340, 20);
        }

        if (game.gameOver) {
            g2D.setFont(new Font("幼圆", Font.BOLD, 20));
            g2D.drawString("按R键重新游戏", 150, 240);
        }
    }



    //开始线程
    public void run() {
        try {
            while (true){
                update();
                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

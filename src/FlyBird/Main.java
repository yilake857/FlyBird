package FlyBird;

import javax.swing.*;

/**
 * 进行游戏
 */
public class Main {
    //创建窗口属性
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    public static int screenWidth;
    public static int screenHeight;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);//不可调整窗体大小

        Keybord SpaceKey = Keybord.getSample();
        frame.addKeyListener(SpaceKey);


        Panel panel = new Panel();
        frame.add(panel);
    }
}

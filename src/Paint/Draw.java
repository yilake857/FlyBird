package Paint;

import javax.swing.*;
import java.awt.*;

/**
 * 测试用例
 */
public class Draw extends JFrame{
    public static void main(String[] args) {
        System.out.println((int) 0.5);
        new Draw();
    }
    private MyPanel myPanel = null;
    public Draw(){
        myPanel = new MyPanel();
        this.add(myPanel);
        this.setSize(400,400);//设置大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
        this.setVisible(true);//可视化
    }

}
class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {//绘画区
        super.paint(g);
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/assets/img.png"));
        g.drawImage(image,0,0,102,102,this);

    }
}
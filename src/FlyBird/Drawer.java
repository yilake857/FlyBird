package FlyBird;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Drawer：包括 int 型属性 x 和 y，Image 型的属性 image
 * 和 AffineTransform 型的属性 transform，其中 x 和 y 代表图片的位置，
 * 而 image 代表的是图片对象，
 * 这个时候就需要新建的类 ImageLoader 来返回 Image 对象到 Drawer 中，
 * 最后一 个属性 transform 代表图像的变换形式，在小鸟的飞行过程中，
 * 它的旋转就会用到 transform。
 */
public class Drawer {
    public int x;
    public int y;
    public Image image;
    public AffineTransform transform;

    public Drawer() {
    }

    public Drawer(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Loader.loadImage(imagePath);
    }
}

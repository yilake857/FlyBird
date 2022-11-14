package FlyBird;

import java.awt.*;

public class Tube {
    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public String direction;//方向

    private Image image;

    public Tube(String direction) {
        this.direction = direction;
        renew();
    }

    //最右边去重置为初始默认位置
    public void renew(){
        width = 66;
        height = 400;
        x = Main.WIDTH + 2;

        if(direction.equals("S")){
            y = -(int)(Math.random()  * 120 ) - height / 2;
        }
    }

    public void update(){
        x -= speed;
    }

    //如果撞到管道返回true
    //带下划线的都是鸟的位置
    //不带下划线的x y 均代表管道空白的位置
    public boolean collides(int _x, int _y, int _width, int _height){
        int margin = 2;

        if (_x + _width - margin > x && _x + margin < x + width) {

            if (direction.equals("S") && _y < y + height) {
                return true;
            } else return direction.equals("N") && _y + _height > y;
        }
        return false;
    }

    //返回所有对象的属性，坐标+图片对象
    public Drawer getDrawer(){
        Drawer d = new Drawer();
        d.x = x;
        d.y = y;
        if(image == null){
            image = Loader.loadImage("src/assets/Pipe-" + direction + ".png");
        }
        d.image = image;

        return d;
    }
}

package FlyBird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keybord implements KeyListener {
    //静态变量sample随时可调用
    private static Keybord sample;

    private final boolean[] keys;

    //将按键进行标记
    public Keybord() {
        keys = new boolean[256];
    }

    //返回键盘类
    public static Keybord getSample(){
        if (sample == null) sample = new Keybord();
        return sample;
    }



    //只要有按了某个键，就将其对应的boolean数组改成true
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length){
            keys[e.getKeyCode()] = true;
        }
    }

    public boolean Pushed(int key){
        if(key >= 0 && key < keys.length){
            return keys[key];
        }
        return false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length){
            keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

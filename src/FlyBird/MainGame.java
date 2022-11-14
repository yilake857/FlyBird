package FlyBird;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
//Game类实现的功能：
//监视、返回含有所有对象的容器、移动管道、判断撞击
public class MainGame {
    public static final int PIPE_DELAY = 100;
    //游戏属性
    public int score;
    //判断游戏是否继续
    public boolean gameOver;
    public boolean started;
    private boolean paused;
    //控制按键时的相邻两次空格
    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    private Bird bird;
    private ArrayList<Tube> pipes;
    private final Keybord keyboard;



    public MainGame(){
        keyboard = Keybord.getSample();
        restart();
    }

    //功能：监视
    private void watchForStart(){
        if(!started && keyboard.Pushed(KeyEvent.VK_SPACE)){
            started = true;
        }
    }
    private void watchForPause(){
        if(pauseDelay > 0){
            pauseDelay--;
        }

        if (keyboard.Pushed(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }
    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.Pushed(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    //功能：移动管道
    public void movePipes(){
        pipeDelay--;
        //什么时候生产新的上下管道
        if(pipeDelay < 0){
            pipeDelay = PIPE_DELAY;
            Tube north = null;
            Tube south = null;

            for (Tube pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    if (north == null) {
                        north = pipe;
                    } else if (south == null) {
                        south = pipe;
                        break;
                    }
                }
            }

            //生成N管道
            if(north == null){
                Tube tube = new Tube("N");
                pipes.add(tube);
                north = tube;
            }else {
                north.renew();
            }
            //生成S管道
            if(south == null){
                Tube tube = new Tube("S");
                pipes.add(tube);
                south = tube;
            }else {
                south.renew();
            }
            //使得中间部分可以通过
            north.y = south.y + south.height + 175;
        }
        for (Tube tube:pipes){
            tube.update();
        }
    }

    //功能：检测撞击
    public void checkForCollisions(){
        // 检测管
        for (Tube pipe : pipes) {
            if (pipe.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameOver = true;
                bird.death = true;
            } else if (pipe.x == bird.x && pipe.direction.equalsIgnoreCase("S")) {
                score++;
            }
        }
        // 检测撞地
        if(bird.y + bird.height > Main.HEIGHT - 80){
            gameOver = true;
            bird.y = Main.HEIGHT - 80 - bird.height;
        }
    }

    //功能：返回容器
    //将背景、管道、小鸟全部绘制到Panel
    public ArrayList<Drawer> getDrawers(){
        ArrayList<Drawer> drawers = new ArrayList<>();
        drawers.add(new Drawer(0,0, "src/assets/img.png"));
        for (Tube tube : pipes){
            drawers.add(tube.getDrawer());
        }
        drawers.add(bird.getDrawer());
        return drawers;
    }

    //每一次刷新页面时都要判断暂停和
    //刷新
    public void update(){
        //监视开始
        watchForStart();
        if (!started)
            return;
        //监视暂停
        watchForPause();
        //监视重置
        watchForReset();
        if (paused)
            return;
        //小鸟的刷新
        bird.update();

        if (gameOver)
            return;
        //全都没有，移动管道+检查撞击=更新
        movePipes();
        checkForCollisions();
    }

    //初始化
    public void restart(){
        paused = false;
        started = false;
        gameOver = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        bird = new Bird();
        pipes = new ArrayList<Tube>();

    }
}

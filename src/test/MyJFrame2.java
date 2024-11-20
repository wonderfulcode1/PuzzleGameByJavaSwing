package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame2 extends JFrame implements KeyListener {
    public MyJFrame2(){
        initJFrame();

        this.addKeyListener(this);

        setVisible(true);
    }

    private void initJFrame() {
        //设置界面的宽高
        setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图游戏 v1.0");
        //设置总是在上
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //取消默认的居中放置
        this.setLayout(null);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) //中文不行
    {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开按键");
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
    }
}
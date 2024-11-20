package test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame1 extends JFrame implements MouseListener{
    JButton bt=new JButton("别看我不爽");
    public MyJFrame1(){
        initJFrame();
        bt.setBounds(100,100,200,200);
        bt.addMouseListener(this);
        this.getContentPane().add(bt);
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
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
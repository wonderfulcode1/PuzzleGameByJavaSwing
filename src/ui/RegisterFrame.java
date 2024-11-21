package ui;

import User.Data;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class RegisterFrame extends JFrame implements MouseListener {
    JButton Reset =new JButton(new ImageIcon("image/register/重置按钮.png"));
    JButton register =new JButton(new ImageIcon("image/register/注册按钮.png"));

    JTextField usernameField = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField Again = new JPasswordField();

    Data d=new Data();

    public RegisterFrame() throws IOException {
        initFrame();

        initImage();

        setVisible(true);
    }

    private void initImage() {
        this.getContentPane().removeAll();

        JLabel usernameLabel = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        usernameLabel.setBounds(90,150,79,17);
        this.getContentPane().add(usernameLabel);

        JLabel passLabel =new JLabel(new ImageIcon("image/register/注册密码.png"));
        passLabel.setBounds(100,200,64,16);
        this.getContentPane().add(passLabel);

        JLabel again =new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        again.setBounds(73,250,96,17);
        this.getContentPane().add(again);

        //JLabel registerLabel =new JLabel(new ImageIcon("image/login/注册按钮.png"));
        register.setBounds(100,300,128,47);
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //JLabel enterLabel = new JLabel(new ImageIcon("image/login/登录按钮.png"));
        Reset.setBounds(240,300,128,47);
        Reset.setBorderPainted(false);
        Reset.setContentAreaFilled(false);
        this.getContentPane().add(Reset);

        usernameField.setBounds(170,150,200,25);
        this.getContentPane().add(usernameField);

        //JTextField usernameField = new JTextField();
        password.setBounds(170,200,200,25);
        this.getContentPane().add(password);
        password.setEchoChar('●');

        //JPasswordField passField = new JPasswordField();
        Again.setBounds(170,250,200,25);
        this.getContentPane().add(Again);
        Again.setEchoChar('●');

        JLabel backgroundLabel = new JLabel(new ImageIcon("image/register/background.png"));
        backgroundLabel.setBounds(0, 0, 470, 390);
        this.getContentPane().add(backgroundLabel);

        Reset.addMouseListener(this);
        register.addMouseListener(this);

        this.getContentPane().repaint();
    }

    private void initFrame() {
        setSize(488, 430);
        //设置界面标题
        this.setTitle("注册界面");
        //设置总是在上
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示出来,建议写在最后
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == Reset) {
            String pass=String.valueOf(password.getPassword());
            String pass1=String.valueOf(password.getPassword());
            String username=usernameField.getText();
            try {
                if(!d.find(username)){
                    System.out.println("未注册");
                    showJDialog("未注册");
                    return;
                }
                if(!pass.equals(pass1)){
                    System.out.println("密码不一致");
                    showJDialog("密码不一致");
                    return;
                }
                d.resetPassword(username,pass);
                System.out.println("重置成功");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (source == register) {
            String pass=String.valueOf(password.getPassword());
            String pass1=String.valueOf(Again.getPassword());
            String username=usernameField.getText();
            try {
                if(d.find(username)){
                    System.out.println("已注册");
                    showJDialog("已注册");
                    return ;
                }
                if(pass.equals(pass1)){
                    System.out.println("注册成功！");
                    d.add(username,pass);
                    System.out.println("登录成功！");
                    this.setVisible(false);
                    new GameFrame();
                }
                else {
                    showJDialog("密码不一致。");
                    System.out.println("密码不一致。");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == Reset) {
            Reset.setIcon(new ImageIcon("image/register/重置按下.png"));
        }else if (source == register) {
            register.setIcon(new ImageIcon("image/register/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == Reset) {
            Reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
        }else if (source == register) {
            register.setIcon(new ImageIcon("image/register/注册按钮.png"));
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void showJDialog(String content) {

        JDialog jDialog = new JDialog();

        jDialog.setSize(200, 150);

        jDialog.setAlwaysOnTop(true);

        jDialog.setLocationRelativeTo(null);

        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }
}

package ui;

import User.Data;
import Util.CodeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginFrame extends JFrame implements MouseListener {
    JButton enter=new JButton(new ImageIcon("image/login/登录按钮.png"));
    JButton register =new JButton(new ImageIcon("image/login/注册按钮.png"));
    JButton showLabel =new JButton(new ImageIcon("image/login/显示密码.png"));

    JLabel code =new JLabel();

    JTextField usernameField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JTextField verifyField = new JTextField();

    Data d=new Data();

    public LoginFrame() throws IOException {

        initFrame();

        initImage();

        //显示出来,建议写在最后
        this.setVisible(true);
    }

    private void initImage() {

        this.getContentPane().removeAll();

        JLabel usernameLabel = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameLabel.setBounds(100,150,47,17);
        this.getContentPane().add(usernameLabel);

        JLabel passLabel =new JLabel(new ImageIcon("image/login/密码.png"));
        passLabel.setBounds(100,200,32,16);
        this.getContentPane().add(passLabel);

        JLabel verifyLabel =new JLabel(new ImageIcon("image/login/验证码.png"));
        verifyLabel.setBounds(100,250,56,21);
        this.getContentPane().add(verifyLabel);

        //JLabel registerLabel =new JLabel(new ImageIcon("image/login/注册按钮.png"));
        register.setBounds(100,300,128,47);
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //JLabel enterLabel = new JLabel(new ImageIcon("image/login/登录按钮.png"));
        enter.setBounds(240,300,128,47);
        enter.setBorderPainted(false);
        enter.setContentAreaFilled(false);
        this.getContentPane().add(enter);

        //JTextField usernameField = new JTextField();
        usernameField.setBounds(170,150,200,25);
        this.getContentPane().add(usernameField);

        //JPasswordField passField = new JPasswordField();
        passField.setBounds(170,200,200,25);
        this.getContentPane().add(passField);
        passField.setEchoChar('●');

        //JLabel showLabel = new JLabel(new ImageIcon("image/login/显示密码.png"));
        showLabel.setBounds(380,200,18,29);
        showLabel.setBorderPainted(false);
        showLabel.setContentAreaFilled(false);
        this.getContentPane().add(showLabel);

        //JTextField verifyField = new JTextField();
        verifyField.setBounds(170,250,100,25);
        this.getContentPane().add(verifyField);

        String codeStr = CodeUtil.getCode();
        //设置内容
        code.setText(codeStr);
        //绑定鼠标事件
        code.addMouseListener(this);
        //位置和宽高
        code.setBounds(280, 250, 50, 30);
        //添加到界面
        this.getContentPane().add(code);

        JLabel backgroundLabel = new JLabel(new ImageIcon("image/login/background.png"));
        backgroundLabel.setBounds(0, 0, 470, 390);
        this.getContentPane().add(backgroundLabel);

        enter.addMouseListener(this);
        register.addMouseListener(this);
        showLabel.addMouseListener(this);

        this.getContentPane().repaint();
    }

    private void initFrame() {
        this.setSize(488, 430);

        this.setTitle("登录界面");

        this.setAlwaysOnTop(true);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void showJDialog(String content) {

        JDialog jDialog = new JDialog();

        jDialog.setSize(200, 150);

        jDialog.setAlwaysOnTop(true);

        jDialog.setLocationRelativeTo(null);

        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if(source == enter) {
            String usernameInput = usernameField.getText();
            String codeInput = verifyField.getText();
            String passwordInput = String.valueOf(passField.getPassword());
            if(!codeInput.equals(code.getText())) {
                System.out.println("验证码错误");
                showJDialog("验证码错误");
                return;
            }
            if(!d.register(usernameInput,passwordInput))
            {
                System.out.println("用户名错误");
                showJDialog("用户名或密码错误");
                return;
            }
            System.out.println("登录成功！");

            this.setVisible(false);
            new GameFrame();
        }else if(source == register) {
            this.setVisible(false);
            try {
                new RegisterFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if(source == enter) {
            System.out.println("登录");
            enter.setIcon(new ImageIcon("image/login/登录按下.png"));
        }else if(source == register) {
            System.out.println("注册");
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }else if(source == showLabel) {
            System.out.println("显示密码");
            showLabel.setIcon(new ImageIcon("image/login/显示密码按下.png"));
            passField.setEchoChar((char)0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if(source == enter) {
            System.out.println("登录");
            enter.setIcon(new ImageIcon("image/login/登录按钮.png"));
        }else if(source == register) {
            System.out.println("注册");
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }else if(source == showLabel) {
            showLabel.setIcon(new ImageIcon("image/login/显示密码.png"));
            passField.setEchoChar('●');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {
    public int[] temp={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    int[] win={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
    int i0=0,counter=0;
    String[] name={"animal","girl","sport"};
    String path="image/animal/animal3/";

    //创建菜单上条目对象
    JMenuItem replyItem = new JMenuItem("重新游戏");
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");

    JMenuItem accountItem = new JMenuItem("投喂");

    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    public GameFrame() {
        //初始化界面
        InitFrame();

        //初始化菜单
        InitJMenuBar();

        //初始化数组
        initDAta();

        //初始化图片
        initImage();

        //显示出来,建议写在最后
        setVisible(true);
    }

    private void initDAta() {
        //产生随机数
        //int[] temp={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for(int i=0;i<temp.length;i++)
        {   //获取随机指引
            int index = r.nextInt(temp.length);
            //拿遍历的每一个数据，和索引的数据交换
            int t=temp[index];
            temp[index]=temp[i];
            temp[i]=t;
            if(temp[i]==0){i0=i;}
            if(temp[index]==0){i0=index;}
        }
    }

    //初始化图片
    private void initImage() {
        //清空
        this.getContentPane().removeAll();

        if(victory())
        {
            JLabel winLabel=new JLabel(new ImageIcon("image/win.png"));
            winLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winLabel);
        }

        for (int i = 0; i <= 15; i++)//0时产生空的位置
        {
            JLabel label1=new JLabel(new ImageIcon(path+temp[i]+".jpg"));
            label1.setBounds((i%4)*105+83,(i/4)*105+134,105,105);
            label1.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(label1);
        }

        JLabel StepCount=new JLabel("步数："+counter);
        StepCount.setBounds(50,50,100,20);
        this.getContentPane().add(StepCount);

        //背景图片
        ImageIcon background = new ImageIcon("image/background.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(40, 40, background.getIconWidth(), background.getIconHeight());
        this.getContentPane().add(backgroundLabel);

        //刷新
        this.getContentPane().repaint();
    }
    private void InitJMenuBar() {
        //初始化菜单
        //创建菜单对象
        JMenuBar menuBar = new JMenuBar();

        //菜单上选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeJMenu = new JMenu("更换图片");

        functionJMenu.add(changeJMenu);
        functionJMenu.add(replyItem);
        functionJMenu.add(exitItem);
        functionJMenu.add(reLoginItem);

        changeJMenu.add(girlItem);
        changeJMenu.add(animalItem);
        changeJMenu.add(sportItem);

        aboutJMenu.add(accountItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);

        setJMenuBar(menuBar);

        replyItem.addActionListener(this);
        exitItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
    }

    private void InitFrame() {
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

        //
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==65)
        {
            this.getContentPane().removeAll();
            JLabel label1=new JLabel(new ImageIcon(path+"all.jpg"));
            label1.setBounds(40,40,508,560);
            this.getContentPane().add(label1);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //对上下左右进行判断
        //左37，上38，右39，下40
        if(victory())
        {
            return;
        }
        int x=i0%4,y=i0/4;
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("左");
            if(x<3){
                int t=temp[i0+1];
                temp[i0+1]=temp[i0];
                temp[i0]=t;
                i0++;
                counter++;
            }
            initImage();
        } else if (code == 38) {
            System.out.println("上");
            if(y<3){
                int t=temp[i0+4];
                temp[i0+4]=temp[i0];
                temp[i0]=t;
                i0+=4;
                counter++;
            }
            initImage();
        }else if (code == 39) {
            System.out.println("右");
            if(x>0){
                int t=temp[i0-1];
                temp[i0-1]=temp[i0];
                temp[i0]=t;
                i0--;
                counter++;
            }
            initImage();
        }
        else if (code == 40) {
            System.out.println("下");
            if(y>0){
                int t=temp[i0-4];
                temp[i0-4]=temp[i0];
                temp[i0]=t;
                i0-=4;
                counter++;
            }
            initImage();
        }else if (code == 65) {
            initImage();
        }else if(code==87){
            for(int i=0;i<temp.length;i++)temp[i]=i+1;
            temp[15]=0;
            i0=15;
            initImage();
        }
    }

    public boolean victory()
    {
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i]!=win[i])return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object=e.getSource();
        {
            if(object==exitItem)
            {
                System.out.println("退出");
                System.exit(0);
            } else if (object==reLoginItem) {
                System.out.println("重新登录");
                this.setVisible(false);
                try {
                    new LoginFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else if (object==accountItem) {
                System.out.println("公众号");
                //弹框对象
                JDialog dialog=new JDialog();
                JLabel label1=new JLabel(new ImageIcon("image/收款码.jpg"));
                label1.setBounds(0,0,204,227);
                dialog.getContentPane().add(label1);
                dialog.setSize(210,230);
                dialog.setLocationRelativeTo(null);//居中
                dialog.setAlwaysOnTop(true);//页面上方
                dialog.setModal(true);//不关闭无法操作下面界面
                dialog.setVisible(true);
            }else if (object==replyItem) {
                System.out.println("重新游戏");
                initDAta();
                counter=0;
                initImage();
            }else if (object==girlItem) {
                Random r=new Random();
                int index=r.nextInt(13)+1;
                path="image/girl/girl"+index+"/";
                initDAta();
                counter=0;
                initImage();
            }else if (object==animalItem) {
                Random r=new Random();
                int index=r.nextInt(8)+1;
                path="image/animal/animal"+index+"/";
                initDAta();
                counter=0;
                initImage();
            }else if (object==sportItem) {
                Random r=new Random();
                int index=r.nextInt(10)+1;
                path="image/sport/sport"+index+"/";
                initDAta();
                counter=0;
                initImage();
            }
        }
    }
}

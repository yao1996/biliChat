package top.GUI.LoginGui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by YaoKeQi on 2017/4/22.
 * commit on 4/24
 */
class LoginGui extends JFrame {
    LoginGui() {
        setResizable(false);
        setLayout(null);
        //设置大小
        setSize(450,333);
        //窗口居中
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d2 = getSize();
        setBounds((d1.width - d2.width)/2,(d1.height - d2.height)/2,d2.width,d2.height);
        setTitle("哔哩哔哩 (゜-゜)つロ 干杯~-bilibili");
        //替换Java图标
        Image icon = Toolkit.getDefaultToolkit().getImage("src/top/LoginGui/icon1.png");
        setIconImage(icon);
        JPanel iconPanel = iconPanel();
        JPanel mainPanel = mainPanel();
        add(iconPanel);
        add(mainPanel);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //上半logo部分
    private static JPanel iconPanel() {
        JPanel iconPanel = new JPanel();
        //iconPanel.setOpaque(false);
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setBackground(Color.pink);
        iconPanel.setSize(450,180);

        JLabel icon = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/top/LoginGui/icon2.png");
        icon.setIcon(imageIcon);

        JLabel westPanel = new JLabel();
        westPanel.setPreferredSize(new Dimension(165,180));

        iconPanel.add(icon,BorderLayout.CENTER);
        iconPanel.add(westPanel,BorderLayout.WEST);
        return iconPanel;
    }

    //此处为输入用户名密码,以及按钮部分
    private static JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("insets 10 100 0 0"));
        //设置透明度以便背景颜色可以显示正常
        mainPanel.setOpaque(false);
        mainPanel.setLocation(0,180);
        mainPanel.setSize(450,153);

        JPanel centerPanel = new JPanel();
        JPanel textPanel = textPanel();
        JPanel buttonPanel = buttonPanel();
        mainPanel.add(textPanel,"wrap");
        mainPanel.add(buttonPanel);
        return mainPanel;
    }

    private static JPanel textPanel () {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new MigLayout());

        JLabel username = new JLabel("用户名：");
        JLabel password = new JLabel("密码：");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        textPanel.add(username);
        textPanel.add(usernameField,"wrap");
        textPanel.add(password);
        textPanel.add(passwordField,"wrap");
        return textPanel;
    }

    //mainPanel部分中给按钮创建一个JPanel以便定位
    private static JPanel buttonPanel () {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout());
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(new RegisterMoniterInLG());
        buttonPanel.add(loginButton,"gapleft 40");
        buttonPanel.add(registerButton,"gapleft 30");
        return buttonPanel;
    }
}
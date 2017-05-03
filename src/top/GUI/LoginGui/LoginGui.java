package top.GUI.LoginGui;

import net.miginfocom.swing.MigLayout;
import top.Action.Close;
import top.Action.LoginAction;
import top.Action.RegisterInLoginGui;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

/**
 * Created by YaoKeQi on 2017/4/22.
 * commit on 4/24
 */
public class LoginGui extends JFrame {
    public static JLabel promptInLoginGui;
    public LoginGui(Socket socket) {
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
        Image icon = Toolkit.getDefaultToolkit().getImage("src/top/GUI/LoginGui/icon1.png");
        setIconImage(icon);
        JPanel iconPanel = iconPanel();
        JPanel mainPanel = mainPanel(socket);
        promptInLoginGui = new JLabel();
        promptInLoginGui.setBounds(190,235,100,30);
        add(promptInLoginGui);
        add(iconPanel);
        add(mainPanel);
        //提示登录是否成功label

        setVisible(true);
        addWindowListener(new Close(socket));
    }

    //上半logo部分
    private static JPanel iconPanel() {
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setBackground(Color.pink);
        iconPanel.setSize(450,180);

        JLabel icon = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/top/GUI/LoginGui/icon2.png");
        icon.setIcon(imageIcon);

        JLabel westPanel = new JLabel();
        westPanel.setPreferredSize(new Dimension(165,180));

        iconPanel.add(icon,BorderLayout.CENTER);
        iconPanel.add(westPanel,BorderLayout.WEST);
        return iconPanel;
    }

    //此处为输入用户名密码,以及按钮部分
    private static JPanel mainPanel(Socket socket) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("insets 10 100 0 0"));
        //设置透明度以便背景颜色可以显示正常
        mainPanel.setOpaque(false);
        mainPanel.setLocation(0,180);
        mainPanel.setSize(450,153);

        JPanel textPanel = textPanel(socket);
        mainPanel.add(textPanel,"wrap");
        return mainPanel;
    }

    private static JPanel textPanel (Socket socket) {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new MigLayout());

        JLabel username = new JLabel("用户名：");
        JLabel password = new JLabel("密码：");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel buttons = buttonPanel(usernameField,passwordField,socket);

        textPanel.add(username);
        textPanel.add(usernameField,"wrap");
        textPanel.add(password);
        textPanel.add(passwordField,"wrap");
        textPanel.add(buttons,"span 2");
        return textPanel;
    }

    //mainPanel部分中给按钮创建一个JPanel以便定位
    private static JPanel buttonPanel (JTextField username,JPasswordField password,Socket socket) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout());
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        loginButton.addActionListener(new LoginAction(username,password,socket));
        registerButton.addActionListener(new RegisterInLoginGui(socket));
        buttonPanel.add(loginButton,"gapleft 40,gaptop 10");
        buttonPanel.add(registerButton,"gapleft 30,gaptop 10");
        return buttonPanel;
    }
}
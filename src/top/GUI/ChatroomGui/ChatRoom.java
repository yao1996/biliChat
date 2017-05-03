package top.GUI.ChatroomGui;

import net.miginfocom.swing.MigLayout;
import top.Action.Close;
import top.Action.SendChatMsg;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;


/**
 * Created by YaoKeQi on 2017/4/27.
 * 11
 */
public class ChatRoom {
    private Socket socket;
    public static JTextArea messageGetText;
    public static JTextArea messageSendArea;
    public static DefaultListModel<String> listModel;

    public ChatRoom(Socket socket) {
        this.socket = socket;
        init();
    }

    private void init() {
        JFrame Chatroom = new JFrame();
        //标题
        Chatroom.setTitle("哔哩哔哩 (゜-゜)つロ 干杯~-bilibili");
        //设置大小
        Chatroom.setSize(625, 550);
        //窗口居中
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d2 = Chatroom.getSize();
        Chatroom.setBounds((d1.width - d2.width) / 2, (d1.height - d2.height) / 2, d2.width, d2.height);
        //替换Java图标
        Image icon = Toolkit.getDefaultToolkit().getImage("src/top/GUI/LoginGui/icon1.png");
        Chatroom.setIconImage(icon);

        JPanel main = new JPanel(new MigLayout("inset 20 20 20 20"));
        ImageIcon imageIcon = new ImageIcon("src/top/GUI/ChatroomGui/bili.jpg");
        JLabel imgLabel = new JLabel(imageIcon);//将背景图放在标签里。
        Chatroom.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());//设置背景标签的位置
        Chatroom.setContentPane(main);
        main.setOpaque(false);

        JScrollPane messageGet = new JScrollPane();
        messageGet.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageGet.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageGetText = new JTextArea("",20,34) ;
        messageGetText.setEditable(false);
        messageGet.setViewportView(messageGetText);
        JScrollPane messageSend = new JScrollPane();
        messageSend.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageSend.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageSendArea = new JTextArea("",4,34) ;
        messageSend.setViewportView(messageSendArea);
        JPanel buttons = new JPanel(new MigLayout());
        JButton fileButton = new JButton("文件");
        JButton senderButton = new JButton("发送");
        senderButton.addActionListener(new SendChatMsg(messageSendArea,socket));
        buttons.add(fileButton);
        buttons.add(senderButton,"gapleft 20");
        buttons.setOpaque(false);
        JScrollPane users = new JScrollPane();
        listModel = new DefaultListModel<>();
        JList<String> usersList = new JList<>(listModel);
        usersList.setPreferredSize(new Dimension(140,10));
        usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        users.setViewportView(usersList);

        main.add(messageGet,"wrap");
        main.add(messageSend,"wrap,gaptop 20");
        main.add(buttons,"right,gapbottom 10");
        main.add(users,"dock east,gapleft 20,gaptop 110");

        //设置可见
        Chatroom.setVisible(true);
        //设置关闭
        Chatroom.addWindowListener(new Close(socket));
    }
}
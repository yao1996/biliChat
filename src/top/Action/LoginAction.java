package top.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import static top.IO.SendToServer.sendMsg;


/**
 * Created by YaoKeQi on 2017/4/28.
 * 11
 */
public class LoginAction implements ActionListener {
    private JTextField username;
    private JPasswordField password;
    private Socket socket;

    public LoginAction (JTextField username, JPasswordField password, Socket socket) {
        this.username = username;
        this.password = password;
        this.socket = socket;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String usernameS = username.getText();
            String passwordS = String.valueOf(password.getPassword());
            sendMsg(usernameS,passwordS,socket);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

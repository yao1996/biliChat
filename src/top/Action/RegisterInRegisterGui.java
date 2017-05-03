package top.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import static top.GUI.LoginGui.RegisterGui.promptInRegisterGui;
import static top.IO.SendToServer.sendMsg;


/**
 * Created by YaoKeQi on 2017/4/28.
 * 11
 */
public class RegisterInRegisterGui implements ActionListener {
    private JTextField username;
    private JTextField nickname;
    private JPasswordField password;
    private JPasswordField rePassword;
    private Socket socket;

    public RegisterInRegisterGui(JTextField username, JTextField nickname, JPasswordField password, JPasswordField repeatPasswordField,Socket socket) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.rePassword = repeatPasswordField;

        this.socket = socket;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String usernameS = username.getText();
            String nicknameS = nickname.getText();
            String passwordS = String.valueOf(password.getPassword());
            String rePasswordS = String.valueOf(rePassword.getPassword());
            if (usernameS != null && nicknameS != null && passwordS.equals(rePasswordS)) {
                sendMsg(usernameS, nicknameS, passwordS, socket);
            }else if (usernameS == null) {
                promptInRegisterGui.setText("用户名不能为空");
                promptInRegisterGui.repaint();
            }else if (nicknameS == null) {
                promptInRegisterGui.setText("昵称不能为空");
                promptInRegisterGui.repaint();
            }else {
                promptInRegisterGui.setText("两次输入密码不一致");
                promptInRegisterGui.repaint();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

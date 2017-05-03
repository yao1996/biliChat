package top.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import static top.GUI.ChatroomGui.ChatRoom.messageSendArea;
import static top.IO.SendToServer.sendMsg;

/**
 * Created by YaoKeQi on 2017/4/30.
 * 11
 */
public class SendChatMsg implements ActionListener {
    private JTextArea messageArea;
    private Socket socket;

    public SendChatMsg(JTextArea messageArea, Socket socket) {
        this.messageArea = messageArea;
        this.socket = socket;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = messageArea.getText();
        try {
            sendMsg(message,socket);
            messageSendArea.setText("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

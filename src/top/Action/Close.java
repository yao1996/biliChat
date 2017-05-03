package top.Action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import static top.IO.SendToServer.*;

/**
 * Created by YaoKeQi on 2017/4/28.
 * 11
 */
public class Close extends WindowAdapter {
    private Socket socket;

    public Close(Socket socket) {
        this.socket = socket;
    }

    public void windowClosing(WindowEvent e) {
        try {
            sendMsg(socket);
            socket.close();
            System.exit(0);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(0);
        }
    }
}

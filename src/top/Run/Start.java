package top.Run;

import top.GUI.LoginGui.LoginGui;
import top.IO.RecieveFromServer;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by YaoKeQi on 2017/4/23.
 * commit on 4/24
 */
public class Start {
    public static LoginGui loginGui;
    public static void main (String[] args) {
        try {
            Socket socket = new Socket("localhost",8888);
            loginGui = new LoginGui(socket);
            Thread receive = new Thread(new RecieveFromServer(socket));
            receive.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

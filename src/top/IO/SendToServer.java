package top.IO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by YaoKeQi on 2017/5/2.
 * 11
 */
public class SendToServer {

    //登录
    public static void sendMsg(String username,String password,Socket socket) throws IOException {
        int[] i = new int[2];
        i[0] = username.length();
        i[1] = password.length();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeByte(0);
        dos.writeInt(i[0]);
        dos.writeInt(i[1]);
        dos.writeBytes(username);
        dos.writeBytes(password);
        dos.flush();
    }

    //注册
    public static void sendMsg(String username,String nickname,String password,Socket socket) throws IOException {
        int[] i = new int[2];
        i[0] = username.length();
        i[1] = password.length();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeByte(1);
        dos.writeInt(i[0]);
        dos.writeInt(i[1]);
        dos.writeBytes(username);
        dos.writeBytes(password);
        dos.writeUTF(nickname);
        dos.flush();
    }

    //聊天
    public static void sendMsg(String msg,Socket socket) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeByte(2);
        dos.writeUTF(msg);
        dos.flush();
    }

    //退出
    public static void sendMsg(Socket socket) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeByte(3);
        dos.flush();
    }
}

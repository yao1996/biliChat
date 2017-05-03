package top.IO;

import top.GUI.ChatroomGui.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static top.GUI.ChatroomGui.ChatRoom.listModel;
import static top.GUI.LoginGui.LoginGui.promptInLoginGui;
import static top.GUI.LoginGui.RegisterGui.promptInRegisterGui;
import static top.Run.Start.loginGui;
import static top.GUI.ChatroomGui.ChatRoom.messageGetText;

/**
 * Created by YaoKeQi on 2017/5/2.
 * 11
 */
public class RecieveFromServer implements Runnable {
    private Socket socket;

    public RecieveFromServer (Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        DataInputStream dis = null;
        boolean run = true;
        try {
            dis = new DataInputStream(socket.getInputStream());
        }catch (IOException e) {
            e.printStackTrace();
        }
        while (run) {
            try {
                if (dis != null) {
                    switch (dis.readByte()) {
                        case 0:
                            getLoginInfo(dis);
                            break;
                        case 1:
                            getRegisterInfo(dis);
                            break;
                        case 2:
                            getMessageInfo(dis);
                            break;
                        case 4:
                            getUsersInfo(dis);
                            break;
                        default:
                            break;
                    }
                }
            }catch (IOException e) {
                run = false;
            }
        }
    }

    private void getLoginInfo(DataInputStream dis) {
        try {
            int i = dis.readByte();
            if (i == 1) {
                loginGui.dispose();
                new ChatRoom(socket);
                int num = dis.readByte();
                int index = 0;
                for (; num >0; num--) {
                    String user = dis.readUTF();
                    listModel.add(index,user);
                    index ++;
                }
            } else if (i == 0){
                promptInLoginGui.setText("登录失败，请重试");
                promptInLoginGui.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getRegisterInfo(DataInputStream dis) {
        try {
            int i =dis.readByte();
            if (i == 1) {
                promptInRegisterGui.setText("注册成功");
                promptInRegisterGui.repaint();
            }else if (i == 0){
                promptInRegisterGui.setText("用户名已被占用");
                promptInRegisterGui.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessageInfo (DataInputStream dis) {
        String msg;
        try {
            msg = dis.readUTF();
            String lineSeparator;
            lineSeparator = System.getProperty("line.separator");
            messageGetText.append(msg + lineSeparator);
            msg = dis.readUTF();
            messageGetText.append(msg + lineSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUsersInfo (DataInputStream dis) {
        try {
            int addOrRemove = dis.readByte();
            if (addOrRemove == 1) {
                listModel.addElement(dis.readUTF());
            }else {
                listModel.removeElement(dis.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

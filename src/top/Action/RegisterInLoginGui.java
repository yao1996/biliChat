package top.Action;

import top.GUI.LoginGui.RegisterGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * Created by YaoKeQi on 2017/4/23.
 * commit on 4/24
 */
public class RegisterInLoginGui implements ActionListener {
    private Socket socket;
    public RegisterInLoginGui (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new RegisterGui(socket);
    }
}

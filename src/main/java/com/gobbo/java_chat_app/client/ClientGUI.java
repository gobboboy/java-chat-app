package com.gobbo.java_chat_app.client;

import com.gobbo.java_chat_app.Message;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class ClientGUI extends JFrame {
    public ClientGUI(String username) {
        super("User: " + username);

        setSize(1218, 685);
        setLocationRelativeTo(null);

    }
}

package com.gobbo.java_chat_app.client;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGUI clientGUI = null;
                try {
                    clientGUI = new ClientGUI("TapTap");
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clientGUI.setVisible(true);
            }
        });
    }
}

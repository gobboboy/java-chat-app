package com.gobbo.java_chat_app.client;

import com.gobbo.java_chat_app.Message;

import java.util.ArrayList;

public interface MessageListener {
    void onMessageRecieve(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}

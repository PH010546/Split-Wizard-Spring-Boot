package com.splitwizard.splitwizard.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService service;

    public SocketModule(SocketIOServer server, SocketService service) {
        this.service = service;
        this.server = server;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
        server.addEventListener("notificationToServer", Message.class, onChatReceived());

    }

    private DataListener<Message> onChatReceived() {
        log.info("message received");
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
//            senderClient.getNamespace().getBroadcastOperations().sendEvent("get_message", data.getMessage());
//            senderClient.getNamespace().getBroadcastOperations().sendEvent("notificationToClient", data.getMessage());
            service.sendMessage("notificationToClient", senderClient, data.getMessage(), data.getData());

        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}

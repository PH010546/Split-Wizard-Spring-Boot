//package com.splitwizard.splitwizard.socket;
//
//
//import com.corundumstudio.socketio.SocketIOClient;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class SocketService {
//
//    public void sendMessage(String eventName, SocketIOClient senderClient, String message, Object data) {
//        for (
//                SocketIOClient client : senderClient.getNamespace().getAllClients()) {
//            if (!client.getSessionId().equals(senderClient.getSessionId())) {
//                client.sendEvent(eventName,
//                        new Message(MessageType.SERVER, message, data));
//            }
//        }
//    }
//
//}

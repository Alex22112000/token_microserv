package jms;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;

public class TokenSender implements ISender {

    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/BackendQueue")
    private Queue queue;

    @Override
    public void sendToken(String token) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            Message message = context.createMessage();
            message.setJMSType("token");
            message.setStringProperty("token", token);
            producer.send(queue, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCheck(boolean check) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            Message message = context.createMessage();
            message.setJMSType("check");
            message.setBooleanProperty("check", check);
            producer.send(queue, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

package jms;

import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import model.interfaces.in.IModel;

@MessageDriven(mappedName = "jms/TokenQueue")
public class TokenListener implements MessageListener {

    @Inject
    private IModel model;

    @Inject
    private ISender sender;

    @Override
    public void onMessage(Message message) {
        try {
            String type = message.getJMSType();
            switch (type) {
                case "createToken":
                    {
                        String login = message.getStringProperty("login");
                        String role = message.getStringProperty("role");
                        sender.sendToken(model.createToken(login, role));
                        break;
                    }
                case "checkToken":
                {
                    String login = message.getStringProperty("login");
                    String token = message.getStringProperty("token");
                    sender.sendCheck(model.checkToken(login, token));
                    break;
                }
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

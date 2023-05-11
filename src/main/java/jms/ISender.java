package jms;

public interface ISender {
    void sendToken(String token);
    void sendCheck(boolean check);
}

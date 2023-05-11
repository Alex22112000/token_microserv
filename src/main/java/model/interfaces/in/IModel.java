package model.interfaces.in;

public interface IModel {
	String createToken(String login, String role) throws Exception;
	boolean checkToken(String login, String token);
}
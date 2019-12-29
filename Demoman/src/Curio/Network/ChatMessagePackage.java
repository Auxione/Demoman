package Curio.Network;

import java.io.Serializable;

public class ChatMessagePackage implements Serializable {
	public Credentials credentials;
	public String ChatMessage;

	public ChatMessagePackage(Credentials credentials, String ChatMessage) {
		this.credentials = credentials;
		this.ChatMessage = ChatMessage;
	}
}

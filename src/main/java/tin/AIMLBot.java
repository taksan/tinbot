package tin;

import bitoflife.chatterbean.AliceBot;
import bitoflife.chatterbean.ChatterBean;

import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;
import com.skype.User;

public class AIMLBot implements ChatMessageListener {

	private AliceBot aliceBot;
	
	public static void main(String[] args) throws Exception {
		ChatterBean chatterBean = new ChatterBean(null);
		AliceBot a = chatterBean.getAliceBot();
		System.out.println(a.respond("hello"));
		System.out.println(a.respond("My name is lucas"));
		System.out.println(a.respond("Who are you?"));
	}

	public AIMLBot() {
		aliceBot = new AliceBot();
	}

	public void chatMessageReceived(ChatMessage receivedChatMessage)
			throws SkypeException {
		String reply = getReply(getUser(receivedChatMessage.getSender()), receivedChatMessage.getContent());
		if (reply != null)
			receivedChatMessage.getSender().send(reply);
	}

	private String getReply(String user, String message) {
		return aliceBot.respond(message);
	}

	private String getUser(User sender) throws SkypeException {
		String fullName = sender.getFullName();
		if (fullName == null)
			return sender.getId();
		return fullName;
	}

	public void chatMessageSent(ChatMessage sentChatMessage)
			throws SkypeException {
		// does nothing
	}

}

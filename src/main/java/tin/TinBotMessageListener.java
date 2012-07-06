package tin;

import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;
import com.skype.User;

public class TinBotMessageListener implements ChatMessageListener {
	private ReplyBot bot;

	public TinBotMessageListener(ReplyBot bot) {
		this.bot = bot;
	}

	@Override
	public void chatMessageReceived(ChatMessage receivedChatMessage)
			throws SkypeException {
		String senderName = getUser(receivedChatMessage.getSender());
		String reply = bot.getReply(senderName, receivedChatMessage.getContent());
		if (reply != null)
			receivedChatMessage.getChat().send(reply);
	}

	private String getUser(User sender) throws SkypeException {
		String fullName = sender.getFullName();
		if (fullName == null)
			return sender.getId();
		return fullName;
	}

	@Override
	public void chatMessageSent(ChatMessage sentChatMessage)
			throws SkypeException {
		// nothing to do here
	}
	
}

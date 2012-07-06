package tin;

import bitoflife.chatterbean.AliceBot;
import bitoflife.chatterbean.ChatterBean;

public class AIMLBot implements ReplyBot {

	private AliceBot aliceBot;
	
	public static void main(String[] args) throws Exception {
		ChatterBean chatterBean = new ChatterBean(null);
		AliceBot a = chatterBean.getAliceBot();
		System.out.println(a.respond("hello"));
		System.out.println(a.respond("My name is lucas"));
		System.out.println(a.respond("Who are you?"));
	}

	public AIMLBot() {
		ChatterBean chatterBean = new ChatterBean(null);
		aliceBot = chatterBean.getAliceBot();
	}

	public String getReply(String user, String message) {
		return aliceBot.respond(message);
	}
}

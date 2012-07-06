package tin;

import com.skype.Skype;
import com.skype.SkypeException;

public class TinMain {
	public static void main(String[] args) throws SkypeException {
		if (!Skype.isRunning()) {
			System.out.println("Skype must be running");
		}
		Skype.setDaemon(false);
		
//		Skype.addChatMessageListener(new TinBot());
		Skype.addChatMessageListener(new AIMLBot());
	}
}

package tin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

public class TinBot implements ReplyBot {
	
	private static final String TINCONFIG = ".tinconfig";

	@Override
	public String getReply(String user, String message) {
		Map<String, String> replyByPattern = getReplyByPattern();
		return getReplyForMessage(replyByPattern, new Sender(user), message);
	}

	String getReplyForMessage(Map<String, String> replyByPattern, Sender sender, String message) {
		for (Entry<String, String> entry : replyByPattern.entrySet()) {
			String pattern = "(?ism)"+entry.getKey();
			String reply = entry.getValue().replace("$SENDER", sender.get());
			if (message.matches(pattern)) {
				return message.replaceAll(pattern, reply);
			}
		}
		return null;
	}
	
	private Map<String, String> getReplyByPattern() {
		File homeDir = getHomeDir();
		File config = new File(homeDir, TINCONFIG);
		if (!config.exists()) {
			throw new IllegalStateException(config.getAbsolutePath() + " doesn't exist." + getSample());
		}
		List<String> configLines = readConfigOrCry(config);
		return getReplyByTextMap(configLines);
	}

	Map<String, String> getReplyByTextMap(List<String> configLines) {
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		for (String line : configLines) {
			String[] split = line.split("(?<!\\\\)= ");
			if (split.length != 2)
				continue;
			replyByPattern.put(split[0].trim(), split[1].trim());
		}
		return replyByPattern;
	}

	private String getSample() {
		return "\n" +
				"Sample file: " +
				"hi = hi $SENDER\n" +
				"is (.*) there\\? = $1 is not around here";
	}

	private List<String> readConfigOrCry(File config) {
		try {
			return FileUtils.readLines(config);
		} catch (IOException e) {
			throw new IllegalStateException("Could not read configuration file " + TINCONFIG, e);
		}
	}

	private File getHomeDir() {
		String home = System.getProperty("user.home");
		File homeDir = new File(home);
		return homeDir;
	}
}

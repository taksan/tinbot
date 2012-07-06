package tin;

public class Sender  {

	private String sender;

	public Sender(String sender) {
		this.sender = sender;
	}

	public String get() {
		return sender.replaceAll("([^ ]*) .*", "$1");
	}

}

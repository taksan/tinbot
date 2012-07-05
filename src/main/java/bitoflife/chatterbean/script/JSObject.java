package bitoflife.chatterbean.script;

import java.applet.Applet;

public class JSObject {

	public static JSObject getWindow(Applet applet) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public Object eval(String script) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public Object getMember(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public void setMember(String name, Object value) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}

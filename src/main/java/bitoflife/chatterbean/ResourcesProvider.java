package bitoflife.chatterbean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ResourcesProvider {
	
	public static InputStream properties() {
		return ResourcesProvider.class.getResourceAsStream("/Bots/properties.xml");
	}

	public static InputStream context() {
		return ResourcesProvider.class.getResourceAsStream("/Bots/context.xml");
	}

	public static InputStream splitters() {
		return ResourcesProvider.class.getResourceAsStream("/Bots/splitters.xml");
	}

	public static InputStream substitutions() {
		return ResourcesProvider.class.getResourceAsStream("/Bots/substitutions.xml");
	}

	public static InputStream[] aimls() {
		String[] aimls = new String[]{
	    		"Again.aiml","condition.aiml","random.aiml","version.aiml","Alice.aiml","empty_wildcard.aiml",
	    		"learn.aiml","thatstar.aiml","Gender.aiml","Person2.aiml", "thattopic.aiml",
	    		"Astrology.aiml" ,"id.aiml","Person.aiml","topicstar.aiml","Learn/Learned.aiml"};
	    
	    List<InputStream> aimlsResources = new ArrayList<InputStream>();
	    
	    for (String string : aimls) {
	    	String resourceName = "/Bots/Alice/"+string;
			InputStream resourceAsStream = ResourcesProvider.class.getResourceAsStream(resourceName);
	    	if(resourceAsStream == null)
	    		throw new RuntimeException(resourceName + " :resource not found");
			aimlsResources.add(resourceAsStream);
		}
	    return aimlsResources.toArray(new InputStream[0]);
	}

}

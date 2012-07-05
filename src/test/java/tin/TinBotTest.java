package tin;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TinBotTest {
	TinBot subject = new TinBot();
	
	@Test
	public void onGetReplyByTextMap_ShouldReturnMap()
	{
		List<String> configLines = Arrays.asList(
				"inquire = answer1",
				"inq\\=max = answer2"
				);
		Map<String, String> actual = subject.getReplyByTextMap(configLines);
		String actualInquires = StringUtils.join(actual.keySet().toArray(),"\n");
		String actualReplies = StringUtils.join(actual.values().toArray(),"\n");
		
		assertEquals(
				"inquire\n" +
				"inq\\=max", 
				actualInquires);
		
		assertEquals(
				"answer1\n" +
				"answer2", 
				actualReplies);
	}
	
	@Test
	public void onGetReplyByTextMap_ShouldgnoreMalformedLines(){
		List<String> configLines = Arrays.asList(
				"inquire  answer1",
				" "
				);
		Map<String, String> actual = subject.getReplyByTextMap(configLines);
		String actualInquires = StringUtils.join(actual.keySet().toArray(),"\n");
		String actualReplies = StringUtils.join(actual.values().toArray(),"\n");
		
		assertEquals(
				"", 
				actualInquires);
		
		assertEquals(
				"", 
				actualReplies);
	}
	
	@Test
	public void onGetReplyForMessage_withSimplePattern_ShouldReturnStoredReply()
	{
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		replyByPattern.put("hello", "bye");
		
		String reply = subject.getReplyForMessage(replyByPattern, new Sender("John Doe"), "hello");
		assertEquals("bye", reply);
	}
	
	@Test
	public void onGetReplyForMessage_withRegexPattern_ShouldReturnStoredReply(){
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		replyByPattern.put("hello (.*)", "bye $1");
		
		String reply = subject.getReplyForMessage(replyByPattern, new Sender("John Doe"), "hello darling");
		assertEquals("bye darling", reply);
	}
	
	@Test
	public void onGetReplyForMessage_withUserVariableInReply_ShouldPutUsernameInReply()
	{
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		replyByPattern.put("hello (.*)", "bye $SENDER");
		
		String reply = subject.getReplyForMessage(replyByPattern, new Sender("John Doe"), "hello darling");
		assertEquals("bye John Doe", reply);
	}
	
	@Test
	public void onGetReplyForMessage_ShouldIgnoreCase()
	{
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		replyByPattern.put("hello (.*)", "bye $1");
		
		String reply = subject.getReplyForMessage(replyByPattern, new Sender("John Doe"), "HeLLo darling");
		assertEquals("bye darling", reply);
	}
	
	@Test
	public void onGetReplyForMessage_ShouldIgnoreLineBreaks()
	{
		Map<String, String> replyByPattern = new LinkedHashMap<String, String>();
		replyByPattern.put("hello (.*)", "bye $1");
		
		String message = "HeLLo \n" +
				"darling";
		String reply = subject.getReplyForMessage(replyByPattern, new Sender("John Doe"), message);
		assertEquals("bye \n" + 
				"darling", reply);
	}
}

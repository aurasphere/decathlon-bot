package co.aurasphere.decathlon.bot;

import java.util.List;

public class Config {

	private List<String> pages;

	private int delaySeconds;

	private String chatId;

	private String botToken;

	public int getDelaySeconds() {
		return delaySeconds;
	}

	public void setDelaySeconds(int delaySeconds) {
		this.delaySeconds = delaySeconds;
	}

	public List<String> getPages() {
		return pages;
	}

	public void setPages(List<String> pages) {
		this.pages = pages;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getBotToken() {
		return botToken;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

}
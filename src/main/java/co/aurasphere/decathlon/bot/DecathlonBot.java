package co.aurasphere.decathlon.bot;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class DecathlonBot {

	public static void main(String[] args) throws Exception {

		// Load configuration
		URL resource = DecathlonBot.class.getClassLoader().getResource("config.json");
		FileReader reader = new FileReader(new File(resource.toURI()));
		Gson gson = new Gson();
		Config config = gson.fromJson(reader, Config.class);

		// Init
		TelegramBot telegramBot = new TelegramBot(config.getBotToken());

		// Avoids multiple notification to be sent
		Map<String, Boolean> notificationMap = new HashMap<>();
		config.getPages().forEach(p -> notificationMap.put(p, true));

		// Main loop
		while (true) {
			for (String page : config.getPages()) {

				// Download page
				String html = Jsoup.connect(page).get().html();

				// Send notification
//				if (html.contains("http://schema.org/InStock") && notificationMap.get(page)) {
				if (html.contains("product_instock:\"A\"") && notificationMap.get(page)) {
					notificationMap.put(page, false);
					System.out.println("InStock " + page);
					
					// Sends a notification on Telegram
					telegramBot.execute(new SendMessage(config.getChatId(), "Articolo di nuovo in stock: " + page));
				}

//				if (html.contains("http://schema.org/OutOfStock")) {
				if (html.contains("product_instock:\"U\"")) {
					notificationMap.put(page, true);
				}
			}

			Thread.sleep(config.getDelaySeconds() * 1000);
		}
	}

}
package crawlers;

import java.io.IOException;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scrappers.TranslateMovie;
import scrappers.WebScrap;

public class PapystreamingCrawler {

	private static ArrayList<String> url_list = new ArrayList<String>();
	private static WebScrap webscrap = new WebScrap();
	private static WebScrap translatemovie = new WebScrap();

	public static void main(String[] args) {

		System.out.println("Welcome");
		String url = "https://vvw.papystreaming.stream/avant-toi/";

		crawl(1, url, new ArrayList<String>());
		// System.out.println(url_list.size());

	}

	private static void crawl(int level, String url, ArrayList<String> visited) {
		if (level <= 15) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if ((visited.contains(next_link) == false)
							&& (next_link.contains("https://vvw.papystreaming.stream/"))) {
						level++;
						visited.add(next_link);
						crawl(level, next_link, visited);
					}
				}

			}
		}
	}

	private static Document request(String url, ArrayList<String> v) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			if (con.response().statusCode() == 200) {
				if (test_url(url)) {
					// System.out.println("Link " + url);
					String name = url.substring(33, url.length() - 1).replace("-", " ");

					System.out.println("--------------------------------");
					System.out.println(name);
					translatemovie.movieTranslation(name, url);
					// url_list.add(url);
					// System.out.println(url_list.size());
				}
				return doc;

			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static boolean test_url(String url) {
		if (url.contains("/cat/"))
			return false;
		if (url.contains("/voirseries/"))
			return false;
		if (url.contains("/connection/"))
			return false;
		if (url.contains("/voirfilms/"))
			return false;
		if (url.contains("/#"))
			return false;
		if (url == "https://vvw.papystreaming.stream/")
			return false;

		return true;
	}

}

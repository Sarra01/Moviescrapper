package crawlers;

import java.io.IOException;


import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scrappers.TranslateMovie;
import scrappers.WebScrap;

public class ChillCrawler {

	private static WebScrap translatemovie = new WebScrap();
	private static ArrayList<String> url_list = new ArrayList<String>();
	private static ArrayList<String> name_list = new ArrayList<String>();
	
	
	public static ArrayList<String> getName_list() {
		return name_list;
	}

	public static void setName_list(ArrayList<String> name_list) {
		ChillCrawler.name_list = name_list;
	}

	private static WebScrap webscrap = new WebScrap();
/*
	public static void main(String[] args) {

		System.out.println("Welcome");
		String url = "https://123chill.to/21-bridges-watch-online/";

		crawl(1, url, new ArrayList<String>());
		// System.out.println(url_list.size());
	}
*/


	public ArrayList<String> getUrl_list() {
		return url_list;
	}

	public  void setUrl_list(ArrayList<String> url_list) {
		ChillCrawler.url_list = url_list;
	}

	public  WebScrap getWebscrap() {
		return webscrap;
	}

	public void setWebscrap(WebScrap webscrap) {
		ChillCrawler.webscrap = webscrap;
	}

	public void crawl(int level, String url, ArrayList<String> visited) {
		if (level <= 15) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if ((visited.contains(next_link) == false) && (next_link.contains("https://123chill.to/"))) {
						level++;
						visited.add(next_link);
						crawl(level, next_link, visited);
					}
				}

			}
		}
	}

	private Document request(String url, ArrayList<String> v) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			if (con.response().statusCode() == 200) {
				if (test_url(url)) {
					System.out.println("Link " + url);
					String name = url.substring(20, url.length() - 1).replace("-", " ");
					name = name.replace("watch", "");
					name = name.replace("online", "");

					if ((name.contains("watch")) && (name.contains("online"))) {
						name = name.substring(6, name.length() - 7);
					}
					System.out.println("--------------------------------");
					System.out.println(name);

					translatemovie.movieTranslation(name, url);

				}
				return doc;

			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean test_url(String url) {
		if (url.contains("?action="))
			return false;
		if (url.contains("/genre/"))
			return false;
		if (url.contains("/movies/"))
			return false;
		if (url.contains("/star/"))
			return false;
		if (url.contains("/director/"))
			return false;
		if (url == "https://123chill.to/")
			return false;

		return true;
	}

}

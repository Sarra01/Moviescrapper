package crawlers;

import java.io.IOException;

import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scrappers.TranslateMovie;
import scrappers.WebScrap;

public class PrimewireCrawler {

	private static ArrayList<String> url_list = new ArrayList<String>();
	private static WebScrap webscrap = new WebScrap();
	private static WebScrap translatemovie = new WebScrap();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome");
		String url = "https://primewire.es/movie/dark-web-1rx3w";
		crawl(1, url, new ArrayList<String>());

		System.out.println(url_list.size());

	}

	private static void crawl(int level, String url, ArrayList<String> visited) {
		if (level <= 15) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if ((visited.contains(next_link) == false) && (next_link.contains("https://primewire.es/movie/"))) {
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

					System.out.println("----------------------");
					System.out.println("Link " + url);
					url_list.add(url);
					// System.out.println(url_list.size());

					String[] t = url.substring(27).split("-");
					String ch = "";
					for (int i = 0; i < t.length - 1; i++) {
						ch += t[i];
						ch += " ";
					}
					String name = ch.substring(0, ch.length() - 1);

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

	public static boolean test_url(String url) {
		if (url.contains("/star/"))
			return false;
		if (url.contains("/tag/"))
			return false;
		if (url.contains("/series/"))
			return false;
		if (url.contains("/genre/"))
			return false;
		if (url.contains("/country/"))
			return false;

		return true;
	}

}

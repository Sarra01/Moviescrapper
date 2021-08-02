package crawlers;

import java.io.IOException;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scrappers.TranslateMovie;
import scrappers.WebScrap;

public class MoviestarsCrawler {

	private static ArrayList<String> url_list = new ArrayList<String>();
	private static WebScrap webscrap = new WebScrap();
	private static WebScrap translatemovie = new WebScrap();

	public static void main(String[] args) {

		System.out.println("Welcome");
		String url = "https://moviestars.to/movie/happy-cleaners-24245";
		ArrayList<String> l = new ArrayList<String>();
		l.add("https://moviestars.to/");
		crawl(1, url, l);
		System.out.println(url_list.size());

	}

	private static void crawl(int level, String url, ArrayList<String> visited) {
		if (level <= 15) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if ((visited.contains(next_link) == false) && (next_link.contains("https://moviestars.to/"))) {
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

					System.out.println("Link " + url);
					// url_list.add(url);
					// System.out.println(url_list.size());

					String[] t = url.substring(28).split("-");
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
		if (url.contains("/genre/"))
			return false;
		if (url.contains("/year/"))
			return false;
		if (url.contains("/country/"))
			return false;
		if (url.contains("/tv-shows"))
			return false;
		if (url.contains("/contact"))
			return false;
		if (url.contains("facebook.com"))
			return false;
		if (url.contains("twitter.com"))
			return false;
		if (url.contains("addthis"))
			return false;
		if (url.contains("pl.moviesflix4k.work"))
			return false;
		if (url.contains("disqus.com"))
			return false;
		if (url == "https://moviestars.to/")
			return false;
		if (url == "https://moviestars.to/movies")
			return false;

		return true;
	}

}

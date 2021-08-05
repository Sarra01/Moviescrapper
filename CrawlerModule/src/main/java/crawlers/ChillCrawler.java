package crawlers;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ChillCrawler {

	
	private ArrayList<String> urlList = new ArrayList<String>();
	private ArrayList<String> nameList = new ArrayList<String>();

	

	/*public void main(String[] args) {
		System.out.println("Welcome");
		String url = "https://123chill.to/21-bridges-watch-online/";
		crawl(1, url, new ArrayList<String>());
		// System.out.println(url_list.size());
	}*/

	public ArrayList<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(ArrayList<String> urlList) {
		this.urlList = urlList;
	}

	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public void crawl(int level, String url, ArrayList<String> visited) {
		if ((level <= 15)&&(nameList.size()<15)) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String nextLink = link.absUrl("href");
					if ((visited.contains(nextLink) == false) && (nextLink.contains("https://123chill.to/"))) {
						level++;
						visited.add(nextLink);
						crawl(level, nextLink, visited);
						

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
				if (testUrl(url)) {
					
					System.out.println(nameList.size());
					System.out.println(urlList.size());
					
					System.out.println("Link " + url);
					String name = url.substring(20, url.length() - 1).replace("-", " ");
					name = name.replace("watch", "");
					name = name.replace("online", "");

					if ((name.contains("watch")) && (name.contains("online"))) {
						name = name.substring(6, name.length() - 7);
					}
					System.out.println("--------------------------------");
					System.out.println(name);
					urlList.add(url);
					nameList.add(name);

				}
				return doc;

			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static boolean testUrl(String url) {
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
		if (url .contains("charlotte-moon-mysteries-green-on-the-greens"))
			return false;

		return true;
	}

}

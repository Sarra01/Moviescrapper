package crawlers;

import java.io.IOException;


import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PapystreamingCrawler {

	private ArrayList<String> urlList = new ArrayList<String>();
	private ArrayList<String> nameList = new ArrayList<String>();


	private void crawl(int level, String url, ArrayList<String> visited) {
		if (level <= 15) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String nextLink = link.absUrl("href");
					if ((visited.contains(nextLink) == false)
							&& (nextLink.contains("https://vvw.papystreaming.stream/"))) {
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

					String name = url.substring(33, url.length() - 1).replace("-", " ");

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

	public boolean testUrl(String url) {
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
	

}

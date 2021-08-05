package crawlers;


import java.io.IOException;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MoviestarsCrawler {

	private ArrayList<String> urlList = new ArrayList<String>();
	private ArrayList<String> nameList = new ArrayList<String>();

	public void crawl(int level, String url, ArrayList<String> visited) {
		if ((level <= 15)&&(nameList.size()<5)) {

			Document doc = request(url, visited);
			if (doc != null) {
				for (Element link : doc.select("a[href]")) {
					String nextLink = link.absUrl("href");
					if ((visited.contains(nextLink) == false) && (nextLink.contains("https://moviestars.to/"))) {
						level++;
						visited.add(nextLink);
						crawl(level, nextLink, visited);
					}
				}

			}
		}
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



	private Document request(String url, ArrayList<String> v) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			if (con.response().statusCode() == 200) {
				if (testUrl(url)) {

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
		if (url == "https://moviestars.to")
			return false;
		if (url == "https://moviestars.to/movies")
			return false;

		return true;
	}
	

}
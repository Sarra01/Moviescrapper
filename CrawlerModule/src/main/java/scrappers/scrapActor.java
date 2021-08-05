package scrappers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;

public class ScrapActor {
	public static void main(String[] args) {
		String actorname = "Janet McTeer";
		ArrayList<String> actorInfo = scrape(actorname);
	}

	public static ArrayList<String> scrape(String actorname) {
		final String url = "https://www.rottentomatoes.com/celebrity/";

		try {
			try {

				final Document document = Jsoup.connect(url + actorname.replace(" ", "_")).get();
				String dateOfBirth = "";
				String placeOfBirth = "";
				for (Element e : document.select("p.celebrity-bio__item")) {

					if (e.text().contains("Birthday:")) {
						String data = e.text();
						dateOfBirth = data.split(":", 2)[1];
						System.out.println(dateOfBirth);
					}
					if (e.text().contains("Birthplace:")) {
						String data = e.text();
						placeOfBirth = data.split(":", 2)[1];
						System.out.println(placeOfBirth);
					}
				}

				ArrayList<String> actorinfo = new ArrayList<String>();
				actorinfo.add(dateOfBirth);
				actorinfo.add(placeOfBirth);
				return actorinfo;
			} catch (Exception ex) {
				final Document document = Jsoup.connect(url + actorname.replace(" ", "-")).get();
				String dateOfBirth = "";
				String placeOfBirth = "";
				for (Element e : document.select("p.celebrity-bio__item")) {

					if (e.text().contains("Birthday:")) {
						String data = e.text();
						dateOfBirth = data.split(":", 2)[1];
						System.out.println(dateOfBirth);
					}
					if (e.text().contains("Birthplace:")) {
						String data = e.text();
						placeOfBirth = data.split(":", 2)[1];
						System.out.println(placeOfBirth);
					}
				}

				ArrayList<String> actorinfo = new ArrayList<String>();
				actorinfo.add(dateOfBirth);
				actorinfo.add(placeOfBirth);
				return actorinfo;
			}
		} catch (Exception ex) {
			ArrayList<String> actorinfo = new ArrayList<String>();
			ex.printStackTrace();

			return actorinfo;
		}
	}
}
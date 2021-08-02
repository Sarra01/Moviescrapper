package scrappers;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scrapeimdb {
	private static String[] category;
	private static String director;
	private static ArrayList<String> actors = new ArrayList<String>();
	private static ArrayList<String> languages = new ArrayList<String>();

	private static String imdbRating;

	public static void main(String[] args) {
		ScrapeImdb("https://www.imdb.com/title/tt1375666/?ref_=tt_sims_tt_t_3");
		for (String i : category) {
			System.out.println(i);
		}
		System.out.println(director);
		System.out.println(imdbRating);
		for (String j : languages) {
			System.out.println(j);
		}
	}

	public static void ScrapeImdb(String movieurl) {
		try {

			final Document doc = Jsoup.connect(movieurl).get();
			if (doc != null) {
				String s = doc.select("li.ipc-inline-list__item").text();

				if (!s.substring(0, s.indexOf("Cast")).contains("min")) {
					return;
				}

				int i = 0;
				for (Element e : doc.select("div.StyledComponents__CastItemSummary-y9ygcu-9.fBAofn a")) {
					i += 1;
					if ((i % 2) == 1) {

						actors.add(e.text());
						System.out.println(e.text());
					}

				}
				category = doc.select("a.GenresAndPlot__GenreChip-cum89p-3.fzmeux.ipc-chip.ipc-chip--on-baseAlt").text()
						.split(" ");
				director = doc.select(
						"a.ipc-metadata-list-item__list-content-item.ipc-metadata-list-item__list-content-item--link")
						.first().text();
				String type;
				type = doc.select("li.ipc-inline-list__item").first().text();
				// System.out.println(type);
				// System.out.println(doc.select("li.ipc-inline-list__item").text());

				try {
					imdbRating = doc.select("span.AggregateRatingButton__RatingScore-sc-1ll29m0-1.iTLWoV").first()
							.text();
				} catch (Exception e) {
					imdbRating = "-";
				}
				String[] str = {};
				for (Element e : doc.select("div")) {
					if (e.attr("data-testid").equals("title-details-section")) {

						str = e.text().split(" ");

					}
				}
				// for (String ch: str) {System.out.println(ch);}
				int k = 0;
				boolean test = false;
				while (true) {
					k += 1;
					if (str[k].contains("Language")) {
						test = true;
					} else if (str[k].contains("Also")) {
						break;
					} else if (str[k].contains("Filming")) {
						break;
					} else if (str[k].contains("Production")) {
						break;
					} else if (test == true) {
						languages.add(str[k]);
					}
					// else {System.out.println("no languages");}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

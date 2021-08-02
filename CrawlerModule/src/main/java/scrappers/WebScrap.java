package scrappers;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrap {
	private static String[] category;
	private static String director;
	private static ArrayList<String> actors = new ArrayList<String>();
	private static ArrayList<String> languages = new ArrayList<String>();
	private static String translatedMovie;
	private static String movieLink;
	private static String movieYear;

	private static String imdbRating;

	/*
	 * public static void main(String[] args) {
	 * ScrapeImdb("https://www.imdb.com/title/tt3554046/?ref_=adv_li_tt", "");
	 * 
	 * }
	 */

	public  void movieTranslation(String text, String url) {
		translatedMovie = "Not Found";
		final String urll = "https://www.imdb.com/find?q=";
		String trtext = "";
		String link = "";
		String year = "";
		try {
			final Document document = Jsoup.connect(urll + text.replace(" ", "+") + "&ref_=nv_sr_sm").get();
			int i = 0;
			for (Element e : document.select("td.result_text a")) {
				i += 1;
				link = e.absUrl("href");
				movieLink = link;

				if (i == 1) {
					trtext = (e.text());
					translatedMovie = trtext;

					break;
				}

			}
			ScrapeImdb(movieLink, url);

		} catch (Exception ex) {
			System.out.println("error");
		}
		try {
			final Document document = Jsoup.connect(movieLink).get();
			int i = 0;
			for (Element e : document.select("span.TitleBlockMetaData__ListItemText-sc-12ein40-2.jedhex")) {
				i += 1;
				if (i == 1) {
					year = e.text();
					movieYear = year;

					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("error");

		}

	}


	public static String getTranslatedMovie() {
		return translatedMovie;
	}


	public static void setTranslatedMovie(String translatedMovie) {
		WebScrap.translatedMovie = translatedMovie;
	}


	public void ScrapeImdb(String movieurl, String url) {
		try {
			languages = new ArrayList<String>();
			final Document doc = Jsoup.connect(movieurl).get();
			if (doc != null) {
				String s = doc.select("li.ipc-inline-list__item").text();
				boolean t = false;
				try {
					t = s.substring(0, Math.min(s.length() - 1, s.indexOf("Cast"))).contains("min");

				} catch (Exception e) {
					return;
				}
				if (!t) {
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

				while (k < str.length) {
					k += 1;
					if (str[k].contains("Language")) {
						test = true;
					} else if (str[k].contains("Also")) {
						break;
					} else if (str[k].contains("Filming")) {
						break;
					} else if (str[k].contains("Production")) {
						break;
					} else if (str[k].contains("See")) {
						break;
					} else if (test == true) {
						languages.add(str[k]);
					}
					// else {System.out.println("no languages");}

				}

				for (String j : category) {
					System.out.println(j);
				}
				System.out.println(director);
				System.out.println(imdbRating);
				for (String j : languages) {
					System.out.println(j);
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
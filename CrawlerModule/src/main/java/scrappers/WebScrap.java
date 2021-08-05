package scrappers;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrap {

	private static String director;
	private static ArrayList<String> actors = new ArrayList<String>();
	private static ArrayList<String> languages = new ArrayList<String>();
	private static String[] category;

	private static String translatedMovie;
	private static String movieLink;
	private static String movieYear;
	private static String imdbRating;

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
						if (!e.text().contains("episode"))actors.add(e.text());
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

	public void movieTranslation(String text, String url) {
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

	public String[] getCategory() {
		return category;
	}

	public void setCategory(String[] category) {
		WebScrap.category = category;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		WebScrap.director = director;
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public void setActors(ArrayList<String> actors) {
		WebScrap.actors = actors;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		WebScrap.languages = languages;
	}

	public String getTranslatedMovie() {
		return translatedMovie;
	}

	public void setTranslatedMovie(String translatedMovie) {
		WebScrap.translatedMovie = translatedMovie;
	}

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		WebScrap.movieLink = movieLink;
	}

	public String getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(String movieYear) {
		WebScrap.movieYear = movieYear;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		WebScrap.imdbRating = imdbRating;
	}

}
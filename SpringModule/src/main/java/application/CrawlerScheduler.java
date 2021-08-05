package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import crawlers.ChillCrawler;
import crawlers.MoviestarsCrawler;
import model.Actor;
import model.Category;
import model.Film;
import model.Language;
import repository.ActorRepository;
import repository.CategoryRepository;
import repository.FilmRepository;
import repository.LanguageRepository;
import scrappers.WebScrap;

@Component
@EnableScheduling
@EnableConfigurationProperties
public class CrawlerScheduler {
	@Autowired
	private ActorRepository actrep;
	@Autowired
	private FilmRepository filmrep;
	@Autowired
	private CategoryRepository catrep;
	@Autowired
	private LanguageRepository langrep;

	// a = nameList.get(i)
	// b = urlList.get(i)

	public void filmFilling(String a, String b) {
		WebScrap webscrap = new WebScrap();
		webscrap.movieTranslation(a, b);

		Film film = new Film();

		film.setNameFilm(webscrap.getTranslatedMovie());
		film.setCurrentRate(webscrap.getImdbRating());
		film.setYear(webscrap.getMovieYear());
		film.setLinkChill(b);
		if (webscrap.getCategory()==null) 
		{System.out.println(film.getLinkChill());
		return;
		}
		if (webscrap.getActors()==null)
		{System.out.println(film.getLinkChill());
		return;
		}

		Set<Actor> setact = new HashSet<Actor>();

		for (String j : webscrap.getActors()) {

			Actor actor = new Actor();
			actor.setNameActor(j);

			if (actrep.findByNameActor(actor.getNameActor()) == null) {
				actrep.save(actor);
				setact.add(actor);
			} else {
				setact.add(actrep.findByNameActor(actor.getNameActor()));
			}

		}
		
		Set<Category> setcat = new HashSet<Category>();
		
		for (String j : webscrap.getCategory()) {
			
			Category category = new Category();

			category.setTitle(j);

			if (catrep.findByTitle(category.getTitle()) == null) {
				catrep.save(category);
				setcat.add(category);
			} else {
				setcat.add(catrep.findByTitle(category.getTitle()));
			}

		}

		Set<Language> setlang = new HashSet<Language>();

		for (String j : webscrap.getLanguages()) {
			Language language = new Language();
			language.setNameLanguage(j);

			if (langrep.findByNameLanguage(language.getNameLanguage()) == null) {
				langrep.save(language);
				setlang.add(language);
			} else {
				setlang.add(langrep.findByNameLanguage(language.getNameLanguage()));
			}

		}

		film.setCategories(setcat);
		film.setActors(setact);
		film.setLanguages(setlang);

		if (filmrep.findByNameFilm(film.getNameFilm()) == null) {
			filmrep.save(film);
		}

	}

	@Bean
	@Scheduled(fixedRate = 600000)
	public void scheduleFixedRateTask() {

		System.out.println("******************* chill *******************");
		String url = "https://123chill.to/movies/";
		ChillCrawler chillCrawler = new ChillCrawler();
		chillCrawler.setUrlList(new ArrayList<>());
		chillCrawler.setNameList(new ArrayList<>());

		chillCrawler.crawl(1, url, new ArrayList<>());
		ArrayList<String> urlList = chillCrawler.getUrlList();
		ArrayList<String> nameList = chillCrawler.getNameList();

		for (int i = 0; i < urlList.size(); i++) {
			filmFilling(nameList.get(i), urlList.get(i));
		}

		/*
		 * System.out.println("******************* moviestars *******************"); url
		 * = "https://moviestars.to/movie/happy-cleaners-24245"; MoviestarsCrawler
		 * moviestarscrawler = new MoviestarsCrawler(); moviestarscrawler.setUrlList(new
		 * ArrayList<>()); moviestarscrawler.setNameList(new ArrayList<>());
		 * 
		 * moviestarscrawler.crawl(1, url, new ArrayList<>()); urlList =
		 * moviestarscrawler.getUrlList(); nameList = moviestarscrawler.getNameList();
		 * 
		 * for (int i = 0; i < urlList.size(); i++) { filmFilling(nameList.get(i),
		 * urlList.get(i)); }
		 */
	}
}

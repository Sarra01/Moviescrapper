package application;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import crawlers.ChillCrawler;
import model.Actor;
import model.Film;
import repository.ActorRepository;
import repository.FilmRepository;
import scrappers.WebScrap;

@Component
@EnableScheduling
@EnableConfigurationProperties
@EnableAutoConfiguration
public class CrawlerScheduler {
	@Autowired
	ActorRepository actrep;
	@Autowired(required = false)
	private FilmRepository filmrep;

	
	@Scheduled(fixedDelay = 120)
	public void scheduleFixedRateTask() {
		System.out.println("hello am working");
		String url = "https://123chill.to/movies/";
		Actor actor = new Actor();
		actor.setNameActor("Fethi Haddaoui");
	    actrep.save(actor);
		ChillCrawler chillCrawler = new ChillCrawler();
		chillCrawler.setUrl_list(new ArrayList());
		chillCrawler.setName_list(new ArrayList());

		chillCrawler.crawl(1, url, new ArrayList());
		ArrayList<String> u_list = chillCrawler.getUrl_list();
		ArrayList<String> n_list = chillCrawler.getName_list();

		System.out.println(u_list.size());
		for (int i = 0; i < u_list.size(); i++) {
			WebScrap webscrap = new WebScrap();
			webscrap.movieTranslation((n_list.get(i)), u_list.get(i));
			Film film = new Film();
			//film.setNameFilm(webscrap.getTranslatedMovie());
			filmrep.save(film);
			// film.set (webscrap.getCategory());
			// film.se (webscrap.getDirector());

		}

	}
}
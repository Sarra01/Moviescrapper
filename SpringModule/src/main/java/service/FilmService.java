package service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Film;
import repository.FilmRepository;


@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	public List<Film> getAll() {
		return filmRepository.findAll();
	}

	public Optional<Film> get(long id) {
		return filmRepository.findById(id);
	}

	public void delete(long id) {
		filmRepository.deleteById(id);
	}

	public Film add(Film film) {
		return filmRepository.save(film);
	}

}

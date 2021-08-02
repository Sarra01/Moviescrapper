package controller;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import model.Film;
import service.FilmService;

@RestController
@RequestMapping("film")
@RequiredArgsConstructor

public class FilmController {
  
 	@Autowired
    FilmService filmService;
	
   
    @PostMapping("/add")
    public Film saveFilm(@RequestBody Film film) {
		return filmService.add(film); 
	}
        
	@GetMapping("/getall")
	public List<Film> getAllFilm(){
		return filmService.getAll(); 
	}
	
	@GetMapping("/get/{id}")
	public Optional<Film> getFilm(@PathVariable("id") long id){
		return filmService.get(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		filmService.delete(id);
	}
	

	
}

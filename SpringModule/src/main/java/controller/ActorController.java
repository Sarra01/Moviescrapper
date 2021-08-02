package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import model.Actor;
import service.ActorService;

@RestController
@RequestMapping("actor")



@RequiredArgsConstructor

public class ActorController {	
    
	@Autowired
    ActorService actorService;
	    
	@PostMapping("/add")
    public Actor saveActor(@RequestBody Actor actor) {
		return actorService.Add(actor) ; 
	}
        
	@GetMapping("/getall")
	public List<Actor> getAllActor(){
		return actorService.getAll(); 
	}
	
	@GetMapping("/get/{id}")
	public Actor getActor(@PathVariable("id") long id){
		return actorService.get(id);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteActor(@PathVariable("id") long id) {
		actorService.delete(id);
	}


}

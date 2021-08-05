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

import model.Category;
import service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/getall")
	public List<Category> getCategory() {
		return categoryService.getAllCategory();
	}

	@GetMapping("/get/{id}")
	public Category getCategory(@PathVariable("id)") Long id) {
		return categoryService.getCategoryById(id);
	}

	@DeleteMapping("delete/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}

	@PostMapping("/add")
	public void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}

}

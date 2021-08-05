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

import model.Admin;
import service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/getall")
	public List<Admin> getAdmin()

	{
		return adminService.getAll();
	}

	@GetMapping("/get/{id}")
	public Admin getAdmin(@PathVariable("id)") Long id) {
		return adminService.getAdminById(id);
	}

	@DeleteMapping("delete/{id}")
	public void deleteAdmin(@PathVariable("id") Long id) {
		adminService.deleteAdmin(id);
	}

	@PostMapping("/add")
	public void addAdmin(@RequestBody Admin admin) {
		adminService.addAdmin(admin);
	}

}

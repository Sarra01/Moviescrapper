package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Admin;
import repository.AdminRepository;


@Service

public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public List<Admin> getAll() {
		return adminRepository.findAll();

	}

	public Admin getAdminById(Long id) {
		return adminRepository.getOne(id);
	}

	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

}

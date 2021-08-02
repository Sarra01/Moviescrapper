package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.UserInfo;
import repository.UserInfoRepository;


@Service

public class UserInfoService {

	@Autowired

	private UserInfoRepository userRepository;

	public List<UserInfo> getAllUserInfo() {
		return userRepository.findAll();

	}

	public UserInfo getUserById(Long id) {
		return userRepository.getOne(id);
	}

	public void addUser(UserInfo user) {
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}

package br.com.training.repository.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByCpf(String cpf) {
		User user = userRepository.findByCpf(cpf);
		if (user != null) {
			return user;
		}
		throw new EntityNotFoundException("User not found!");
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User update(User user, String cpf) {
		User currentUser = findByCpf(cpf);
		currentUser.setName(user.getName());
		currentUser.setCpf(user.getCpf());
		currentUser.setEmail(user.getEmail());
		currentUser.setBirthDate(user.getBirthDate());
		return userRepository.save(currentUser);
	}

	public void delete(String cpf) {
		User user = findByCpf(cpf);
		userRepository.delete(user);
	}
}

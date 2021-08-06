package br.com.training.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		return userRepository.save(user);
	}

	@GetMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable String cpf) {
		return userRepository.findByCpf(cpf);
	}

	@PutMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public User update(@PathVariable String cpf) {
		User user = userRepository.findByCpf(cpf);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		user.setName(user.getName());
		user.setCpf(user.getCpf());
		user.setEmail(user.getEmail());
		user.setBirthDate(user.getBirthDate());
		return userRepository.save(user);
	}

	@DeleteMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String cpf) {
		User user = userRepository.findByCpf(cpf);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		userRepository.delete(user);
	}
}

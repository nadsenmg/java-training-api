package br.com.training.controller;

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
import br.com.training.repository.service.UserService;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		return userService.createUser(new User(user.getName(), user.getEmail(), user.getCpf(), user.getBirthDate()));
	}

	@GetMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable String cpf) {
		return userService.findByCpf(cpf);
	}

	@PutMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public User update(@PathVariable String cpf, @RequestBody User user) {
		 return userService.update(user, cpf);
	}

	@DeleteMapping(value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String cpf) {
		userService.delete(cpf);
	}
}

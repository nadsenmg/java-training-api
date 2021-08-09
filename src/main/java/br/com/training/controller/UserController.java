package br.com.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.training.model.User;
import br.com.training.model.dto.UserDTO;
import br.com.training.service.UserService;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userForm) {
		userService.createUser(
				new User(userForm.getName(), userForm.getEmail(), userForm.getCpf(), userForm.getBirthDate()));
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/{cpf}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String cpf) {
		User user = userService.findByCpf(cpf);
		UserDTO userResponse = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);
	}

	@PutMapping(value = "/{cpf}")
	public ResponseEntity<UserDTO> update(@PathVariable String cpf, @RequestBody UserDTO userForm) {
		User user = userService.update(
				new User(userForm.getName(), userForm.getEmail(), userForm.getCpf(), userForm.getBirthDate()), cpf);
		userForm = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userForm, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<UserDTO> delete(@PathVariable String cpf) {
		userService.delete(cpf);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

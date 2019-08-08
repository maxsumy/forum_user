package telran.java29.forum.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Delegate;
import telran.java29.forum.dto.UserEditDto;
import telran.java29.forum.dto.UserProfileDto;
import telran.java29.forum.dto.UserRegDto;
import telran.java29.forum.service.AccountService;

@RestController
@RequestMapping("/account")

public class AccountManagmentController {

	@Autowired
	AccountService accountService;

	@PostMapping
	public UserProfileDto register(@RequestBody UserRegDto userRegDto) {
		return accountService.addUser(userRegDto);
	}

	@PostMapping("/{id}")
	public UserProfileDto userLogin(@PathVariable String id, @RequestHeader("Authorization") String auth) {
		return accountService.findUserById(id, auth);
	}

	@PutMapping
	public UserProfileDto edit(@RequestBody UserEditDto userEditDto, @RequestHeader("Authorization") String auth) {
		
		return accountService.editUser(userEditDto, auth);
	}

	@DeleteMapping
	public UserProfileDto remove(@RequestHeader("Authorization") String auth) {
		
		return accountService.removeUser(auth);
	}

	@PutMapping("/{id}/{role}")
	public Set<String> addRole(@PathVariable String id, @PathVariable String role,
			@RequestHeader("Authorization") String auth) {
		
		return accountService.addRoleUser(id, role, auth);
	}

}

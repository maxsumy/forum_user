package telran.java29.forum.service;

import java.util.Set;

import telran.java29.forum.dto.UserEditDto;
import telran.java29.forum.dto.UserProfileDto;
import telran.java29.forum.dto.UserRegDto;

public interface AccountService {
	UserProfileDto addUser(UserRegDto userRegDto);

	UserProfileDto findUserById(String login, String auth);

	UserProfileDto editUser(UserEditDto userEditDto, String auth);

	UserProfileDto removeUser(String auth);

	Set<String> addRoleUser(String id, String role, String auth);
}

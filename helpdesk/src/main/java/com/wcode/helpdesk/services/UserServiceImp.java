package com.wcode.helpdesk.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wcode.helpdesk.models.Role;
import com.wcode.helpdesk.models.User;
import com.wcode.helpdesk.repositories.RolesRepository;
import com.wcode.helpdesk.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = this.roleRepository.findByName("USER");

		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		return this.userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return this.userRepository.findById(id).get();
	}

	@Override
	public User update(Long id, User user) {
		User userCreated = this.findOne(id);
		if (userCreated != null) {
			// user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userCreated.setEmail(user.getEmail());
			userCreated.setName(user.getName());
			userCreated.setLastName(user.getLastName());
			userCreated.setRoles(user.getRoles());
			userCreated.setActive(user.getActive());
			User userUpdated = this.userRepository.save(userCreated);
			return userUpdated;
		}
		return userCreated;
	}

	@Override
	public void delete(Long id) {
		User userCreated = this.findOne(id);
		if (userCreated != null) {
			this.userRepository.delete(userCreated);
		}
	}

	@Override
	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
		return this.userRepository.findAllWhereRoleEquals(role_id, user_id);
	}

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User findCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		return this.userRepository.findByEmail(userName);
	}

}

package com.wcode.helpdesk.services;

import java.util.List;

import com.wcode.helpdesk.models.User;

public interface UserService {

	public User create(User user);

	public List<User> findAll();

	public User findOne(Long id);

	public User findByEmail(String email);

	public User update(Long id, User user);

	public void delete(Long id);

	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id);
	// public List<User> findAllWhereRoleEquals(Long role_id, Long user_id);
	public User findCurrentUser();

}

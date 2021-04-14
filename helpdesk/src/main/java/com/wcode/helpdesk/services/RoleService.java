package com.wcode.helpdesk.services;

import java.util.List;

import com.wcode.helpdesk.models.Role;

public interface RoleService {

	Role create(Role role);

	List<Role> findAll();

	Role findOne(Long id);

	Role update(Long id, Role role);

	void delete(Long id);

	Role findByName(String name);

}

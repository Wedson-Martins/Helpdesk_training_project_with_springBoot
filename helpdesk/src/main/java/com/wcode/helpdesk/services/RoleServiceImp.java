package com.wcode.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcode.helpdesk.models.Role;
import com.wcode.helpdesk.repositories.RolesRepository;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RolesRepository repository;

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		return this.repository.save(role);

	}

	@Override
	public List<Role> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Role findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Role update(Long id, Role role) {
		Role roleBD = this.repository.findById(id).map(r -> {
			if (r != null) {
				role.setId(r.getId());
				role.setName(role.getName().toUpperCase());
				this.repository.save(role);
			}
			return role;
		}).get();

		return null;
	}

	@Override
	public void delete(Long id) {

		Role roleBD = this.repository.findById(id).get();
		if (roleBD != null) {
			this.repository.delete(roleBD);
		}

		// this.repository.findById(id).map(r -> {
		// if (r != null) {
		// this.repository.delete(r);
		// }
		// return r;
		// }).get();

	}

	@Override
	public Role findByName(String name) {
		return this.repository.findByName(name);
	}

}

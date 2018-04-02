package com.circumborrealis.serverside.Repository;

import org.springframework.data.repository.CrudRepository;

import com.circumborrealis.serverside.Model.User;

public interface IUserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}

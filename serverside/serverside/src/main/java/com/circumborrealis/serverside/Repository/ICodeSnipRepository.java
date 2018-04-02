package com.circumborrealis.serverside.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.circumborrealis.serverside.Model.CodeSnip;
import com.circumborrealis.serverside.Model.User;

public interface ICodeSnipRepository extends CrudRepository<CodeSnip, Long>{
	CodeSnip findByUser(User user);
	List<CodeSnip> findByOwner(String owner); 
}



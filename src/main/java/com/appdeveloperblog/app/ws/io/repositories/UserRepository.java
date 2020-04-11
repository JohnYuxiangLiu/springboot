package com.appdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appdeveloperblog.app.ws.io.entity.UserEntity;

//crudRepository contain all method of crud, which UserRepository use to do crud
//this interface saves/retrieves data from UserEntity to and from db
@Repository
//an interface UserRepository can use extends not implements another interface
public interface UserRepository extends CrudRepository<UserEntity, Long> {

//	no need to write more crud here, but customised crud can be write here,e.g.
//	UserEntity findUserByEmail(String email) //return an UserEntity obj by searching email
	
//	below is customised crud
	
//	findByEmail: return a UserEntity from db
	UserEntity findByEmail(String email);
}

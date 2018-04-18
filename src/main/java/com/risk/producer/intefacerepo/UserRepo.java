package com.risk.producer.intefacerepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.constants.Queries;
import com.risk.producer.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

  @Query(Queries.USERBYID)
  List<User> findByName(String userName, String password);
}

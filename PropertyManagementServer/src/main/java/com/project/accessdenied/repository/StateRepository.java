package com.project.accessdenied.repository;

import com.project.accessdenied.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {

}

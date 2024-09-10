package com.example.redisdemo;

import org.springframework.data.repository.CrudRepository;

public interface AccessCountRepository extends CrudRepository<AccessCountEntity, String> {

}

package com.ypc.elasticsearch.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ypc.elasticsearch.entity.user;

import java.util.Optional;

@Component
public interface UserRepository extends ElasticsearchRepository<user,Integer> {

	@Override
	Optional<user> findById(Integer integer);

	@Override
	user save(user user);
	
	@Query("{\"bool\": {\"must\": [{ \"match\": { \"id\": \"?0\"}}]}}")
	Optional<user> queryUserById(@Param("id") Integer id);

}

package io.spring.springelasticdemo.loader;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.spring.springelasticdemo.model.User;
import io.spring.springelasticdemo.repository.elastic.ElasticUserRepository;
import io.spring.springelasticdemo.repository.jpa.JpaUserRepository;
@Component
public class ElasticLoader {
	@Autowired
	ElasticsearchOperations elasticsearchOperations;
	
	@Autowired
    ElasticUserRepository elasticUserRepository;
	
	@Autowired
	JpaUserRepository jpaUserRepository;
	
	@PostConstruct
	@Transactional
	public void loadAll() {
		// Here we create the index
		elasticsearchOperations.putMapping(User.class);
		System.out.println("Loading data...");
		jpaUserRepository.save(getData());
		
		// Normally. we would read data from a JpaRepository,
		// and load it to an ElasticsearchRepository
		List<User> users = (List<User>)jpaUserRepository.findAll();
		elasticUserRepository.save(users);
		System.out.println("Loading completed.");
	}
	
	private List<User> getData() {
		List<User> users = new ArrayList<>();
		users.add(new User(1L, "Frank Zappa", "Engineering", 1000000L));
		users.add(new User(2L, "Rod Jefferson", "Test", 1200000L));
		users.add(new User(3L, "Mike Oldfield", "Infrastructure", 1500000L));
		return users;
	}
}

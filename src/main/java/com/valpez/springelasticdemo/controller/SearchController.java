package com.valpez.springelasticdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valpez.springelasticdemo.model.User;
import com.valpez.springelasticdemo.repository.elastic.ElasticUserRepository;

@RestController
@RequestMapping("/rest/search")
public class SearchController {
	@Autowired
	private ElasticUserRepository userRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@GetMapping(value = "/name/{name}")
	public List<User> seachByName(@PathVariable("name") final String name) {
		return userRepository.findByName(name);
	}

	@GetMapping(value = "/salary/{salary}")
	public List<User> seachBySalary(@PathVariable("salary") final Long salary) {
		return userRepository.findBySalary(salary);
	}

	@GetMapping(value = "/all")
	public List<User> searchAll() {
		Iterable<User> itUsers = userRepository.findAll();
		List<User> users = new ArrayList<>();
		itUsers.forEach(users::add);
		return users;
	}

	/*
	 * We cannot use the ElasticUserRepository to delete an index, because it's
	 * always tied up with a User entity; however, we are going to delete the user
	 * index altogether. For this kind of operations, Spring provides an
	 * ElasticSearchTemplate.
	 */
	@DeleteMapping(value = "/index")
	public boolean deleteIndex() {
		return elasticsearchTemplate.deleteIndex(User.class);
	}
}

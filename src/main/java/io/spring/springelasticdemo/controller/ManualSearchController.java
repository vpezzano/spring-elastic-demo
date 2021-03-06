package io.spring.springelasticdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.springelasticdemo.builder.SearchQueryBuilder;
import io.spring.springelasticdemo.model.User;

@RestController
@RequestMapping("/rest/manual/search")
public class ManualSearchController {
	@Autowired
	SearchQueryBuilder searchQueryBuilder;

	@GetMapping(value = "/{text}")
	public List<User> search(@PathVariable final String text) {
		return searchQueryBuilder.search(text);
	}
}

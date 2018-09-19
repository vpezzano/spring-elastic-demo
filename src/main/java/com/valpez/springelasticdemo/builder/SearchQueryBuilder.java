package com.valpez.springelasticdemo.builder;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.valpez.springelasticdemo.model.User;

/*
 * Elasticsearch DSL is a high-level library whose aim is to help with writing and running
 * queries against Elasticsearch. It is built on top of the official low-level client.
 */
@Component
public class SearchQueryBuilder {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	public List<User> search(String text) {
		// If lenient is set to true, the data-type incompatibility exceptions will be ignored (e.g., querying
		// strings on a numeric field)
		QueryStringQueryBuilder qsqb1 = QueryBuilders.queryStringQuery(text).field("name").field("team").lenient(true);
		QueryStringQueryBuilder qsqb2 = QueryBuilders.queryStringQuery("*" + text + "*").field("name").field("team").lenient(true);
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(qsqb1).should(qsqb2);
		
		NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		return elasticsearchTemplate.queryForList(build, User.class);
	}
}

package com.valpez.springelasticdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

/*
 * The type = "admin" is what we use to search data in ElasticSearch; in the present case, we need to
 * use http://127.0.0.1:9200/user/admin/<id value>, because the type we defined is "admin", an so this
 * has to follow in the hierarchy just after user.
 */
@Entity
@Document(indexName = "user", type = "admin", shards = 1)
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String team;
	private Long salary;
	
	public User() {
		super();
	}

	public User(Long id, String name, String team, Long salary) {
		super();
		this.id = id;
		this.name = name;
		this.team = team;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}
}


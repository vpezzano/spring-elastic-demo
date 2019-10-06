package io.spring.springelasticdemo.repository.elastic;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import io.spring.springelasticdemo.model.User;

@Repository
public interface ElasticUserRepository extends ElasticsearchRepository<User, Long> {

	List<User> findByName(String name);

	List<User> findBySalary(Long salary);

}

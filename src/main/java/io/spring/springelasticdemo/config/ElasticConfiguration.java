package io.spring.springelasticdemo.config;

import java.io.File;
import java.io.IOException;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "io.spring.springelasticdemo.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "io.spring.springelasticdemo.repository.elastic")
public class ElasticConfiguration {
	@Bean
	NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}

	@Bean
	ElasticsearchTemplate elasticsearchTemplate() throws IOException {
		File tmpDir = new File("C:/tmp/elastic");
   
		Settings.Builder elasticSearchSettings = Settings.settingsBuilder().put("http.enabled", "true")
				.put("index.number_of_shards", "1").put("path.data", new File(tmpDir, "data").getAbsolutePath())
				.put("path.logs", new File(tmpDir, "logs").getAbsolutePath())
				.put("path.work", new File(tmpDir, "work").getAbsolutePath()).put("path.home", tmpDir);

		return new ElasticsearchTemplate(
				nodeBuilder().local(true).settings(elasticSearchSettings.build()).node().client());
	}

}

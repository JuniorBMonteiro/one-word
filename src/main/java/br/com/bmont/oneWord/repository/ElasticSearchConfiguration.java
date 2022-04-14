package br.com.bmont.oneWord.repository;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.bmont.oneWord.repository")
public class ElasticSearchConfiguration {
    @Bean
    public RestHighLevelClient elasticsearchClient(){
        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder()
                        .connectedTo("localhost:9200")
                        .withConnectTimeout(10000)
                        .build();
        return RestClients.create(clientConfiguration).rest();
    }
}

package br.com.bmont.oneWord.repository;

import br.com.bmont.oneWord.model.OneWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OneWordRepository extends ElasticsearchRepository<OneWord, String> {
    OneWord findByWord(String word);

    @Query("{\"function_score\":{\"query\":{\"bool\":{\"must\":[{\"match\":{\"className.keyword\":\"?0\"}}]}},\"random_score\":{}}}")
    OneWord findWordRandom(String className);
}

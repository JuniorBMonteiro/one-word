package br.com.bmont.OneWord.repository;

import br.com.bmont.OneWord.model.OneWord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface OneWordRepository extends ElasticsearchRepository<OneWord, String> {
    OneWord findByWord(String word);
}

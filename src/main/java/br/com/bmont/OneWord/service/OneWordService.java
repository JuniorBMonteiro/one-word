package br.com.bmont.OneWord.service;


import br.com.bmont.OneWord.model.OneWord;
import br.com.bmont.OneWord.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneWordService {
    private final OneWordRepository oneWordRepository;

    @Autowired
    public OneWordService(OneWordRepository oneWordRepository) {
        this.oneWordRepository = oneWordRepository;
    }

    public OneWord getByWord(String word){
        return oneWordRepository.findByWord(word);
    }

    public Page<OneWord> getAllWords(Pageable pageable){
        return oneWordRepository.findAll(pageable);
    }

    public OneWord addOneWord(OneWord oneWord) {
        if (oneWord != null){
            oneWordRepository.save(oneWord);
        }
        return oneWord;
    }
}

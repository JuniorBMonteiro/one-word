package br.com.bmont.oneWord.service;


import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void updateOneWord(OneWord oneWord) {
        OneWord savedOneWord = getByWord(oneWord.getWord());
        savedOneWord.setWordTranslation(oneWord.getWordTranslation());
        savedOneWord.setEnglishText(oneWord.getEnglishText());
        savedOneWord.setPortugueseText(oneWord.getPortugueseText());
        oneWordRepository.save(savedOneWord);
    }


    public void deleteOneWord(String word){
        oneWordRepository.delete(getByWord(word));
    }

    public OneWord getOneWordRandom() {
        return oneWordRepository.findWordRandom(OneWord.class.getName());
    }
}

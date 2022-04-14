package br.com.bmont.oneWord.service;


import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.exception.BadRequestException;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.repository.OneWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OneWordService {
    private final OneWordRepository oneWordRepository;

    public OneWord getByWord(String word){
        OneWord oneWord = oneWordRepository.findByWord(word);
        if (oneWord != null){
            return oneWord;
        }else{
            throw new BadRequestException("Word not found");
        }
    }

    public Page<OneWord> getAllWords(Pageable pageable){
        return oneWordRepository.findAll(pageable);
    }

    public OneWord addOneWord(OneWordDTO oneWordDTO) {
        if (wordAlreadyExists(oneWordDTO.getWord())) {
            throw new BadRequestException("Word already exists");
        }
        OneWord oneWord = createOneWordByDTO(oneWordDTO);
        return oneWordRepository.save(oneWord);
    }

    public void updateOneWord(OneWordDTO oneWordDTO) {
        // verifica se a word ja existe
        getByWord(oneWordDTO.getWord());
        OneWord oneWord = createOneWordByDTO(oneWordDTO);
        oneWordRepository.save(oneWord);
    }

    public void deleteOneWord(String word){
        oneWordRepository.delete(getByWord(word));
    }

    public OneWord getOneWordRandom() {
        return oneWordRepository.findWordRandom(OneWord.class.getName());
    }

    private boolean wordAlreadyExists(String word){
        return oneWordRepository.findByWord(word) != null;
    }

    private OneWord createOneWordByDTO(OneWordDTO oneWordDTO){
        return OneWord.builder()
                .word(oneWordDTO.getWord().toLowerCase())
                .wordTranslation(oneWordDTO.getWordTranslation().toLowerCase())
                .englishText(oneWordDTO.getEnglishText().toLowerCase())
                .portugueseText(oneWordDTO.getPortugueseText().toLowerCase())
                .className(OneWord.class.getName())
                .build();
    }
}

package br.com.bmont.oneWord.service;


import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.exception.BadRequestException;
import br.com.bmont.oneWord.mapper.ModelMapperConfig;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.repository.OneWordRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OneWordService {
    private final OneWordRepository oneWordRepository;
    private final ModelMapper modelMapper;

    public OneWordDTO getByWord(String word){
        OneWord oneWord = oneWordRepository.findByWord(word);
        if (oneWord != null){
            return modelMapper.map(oneWord, OneWordDTO.class);
        }else{
            throw new BadRequestException("Word not found");
        }
    }

    public OneWordDTO getOneWordRandom() {
        OneWord oneWord = oneWordRepository.findWordRandom(OneWord.class.getName());
        return modelMapper.map(oneWord, OneWordDTO.class);
    }

    public Page<OneWordDTO> getAllWords(Pageable pageable){
        List<OneWordDTO> oneWordDTOList = oneWordRepository.findAll(pageable).stream()
                .map(e -> modelMapper.map(e, OneWordDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(oneWordDTOList);
    }

    public OneWordDTO addOneWord(OneWordDTO oneWordDTO) {
        if (wordAlreadyExists(oneWordDTO.getWord().toLowerCase())) {
            throw new BadRequestException("Word already exists");
        }
        OneWord oneWord = modelMapper.map(oneWordDTO, OneWord.class);
        oneWordRepository.save(oneWord);
        return oneWordDTO;
    }

    public void updateOneWord(OneWordDTO oneWordDTO) {
        // verifica se a word ja existe
        getByWord(oneWordDTO.getWord());
        OneWord oneWord = modelMapper.map(oneWordDTO, OneWord.class);
        oneWordRepository.save(oneWord);
    }

    public void deleteOneWord(String word){
        OneWord oneWord = modelMapper.map(getByWord(word), OneWord.class);
        oneWordRepository.delete(oneWord);
    }

    private boolean wordAlreadyExists(String word){
        return oneWordRepository.findByWord(word) != null;
    }
}

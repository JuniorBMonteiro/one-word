package br.com.bmont.oneWord.util;

import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.model.OneWord;

public class OneWordCreator {

    public static OneWord createOneWord(){
        return OneWord.builder()
                .word("test")
                .wordTranslation("test")
                .englishText("test")
                .portugueseText("test")
                .className(OneWord.class.getName())
                .build();
    }

    public static OneWordDTO createOneWordDTO(){
        return OneWordDTO.builder()
                .word("test")
                .wordTranslation("test")
                .englishText("test")
                .portugueseText("test")
                .build();
    }
}

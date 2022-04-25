package br.com.bmont.oneWord.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class OneWordDTO {
    @NotEmpty(message = "The word cannot be empty")
    private String word;
    @NotEmpty(message = "The word translation cannot be empty")
    private String wordTranslation;
    @NotEmpty(message = "The english text cannot be empty")
    private String englishText;
    @NotEmpty(message = "The portuguese text cannot be empty")
    private String portugueseText;
}

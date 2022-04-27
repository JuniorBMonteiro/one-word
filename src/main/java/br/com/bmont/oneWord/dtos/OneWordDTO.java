package br.com.bmont.oneWord.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneWordDTO {
    @NotEmpty(message = "The word cannot be empty")
    @Schema(description = "This is the word in english", example = "book")
    private String word;
    @Schema(description = "This is the translation in portuguese of the word in english", example = "livro")
    @NotEmpty(message = "The word translation cannot be empty")
    private String translation;
    @Schema(description = "This is the word inserted in context", example = "the book is on the table")
    @NotEmpty(message = "The english text cannot be empty")
    private String englishText;
    @Schema(description = "This is the translation of the example text", example = "o livro está sobre a mesa")
    @NotEmpty(message = "The portuguese text cannot be empty")
    private String portugueseText;
}

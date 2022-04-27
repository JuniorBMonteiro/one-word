package br.com.bmont.oneWord.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotEmpty;


@Document(indexName = "oneword")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
public class OneWord {
    @Id
    @Field(name="word", type = FieldType.Keyword)
    @EqualsAndHashCode.Include
    @NotEmpty(message = "The word cannot be empty")
    private String word;
    @NotEmpty(message = "The word translation cannot be empty")
    private String translation;
    @NotEmpty(message = "The english text cannot be empty")
    private String englishText;
    @NotEmpty(message = "The portuguese text cannot be empty")
    private String portugueseText;
    @Field(name="className")
    private String className;
}
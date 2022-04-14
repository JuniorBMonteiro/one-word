package br.com.bmont.oneWord.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;


@Document(indexName = "oneword")
public class OneWord {
    @Id
    @Field(name="word", type = FieldType.Keyword)
    private String word;
    private String wordTranslation;
    private String englishText;
    private String portugueseText;

    @Field(name="className")
    private String className;
    public OneWord() {
    }

    public OneWord(String word, String wordTranslation, String englishText, String portugueseText) {
        this.word = word;
        this.wordTranslation = wordTranslation;
        this.englishText = englishText;
        this.portugueseText = portugueseText;
        this.className = OneWord.class.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneWord oneWord = (OneWord) o;
        return Objects.equals(word, oneWord.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getPortugueseText() {
        return portugueseText;
    }

    public void setPortugueseText(String portugueseText) {
        this.portugueseText = portugueseText;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

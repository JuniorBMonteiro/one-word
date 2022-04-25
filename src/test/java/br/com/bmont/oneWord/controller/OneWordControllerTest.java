package br.com.bmont.oneWord.controller;

import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.service.OneWordService;
import br.com.bmont.oneWord.util.OneWordCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class OneWordControllerTest {
    @InjectMocks
    private OneWordController oneWordController;

    @Mock
    private OneWordService oneWordServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<OneWord> oneWordPage = new PageImpl<>(List.of(OneWordCreator.createOneWord()));
        BDDMockito.when(oneWordServiceMock.getAllWords(ArgumentMatchers.any()))
                .thenReturn(oneWordPage);

        BDDMockito.when(oneWordServiceMock.getOneWordRandom())
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.when(oneWordServiceMock.getByWord(ArgumentMatchers.anyString()))
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.when(oneWordServiceMock.addOneWord(ArgumentMatchers.any(OneWordDTO.class)))
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.doNothing().when(oneWordServiceMock).updateOneWord(ArgumentMatchers.any(OneWordDTO.class));

        BDDMockito.doNothing().when(oneWordServiceMock).deleteOneWord(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Get All returns list of OneWord inside page object when successful")
    void getAll_ReturnsListOfOneWordInsidePageObject_WhenSuccessful() {
        String expectedWord = OneWordCreator.createOneWord().getWord();
        Page<OneWord> oneWordPage = oneWordController.getAllWords(null).getBody();
        Assertions.assertThat(oneWordPage).isNotNull();
        Assertions.assertThat(oneWordPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(oneWordPage.toList().get(0).getWord()).isEqualTo(expectedWord);
    }

    @Test
    @DisplayName("Get by word returns OneWord when successful")
    void getByWord_ReturnsOneWord_WhenSuccessful(){
        String expectedWord = OneWordCreator.createOneWord().getWord();
        OneWord oneWord = oneWordController.getByWord(expectedWord).getBody();
        Assertions.assertThat(oneWord).isNotNull();
        Assertions.assertThat(oneWord.getWord())
                .isNotEmpty()
                .isEqualTo(expectedWord);
    }


    @Test
    @DisplayName("Get random returns OneWord when successful")
    void getRandom_ReturnsOneWord_WhenSuccessful(){
        String expectedWord = OneWordCreator.createOneWord().getWord();
        OneWord oneWord = oneWordController.getOneWordRandom().getBody();
        Assertions.assertThat(oneWord).isNotNull();
        Assertions.assertThat(oneWord.getWord()).isNotEmpty();
        Assertions.assertThat(oneWord.getWord()).isEqualTo(expectedWord);
    }

    @Test
    @DisplayName("Add returns OneWord when successful")
    void add_Returns_OneWord_When_Successful(){
        OneWordDTO expectedWord = OneWordCreator.createOneWordDTO();
        OneWord oneWord = oneWordController.addOneWord(expectedWord).getBody();
        Assertions.assertThat(oneWord).isNotNull();
        Assertions.assertThat(oneWord.getWord()).isNotEmpty();
        Assertions.assertThat(oneWord.getWord()).isEqualTo(OneWordCreator.createOneWord().getWord());
    }

    @Test
    @DisplayName("Update OneWord when successful")
    void update_updatesOneWord_WhenSuccessful(){
        Assertions.assertThatCode(() -> oneWordController.updateOneWord(OneWordCreator.createOneWordDTO()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = oneWordController.updateOneWord(OneWordCreator.createOneWordDTO());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete OneWord when successful")
    void delete_RemovesOneWord_WhenSuccessful(){
        Assertions.assertThatCode(() -> oneWordController.deleteOneWord("teste"))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = oneWordController.deleteOneWord("teste");
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
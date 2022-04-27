package br.com.bmont.oneWord.service;

import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.exception.BadRequestException;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.repository.OneWordRepository;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class OneWordServiceTest {

    @InjectMocks
    private OneWordService oneWordService;

    @Mock
    private OneWordRepository oneWordRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @BeforeEach
    void setUp(){
        OneWordDTO oneWordDTO = OneWordCreator.createOneWordDTO();

        BDDMockito.when(modelMapperMock.map(ArgumentMatchers.any(OneWord.class), ArgumentMatchers.any()))
                .thenReturn(oneWordDTO);

        PageImpl<OneWord> oneWordPage = new PageImpl<>(List.of(OneWordCreator.createOneWord()));

        BDDMockito.when(oneWordRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(oneWordPage);

        BDDMockito.when(oneWordRepositoryMock.findByWord(ArgumentMatchers.anyString()))
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.when(oneWordRepositoryMock.findWordRandom(ArgumentMatchers.anyString()))
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.when(oneWordRepositoryMock.save(ArgumentMatchers.any(OneWord.class)))
                .thenReturn(OneWordCreator.createOneWord());

        BDDMockito.doNothing().when(oneWordRepositoryMock)
                .delete(ArgumentMatchers.any(OneWord.class));
    }

    @Test
    @DisplayName("Get all returns list of OneWord inside page object when successful")
    void getAll_ReturnsListOfOneWordInsidePageObject_WhenSuccessful(){
        String expectedWord = OneWordCreator.createOneWord().getWord();
        Page<OneWordDTO> oneWordPage = oneWordService.getAllWords(PageRequest.of(1,1));
        Assertions.assertThat(oneWordPage).isNotNull();
        Assertions.assertThat(oneWordPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(oneWordPage.toList().get(0).getWord()).isEqualTo(expectedWord);
    }

    @Test
    @DisplayName("Get by word returns OneWord when successful")
    void getByWord_ReturnsOneWord_WhenSuccessful(){
        String expectedWord = OneWordCreator.createOneWord().getWord();
        OneWordDTO oneWord = oneWordService.getByWord(expectedWord);
        Assertions.assertThat(oneWord).isNotNull();
        Assertions.assertThat(oneWord.getWord())
                .isNotEmpty()
                .isEqualTo(expectedWord);
    }

    @Test
    @DisplayName("Get by word throws BadRequest when word not found")
    void getByWord_ThrowsBadRequest_WhenWordNotFound(){
        BDDMockito.when(oneWordRepositoryMock.findByWord(ArgumentMatchers.anyString()))
                .thenReturn(null);
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> oneWordService.getByWord("teste"))
                .withMessageContaining("Word not found");
    }

    @Test
    @DisplayName("Get random returns OneWord when successful")
    void getRandom_ReturnsOneWord_WhenSuccessful(){
        String expectedWord = OneWordCreator.createOneWord().getWord();
        OneWordDTO oneWord = oneWordService.getOneWordRandom();
        Assertions.assertThat(oneWord).isNotNull();
        Assertions.assertThat(oneWord.getWord())
                .isNotEmpty()
                .isEqualTo(expectedWord);
    }

    @Test
    @DisplayName("Add returns OneWord when successful")
    void add_Returns_OneWord_When_Successful(){
        OneWord oneWord = OneWordCreator.createOneWord();
        BDDMockito.when(modelMapperMock.map(ArgumentMatchers.any(OneWordDTO.class), ArgumentMatchers.any()))
                .thenReturn(oneWord);
        // simula que a word não existe
        BDDMockito.when(oneWordRepositoryMock.findByWord(ArgumentMatchers.anyString()))
                .thenReturn(null);
        OneWordDTO expectedWord = OneWordCreator.createOneWordDTO();
        OneWordDTO oneWordDTO = oneWordService.addOneWord(expectedWord);
        Assertions.assertThat(oneWordDTO).isNotNull();
        Assertions.assertThat(oneWordDTO.getWord()).isNotEmpty();
        Assertions.assertThat(oneWordDTO.getWord()).isEqualTo(OneWordCreator.createOneWord().getWord());
    }

    @Test
    @DisplayName("Add returns BadRequest when already exists")
    void add_Returns_BadRequest_WhenAlreadyExists(){
        // simula que a word já existe
        OneWordDTO expectedWord = OneWordCreator.createOneWordDTO();
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                        .isThrownBy(() -> oneWordService.addOneWord(expectedWord))
                        .withMessageContaining("Word already exists");
    }

    @Test
    @DisplayName("Update OneWord when successful")
    void update_updatesOneWord_WhenSuccessful(){
        OneWord oneWord = OneWordCreator.createOneWord();
        BDDMockito.when(modelMapperMock.map(ArgumentMatchers.any(OneWordDTO.class), ArgumentMatchers.any()))
                .thenReturn(oneWord);
        Assertions.assertThatCode(() -> oneWordService.updateOneWord(OneWordCreator.createOneWordDTO()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete OneWord when successful")
    void delete_RemovesOneWord_WhenSuccessful(){
        OneWord oneWord = OneWordCreator.createOneWord();
        BDDMockito.when(modelMapperMock.map(ArgumentMatchers.any(OneWordDTO.class), ArgumentMatchers.any()))
                .thenReturn(oneWord);
        Assertions.assertThatCode(() -> oneWordService.deleteOneWord("teste"))
                .doesNotThrowAnyException();
    }
}
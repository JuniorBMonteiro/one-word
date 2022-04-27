package br.com.bmont.oneWord.controller;

import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.service.OneWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/one-word")
@RequiredArgsConstructor
public class OneWordController {
    private final OneWordService oneWordService;

    @GetMapping("/{word}")
    @Operation(summary = "Get OneWord by Word")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Word Does Not Exist")
    })
    public ResponseEntity<OneWordDTO> getByWord(@PathVariable String word){
        return new ResponseEntity<>(oneWordService.getByWord(word), HttpStatus.OK);
    }

    @GetMapping("/random")
    @Operation(summary = "Get OneWord random")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
    })
    public ResponseEntity<OneWordDTO> getOneWordRandom(){
        return new ResponseEntity<>(oneWordService.getOneWordRandom(), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "List all OneWords paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
    })
    public ResponseEntity<Page<OneWordDTO>> getAllWords(@ParameterObject Pageable pageable){
        return new ResponseEntity<>(oneWordService.getAllWords(pageable), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add OneWord")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Word Already exists")
    })
    public ResponseEntity<OneWordDTO> addOneWord(@Valid @RequestBody OneWordDTO oneWord){
        return new ResponseEntity<>(oneWordService.addOneWord(oneWord), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update OneWord ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Word Does Not Exist")
    })
    public ResponseEntity<Void> updateOneWord(@Valid @RequestBody OneWordDTO oneWord){
        oneWordService.updateOneWord(oneWord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{word}")
    @Operation(summary = "Delete OneWord by Word")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When OneWord Does Not Exist")
    })
    public ResponseEntity<Void> deleteOneWord(@PathVariable String word){
        oneWordService.deleteOneWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package br.com.bmont.oneWord.controller;

import br.com.bmont.oneWord.dtos.OneWordDTO;
import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.service.OneWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<OneWord> getByWord(@PathVariable String word){
        return new ResponseEntity<>(oneWordService.getByWord(word), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<OneWord> getOneWordRandom(){
        return new ResponseEntity<>(oneWordService.getOneWordRandom(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OneWord>> getAllWords(Pageable pageable){
        return new ResponseEntity<>(oneWordService.getAllWords(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OneWord> addOneWord(@Valid @RequestBody OneWordDTO oneWord){
        return new ResponseEntity<>(oneWordService.addOneWord(oneWord), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OneWord> updateOneWord(@Valid @RequestBody OneWordDTO oneWord){
        oneWordService.updateOneWord(oneWord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<OneWord> deleteOneWord(@PathVariable String word){
        oneWordService.deleteOneWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

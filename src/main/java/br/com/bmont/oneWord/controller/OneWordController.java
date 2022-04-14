package br.com.bmont.oneWord.controller;

import br.com.bmont.oneWord.model.OneWord;
import br.com.bmont.oneWord.service.OneWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/one-word")
public class OneWordController {
    private OneWordService oneWordService;

    @Autowired
    public OneWordController(OneWordService oneWordService) {
        this.oneWordService = oneWordService;
    }

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
    public ResponseEntity<OneWord> addOneWord(@RequestBody OneWord oneWord){
        return new ResponseEntity<>(oneWordService.addOneWord(oneWord), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OneWord> updateOneWord(@RequestBody OneWord oneWord){
        oneWordService.updateOneWord(oneWord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<OneWord> deleteOneWord(@PathVariable String word){
        oneWordService.deleteOneWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

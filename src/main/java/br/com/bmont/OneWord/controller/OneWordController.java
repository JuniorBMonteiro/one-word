package br.com.bmont.OneWord.controller;

import br.com.bmont.OneWord.model.OneWord;
import br.com.bmont.OneWord.service.OneWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<OneWord>> getAllWords(Pageable pageable){
        return new ResponseEntity<>(oneWordService.getAllWords(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OneWord> addOneWord(@RequestBody OneWord oneWord){
        return new ResponseEntity<>(oneWordService.addOneWord(oneWord), HttpStatus.CREATED);
    }
}

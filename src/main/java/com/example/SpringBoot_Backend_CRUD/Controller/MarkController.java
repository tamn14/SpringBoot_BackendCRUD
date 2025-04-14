package com.example.SpringBoot_Backend_CRUD.Controller;

import com.example.SpringBoot_Backend_CRUD.DTO.MarkDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Mark;
import com.example.SpringBoot_Backend_CRUD.Repository.CourseRepository;
import com.example.SpringBoot_Backend_CRUD.Service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mark")
public class MarkController {
    private MarkService markService ;
    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }
    @GetMapping
    public List<MarkDTO> getAllMark () {
        return markService.findAll() ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<MarkDTO> getMarkByID(@PathVariable int id) {
        MarkDTO markDTO = markService.findMark(id) ;
        return ResponseEntity.ok(markDTO) ;

    }
    @PostMapping
    public ResponseEntity<MarkDTO> addMark(@RequestBody Mark mark) {
        MarkDTO markDTO = markService.AddMark(mark) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(markDTO) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<MarkDTO> updateMark(@RequestBody Mark mark , @PathVariable int id) {
        MarkDTO markDTO = markService.UpdateMark(id , mark) ;
        return ResponseEntity.ok(markDTO) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MarkDTO> deleteMark(@PathVariable int id) {
        markService.RemoveMark(id);
        return ResponseEntity.ok().build();
    }
}

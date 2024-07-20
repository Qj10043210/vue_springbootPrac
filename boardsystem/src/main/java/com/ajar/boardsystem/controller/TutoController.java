package com.ajar.boardsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajar.boardsystem.model.Tuto;
import com.ajar.boardsystem.repository.TutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutoController {
    
    @Autowired
    TutoRepository tr;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tuto>> getAllTutorials(@RequestParam(required = false) String title) {
        try{
            List<Tuto> tutos = new ArrayList<Tuto>();
            if(title == null){
                tr.findAll().forEach(tutos::add);
            }
            else{
                tr.findByTitleContaining(title).forEach(tutos::add);
            }

            if(tutos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutos, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tuto> getTutorialsById(@PathVariable("id") Long id) {
        Optional<Tuto> tutoData = tr.findById(id);
        if(tutoData.isPresent()){
            return new ResponseEntity<>(tutoData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

    @PostMapping("/tutorials")
    public ResponseEntity<Tuto> postTutorials(@RequestBody Tuto tutorial) {
        try{
            Tuto newTutorial = tr.save(new Tuto(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tuto> updateTutorials(@PathVariable("id") Long id, @RequestBody Tuto entity) {
        Optional<Tuto> tutoData = tr.findById(id);
        try{
            if(tutoData.isPresent()){
                Tuto newTutorial = tutoData.get();
                newTutorial.setTitle(entity.getTitle());
                newTutorial.setDescription(entity.getDescription());
                newTutorial.setPublished(entity.isPublished());
                return new ResponseEntity<>(tr.save(newTutorial), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorials(@PathVariable("id") Long id){
        try{
            tr.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAll(){
        tr.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


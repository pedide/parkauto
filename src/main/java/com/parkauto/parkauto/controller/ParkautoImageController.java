package com.parkauto.parkauto.controller;

import com.parkauto.parkauto.entity.ParkautoImages;
import com.parkauto.parkauto.service.ParkautoImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ParkautoImageController {
    @Autowired
    ParkautoImagesService parkautoImagesService;

    @PostMapping("/parkautoImages")
    public ParkautoImages createParkautoImages(@Validated @RequestBody(required = false) ParkautoImages parkautoImages) {
        return parkautoImagesService.saveParkautoImages(parkautoImages);
    }

    @GetMapping("/parkautoImages")
    public List<ParkautoImages> getAllParkautoImages(){
        return parkautoImagesService.getParkautoImages();

    }

    @GetMapping("/parkautoImages/{idParkautoImages}")
    public ResponseEntity findParkautoImagesByid(@PathVariable(name="idParkautoImages") Long idParkautoImages) {

        if(idParkautoImages == null) {
            return ResponseEntity.badRequest().body("Cannot retreive véhicule with null ID");
        }
        ParkautoImages parkautoImages = parkautoImagesService.getParkautoImagesByid(idParkautoImages);
        if(parkautoImages==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(parkautoImages);
    }


    @DeleteMapping("/parkautoImages/{idParkautoImages}")
    public ResponseEntity<ParkautoImages> deleteParkautoImages(@Validated @PathVariable(name="idParkautoImages") Long idParkautoImages) {


        ParkautoImages parkautoImages = parkautoImagesService.getParkautoImagesByid(idParkautoImages);
        if(parkautoImages==null) {
            return ResponseEntity.notFound().build();
        }
        parkautoImagesService.deleteParkautoImages(parkautoImages);
        return ResponseEntity.ok().body(parkautoImages);
    }
}

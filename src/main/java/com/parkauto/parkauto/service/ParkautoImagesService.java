package com.parkauto.parkauto.service;

import com.parkauto.parkauto.entity.ParkautoImages;
import com.parkauto.parkauto.repository.IParkautoImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkautoImagesService {
    @Autowired
    IParkautoImagesRepository parkautoImagesRepository;

    //Liste des parkautoImagess
    public List<ParkautoImages> getParkautoImages(){
        return parkautoImagesRepository.findAll();
    }

    //Save
    public ParkautoImages saveParkautoImages(ParkautoImages parkautoImages) {
        return parkautoImagesRepository.save(parkautoImages);
    }

    //get a ParkautoImages
    public ParkautoImages getParkautoImagesByid(Long idCami) {
        return parkautoImagesRepository.findById(idCami).get();
    }

    //Delete a parkautoImages
    public void deleteParkautoImages(ParkautoImages parkautoImages) {
        parkautoImagesRepository.delete(parkautoImages);
    }

}

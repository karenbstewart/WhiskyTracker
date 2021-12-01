package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

//    @GetMapping(value = "/whisky")
//    public ResponseEntity<List<Whisky>> getAllWhiskies(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/whisky")
    public ResponseEntity<List<Whisky>> findThatHaveAParticularYear(
            @RequestParam(name="year",required = false) Integer year){
        if(year!= null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    // http://localhost:8080/whisky1?age=1&distillery=Talisker

    @GetMapping(value = "/whisky1")
    public ResponseEntity<List<Whisky>> findWhiskeyByAgeAndDistilleryName(
            @RequestParam Integer age, @RequestParam String distillery){
        return new ResponseEntity<>(whiskyRepository.findByAgeAndDistilleryName(age, distillery), HttpStatus.OK);
    }

    //http://localhost:8080/whisky2?distillery=Highland

    @GetMapping(value = "/whisky2")
    public ResponseEntity<List<Whisky>> findWhiskiesFromAParticularRegion(
            @RequestParam String distillery){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(distillery), HttpStatus.OK);
    }


}

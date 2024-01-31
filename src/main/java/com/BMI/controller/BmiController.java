package com.BMI.controller;

import com.BMI.exception.CustomRuntimeException;
import com.BMI.service.BmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bmi"})
@CrossOrigin({"http://localhost:3000"})
public class BmiController {
    @Autowired
    private BmiService bmiService;

    public BmiController() {
    }

    @GetMapping
    public List<String> BmiCal(@RequestParam("weight") double weight, @RequestParam("height") double height) throws CustomRuntimeException {
        return bmiService.giveSuggestion(weight, height);
    }
}
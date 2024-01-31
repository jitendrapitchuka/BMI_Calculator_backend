package com.BMI.service;



import com.BMI.exception.CustomRuntimeException;
import com.BMI.repo.BmiRepo;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    @Autowired
    private BmiRepo bmiRepo;

    public BmiService() {
    }

    public static int randomNumber(int minimum, int maximum) {
        Random random = new Random();
        int randomNumber = random.nextInt(maximum - minimum + 1) + minimum;
        return randomNumber;
    }

    public List<String> giveSuggestion(double weight, double height) throws CustomRuntimeException {
        if (height ==0 || weight ==0) {
            throw new CustomRuntimeException();
        }
            List<String> l = new ArrayList();
            height /= 100.0;
            double result = weight / (height * height);
            byte[] bytes;
            String s="";
            if (result < 18.5) {
                l.add("UnderWeight");
                bytes = this.bmiRepo.findVideoDataById(randomNumber(2, 3));
                s = Base64.getEncoder().encodeToString(bytes);
                l.add(s);
            } else if (result >= 18.5 && result <= 24.9) {
                l.add("Normal weight (Healthy)");
                bytes = this.bmiRepo.findVideoDataById(1);
                s = Base64.getEncoder().encodeToString(bytes);
                l.add(s);
            } else if (result >= 25.0 && result <= 29.9) {
                bytes = this.bmiRepo.findVideoDataById(randomNumber(4, 5));
                s = Base64.getEncoder().encodeToString(bytes);
                l.add("Overweight");
                l.add(s);
            } else if (result >= 30.0) {
                bytes = this.bmiRepo.findVideoDataById(randomNumber(6, 7));
                s = Base64.getEncoder().encodeToString(bytes);
                l.add("Obese");
                l.add(s);
            }

            return l;
        }
    }


package com.BMI.service;

import com.BMI.exception.CustomRuntimeException;
import com.BMI.repo.BmiRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BmiServiceTest {

    @Mock
    private BmiRepo bmiRepo;

    @InjectMocks
    private BmiService bmiService;


    @Test
    public void weightAndHeightZeroException(){
            double weight=0;
            double height =0;

        Assertions.assertThrows(CustomRuntimeException.class,()-> bmiService.giveSuggestion(weight,height));
    }

    @Test
    public void weightOrHeightZeroException(){
        double weight=0;
        double height =165;

        Assertions.assertThrows(CustomRuntimeException.class,()-> bmiService.giveSuggestion(weight,height));
    }

    @Test
    public void testUnderweight() throws CustomRuntimeException {
        when(bmiRepo.findVideoDataById(anyInt())).thenReturn(new byte[] {});

        // Test case for underweight
        List<String> result = bmiService.giveSuggestion(50.0, 170.0);

        assertEquals(2, result.size());
        assertEquals("UnderWeight", result.get(0));
        assertNotNull(result.get(1));
    }

    @Test
    public void testObese() throws CustomRuntimeException {
        when(bmiRepo.findVideoDataById(anyInt())).thenReturn(new byte[] {});

        // Test case for underweight
        List<String> result = bmiService.giveSuggestion(100.0, 160.0);

        assertEquals(2, result.size());
        assertEquals("Obese", result.get(0));
        assertNotNull(result.get(1));
    }

    @Test
    public void testOverWeight() throws CustomRuntimeException {
        when(bmiRepo.findVideoDataById(anyInt())).thenReturn(new byte[] {});

        // Test case for underweight
        List<String> result = bmiService.giveSuggestion(85, 180);

        assertEquals(2, result.size());
        assertEquals("Overweight", result.get(0));
        assertNotNull(result.get(1));
    }

    @Test
    public void testNormalWeight() throws CustomRuntimeException {
        when(bmiRepo.findVideoDataById(anyInt())).thenReturn(new byte[] {});

        // Test case for underweight
        List<String> result = bmiService.giveSuggestion(65, 165);

        assertEquals(2, result.size());
        assertEquals("Normal weight (Healthy)", result.get(0));
        assertNotNull(result.get(1));
    }


}

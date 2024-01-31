package com.BMI.repo;



import com.BMI.entity.Bmi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BmiRepo extends JpaRepository<Bmi, Integer> {
    @Query("SELECT videoData FROM Bmi WHERE id = :id")
    byte[] findVideoDataById(int id);
}

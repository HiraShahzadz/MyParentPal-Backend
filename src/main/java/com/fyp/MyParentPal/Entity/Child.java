package com.fyp.MyParentPal.Entity;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Child extends User {
    private LocalDate dob;
    private String gender;
    private List<String> tags;
    private String parentId;
    // Method to calculate age
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dob, currentDate).getYears();
    }

}


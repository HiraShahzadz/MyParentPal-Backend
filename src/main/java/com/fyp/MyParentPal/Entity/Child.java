package com.fyp.MyParentPal.Entity;

import java.time.LocalDate;
import java.time.Period;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
// constructors, getters, setters, and other methods




    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    // Method to calculate age
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dob, currentDate).getYears();
    }
}


package models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ExpenseMetadata {

    private String name;
    private String imgUrl;
    private String notes;

}

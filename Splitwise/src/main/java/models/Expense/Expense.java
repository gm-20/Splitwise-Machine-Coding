package models.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.ExpenseMetadata;
import models.Split.Split;
import models.User;

import java.util.List;

/*
Expense in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users>
<EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>
 */

@Getter
@Setter
public abstract class Expense {
    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetadata metadata;

    public Expense(double amount,User paidBy,List<Split> splits,ExpenseMetadata metadata){
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.metadata = metadata;
    }

    public abstract boolean validate();
}

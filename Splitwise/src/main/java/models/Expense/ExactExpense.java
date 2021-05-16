package models.Expense;

import models.ExpenseMetadata;
import models.Split.ExactSplit;
import models.Split.Split;
import models.User;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
        super(amount, paidBy, splits, metadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()){
            if (!(split instanceof ExactSplit)){
                return false;
            }
        }
        return true;
    }
}

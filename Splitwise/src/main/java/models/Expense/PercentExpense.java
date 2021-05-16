package models.Expense;

import models.ExpenseMetadata;
import models.Split.PercentSplit;
import models.Split.Split;
import models.User;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
        super(amount, paidBy, splits, metadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()){
            if (!(split instanceof PercentSplit)){
                return false;
            }
        }
        return true;
    }
}

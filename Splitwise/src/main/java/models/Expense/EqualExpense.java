package models.Expense;

import models.ExpenseMetadata;
import models.Split.EqualSplit;
import models.Split.Split;
import models.User;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)){
                return false;
            }
        }
        return true;
    }
}

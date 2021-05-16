package services;

import models.Expense.*;
import models.ExpenseMetadata;
import models.Split.PercentSplit;
import models.Split.Split;
import models.User;

import java.util.List;

public class ExpenseService {

    public static Expense createExpense(ExpenseType expenseType
            , double amount
            , User paidBy
            , List<Split> splits
            , ExpenseMetadata expenseMetadata){
        switch (expenseType){

            case EXACT:
                return new ExactExpense(amount,paidBy,splits,expenseMetadata);
            case EQUAL:
                //100 Rs
                int totalSplits = splits.size(); //3
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;//33.33
                //for every user set same value
                for (Split split : splits){
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (amount-splitAmount*totalSplits));
                return new EqualExpense(amount, paidBy, splits, expenseMetadata);

            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getAmount())/100.0);
                }
                return new PercentExpense(amount,paidBy,splits,expenseMetadata);

            default:
                return null;

        }
    }

}

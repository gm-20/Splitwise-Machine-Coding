import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import guice.BasicModule;
import models.Expense.ExpenseType;
import models.Split.EqualSplit;
import models.Split.ExactSplit;
import models.Split.PercentSplit;
import models.Split.Split;
import models.User;
import services.ExpenseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ExpenseManager expenseManager;

    @Inject
    Main(ExpenseManager expenseManager) {
        Main.expenseManager = expenseManager;
    }

    /*
    Input

You can create a few users in your main method. No need to take it as input.

There will be 3 types of input:

Expense in the format: EXPENSE <user-id-of-person-who-paid> <amount> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

Show balances for all: SHOW

Show balances for a single user: SHOW <user-id>
     */

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new BasicModule());
        Main main = injector.getInstance(Main.class);

        expenseManager.test();

        expenseManager.addUser(new User("u1", "GM", "gm@gmail.com", "9755908329"));
        expenseManager.addUser(new User("u2", "SM", "sm@gmail.com", "123456789"));
        expenseManager.addUser(new User("u3", "DM", "dm@gmail.com", "987654321"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandLine = scanner.nextLine();
            String[] commands = commandLine.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW":
                    if (commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE":
                    //u4 1200 4 u1 u2 u3 u4 SHARE 2 1 1 1
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[noOfUsers + 4];
                    List<Split> splits = new ArrayList<Split>();

                    switch (expenseType) {

                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.users.get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new ExactSplit(expenseManager.users.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseManager.users.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                            break;
                    }
                default:
                    System.out.println("Not correct Command");
            }

        }


    }
}

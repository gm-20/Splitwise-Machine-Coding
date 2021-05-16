package models.Split;

import lombok.Getter;
import lombok.Setter;
import models.User;

@Getter
@Setter
abstract public class Split {
    private User user;
    double amount;

    public Split(User user){
        this.user = user;
    }

}

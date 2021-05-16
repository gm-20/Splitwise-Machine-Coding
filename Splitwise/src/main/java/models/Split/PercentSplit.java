package models.Split;

import lombok.Getter;
import lombok.Setter;
import models.User;

@Getter
@Setter
public class PercentSplit extends Split{

    double percent;

    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }
}

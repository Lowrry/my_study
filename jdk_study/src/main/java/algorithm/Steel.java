package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/26<p/>
 * Time: 16:59<p/>
 */
public interface Steel {

    Result cut(int length);

}

class Result {
    int value = 0;
    List<Integer> cutPlans = new ArrayList<>();

    public Result(int value, List<Integer> cutPlans) {
        this.value = value;
        this.cutPlans = cutPlans;
    }

    public Result() {
    }
}

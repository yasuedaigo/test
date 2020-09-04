package main.comparator;

import java.util.Comparator;
import main.job.Player;

//AGIソート用
public class AGIComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        int ret = p1.getAGI() - p2.getAGI();
        ret = ret*-1;
        return ret;
    }
}

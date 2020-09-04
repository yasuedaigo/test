package main.comparator;

import java.util.Comparator;
import main.job.Player;

//HPソート用
public class HPComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        int ret = p1.getHP() - p2.getHP();
        return ret;
    }
}

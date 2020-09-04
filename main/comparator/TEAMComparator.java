package main.comparator;

import java.util.Comparator;
import main.job.Player;

//HPソート用
public class TEAMComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        int ret = p1.getTeam() - p2.getTeam();
        return ret;
    }
}

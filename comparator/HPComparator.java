package comparator;

import java.util.Random;
import java.util.Comparator;

import job.Player;

//HPソート用
public class HPComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        int ret = p1.GetHP() - p2.GetHP();
        if (ret == 0) {
        	while(ret == 0){
        	Random r = new Random();
            ret = 1-r.nextInt(3);
        	}
        }
        return ret;
    }
}

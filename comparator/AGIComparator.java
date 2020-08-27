package comparator;

import java.util.Random;
import java.util.Comparator;

import job.Player;

//AGIソート用
public class AGIComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        int ret = p1.GetAGI() - p2.GetAGI();
        if (ret == 0) {
        	while(ret == 0){
        	Random r = new Random();
            ret = 1-r.nextInt(3);
        	}
        }
        ret = ret*-1;
        return ret;
    }
}
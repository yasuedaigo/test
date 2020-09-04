package main.tactics;

import main.job.*;
import main.comparator.*;
import main.game.*;
import java.util.Collections;



public class WizardTactics implements Tactics {

	//魔法使いは体力の少ない敵を優先して攻撃する
	public int attackTarget(Party party,Player attacker,int turnnumber){
		int x = -1;
		Player target = new Player("name");
		//HPでソート
		Collections.sort(party.getMembers(),new HPComparator());

		for(int i=0; i<party.getMembers().size(); i++){
			if(party.getPlayer(i).getTeam() != attacker.getTeam()){
				target = party.getPlayer(i);
				break;
			}
		}

		//AGIでソート
		Collections.sort(party.getMembers(),new AGIComparator());
		for(int i=0; i<party.getMembers().size(); i++){
			if(target.equals(party.getPlayer(i))){
				x = i;
				return x;
			}
		}
	    return x;
	}
}
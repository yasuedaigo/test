package tactics;

import game.Party;

import java.util.Collections;


import job.Player;

import comparator.AGIComparator;
import comparator.HPComparator;

public class WizardTactics implements Tactics {

	//魔法使いは体力の少ない敵を優先して攻撃する
	public int target(Party party,Player attacker,int turnnumber){
		int x = -1;
		Player target = new Player("name");
		//HPでソート
		Collections.sort(party.GetMembers(),new HPComparator());

		for(int i=0; i<party.GetMembers().size(); i++){
			if(party.GetPlayer(i).GetTEAM() != attacker.GetTEAM()){
				target = party.GetPlayer(i);
				break;
			}
		}

		//AGIでソート
		Collections.sort(party.GetMembers(),new AGIComparator());
		for(int i=0; i<party.GetMembers().size(); i++){
			if(target.equals(party.GetPlayer(i))){
				x = i;
				return x;
			}
		}
	    return x;
	}
}
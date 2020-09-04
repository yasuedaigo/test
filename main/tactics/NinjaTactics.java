package main.tactics;

import main.job.*;
import java.util.Random;
import main.game.*;


public class NinjaTactics implements Tactics {

	//忍者は相手の戦士を優先して攻撃する
	public int attackTarget(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		//リスト内の敵の戦士の数を数える
		for(int i=0; i<party.getMembers().size(); i++){
			if(party.getPlayer(i).getTeam() != attacker.getTeam()){
				if(party.getPlayer(i).getType() == "戦士" ){
					count++;
			    }
		    }
	    }

		if(count == 0){
			do{
				  x = r.nextInt(party.getMembers().size());
			  }while(attacker.getTeam() == party.getMembers().get(x).getTeam());
		}else if(count >= 1){
			do{
				  x = r.nextInt(party.getMembers().size());
			  }while(attacker.getTeam() == party.getMembers().get(x).getTeam() || party.getMembers().get(x).getType() != "戦士");
		}
		return x;
	}
}
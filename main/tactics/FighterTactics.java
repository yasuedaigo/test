package main.tactics;

import java.util.Random;
import main.job.*;
import main.game.*;

public class FighterTactics implements Tactics {

	//戦士は相手の魔法使いか僧侶を優先して攻撃する
	public int attackTarget(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		//リスト内の敵チームの僧侶と魔法使いの数をカウント
		for(int i=0; i<party.getMembers().size(); i++){
			if(party.getPlayer(i).getTeam() != attacker.getTeam()){
				if(party.getPlayer(i).getType() == "僧侶" || party.getPlayer(i).getType() == "魔法使い" ){
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
			  }while(attacker.getTeam() == party.getMembers().get(x).getTeam() || party.getMembers().get(x).getType() != "魔法使い" && party.getMembers().get(x).getType() != "僧侶");
		}
		return x;
	}
}
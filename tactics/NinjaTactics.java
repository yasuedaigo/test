package tactics;

import game.Party;

import java.util.Random;

import job.Player;

public class NinjaTactics implements Tactics {

	//忍者は相手の戦士を優先して攻撃する
	public int target(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		//リスト内の敵の戦士の数を数える
		for(int i=0; i<party.GetMembers().size(); i++){
			if(party.GetPlayer(i).GetTEAM() != attacker.GetTEAM()){
				if(party.GetPlayer(i).GetTYPE() == "戦士" ){
					count++;
			    }
		    }
	    }

		if(count == 0){
			do{
				  x = r.nextInt(party.GetMembers().size());
			  }while(attacker.GetTEAM() == party.GetMembers().get(x).GetTEAM());
		}else if(count >= 1){
			do{
				  x = r.nextInt(party.GetMembers().size());
			  }while(attacker.GetTEAM() == party.GetMembers().get(x).GetTEAM() || party.GetMembers().get(x).GetTYPE() != "戦士");
		}
		return x;
	}
}
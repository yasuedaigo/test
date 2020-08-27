package tactics;

import java.util.Random;

import game.Party;
import job.Player;

public class FighterTactics implements Tactics {

	//戦士は相手の魔法使いか僧侶を優先して攻撃する
	public int target(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		//リスト内の敵チームの僧侶と魔法使いの数をカウント
		for(int i=0; i<party.GetMembers().size(); i++){
			if(party.GetPlayer(i).GetTEAM() != attacker.GetTEAM()){
				if(party.GetPlayer(i).GetTYPE() == "僧侶" || party.GetPlayer(i).GetTYPE() == "魔法使い" ){
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
			  }while(attacker.GetTEAM() == party.GetMembers().get(x).GetTEAM() || party.GetMembers().get(x).GetTYPE() != "魔法使い" && party.GetMembers().get(x).GetTYPE() != "僧侶");
		}
		return x;
	}
}
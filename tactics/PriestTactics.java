package tactics;

import game.Party;

import java.util.Random;

import job.Player;


public class PriestTactics implements Tactics {

	//僧侶はHP50以下の味方にヒールを使う
	public int target(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		
		//1ターン目は必ず敵チームのプレイヤーを狙う
        if(turnnumber == 1){
        	do{
				  x = r.nextInt(party.GetMembers().size());
			  }while(attacker.GetTEAM() == party.GetMembers().get(x).GetTEAM());
        	return x;
        }
		
        //自チームのHP50以下のプレイヤーをカウントする
		for(int i=0; i<party.GetMembers().size(); i++){
			if(party.GetPlayer(i).GetTEAM() == attacker.GetTEAM()){
				if(party.GetPlayer(i).GetHP() <= 50){
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
			  }while(party.GetPlayer(x).GetTEAM() != attacker.GetTEAM() && party.GetPlayer(x).GetHP() >= 50);
		}
		return x;
	}
}	
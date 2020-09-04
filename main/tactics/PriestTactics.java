package main.tactics;

import main.job.*;
import main.game.*;
import java.util.Random;




public class PriestTactics implements Tactics {

	//僧侶はHP50以下の味方にヒールを使う
	public int attackTarget(Party party,Player attacker,int turnnumber){
		Random r = new Random();
		int count = 0;
		int x = -1;
		
		//1ターン目は必ず敵チームのプレイヤーを狙う
        if(turnnumber == 1){
        	do{
				  x = r.nextInt(party.getMembers().size());
			  }while(attacker.getTeam() == party.getMembers().get(x).getTeam());
        	return x;
        }
		
        //自チームのHP50以下のプレイヤーをカウントする
		for(int i=0; i<party.getMembers().size(); i++){
			if(party.getPlayer(i).getTeam() == attacker.getTeam()){
				if(party.getPlayer(i).getHP() <= 50){
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
			  }while(party.getPlayer(x).getTeam() != attacker.getTeam() && party.getPlayer(x).getHP() >= 50);
		}
		return x;
	}
}	
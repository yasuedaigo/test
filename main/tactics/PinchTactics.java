package main.tactics;

import main.job.*;
import java.util.Random;
import main.game.*;


public class PinchTactics implements Tactics {

	//チームが残り一人になるとstrを2倍する
	public int attackTarget(Party party,Player attacker,int turnnumber){
		int x;
		Random r = new Random();
		attacker.setSTR(attacker.getSTR()*2);
		do{
			  x = r.nextInt(party.getMembers().size());
		  }while(attacker.getTeam() == party.getMembers().get(x).getTeam());
		return x;
	}
}
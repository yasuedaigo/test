package tactics;

import game.Party;

import java.util.Random;

import job.Player;

public class PinchTactics implements Tactics {

	//チームが残り一人になるとstrを2倍する
	public int target(Party party,Player attacker,int turnnumber){
		int x;
		Random r = new Random();
		attacker.SetSTR(attacker.GetSTR()*2);
		do{
			  x = r.nextInt(party.GetMembers().size());
		  }while(attacker.GetTEAM() == party.GetMembers().get(x).GetTEAM());
		return x;
	}
}
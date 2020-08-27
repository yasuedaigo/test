package tactics;
import game.Party;
import job.Player;

public interface Tactics {

	int target(Party party,Player attacker,int turnumber);

}

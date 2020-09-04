package main.tactics;

import main.job.*;
import main.game.*;

public interface Tactics {

	int attackTarget(Party party,Player attacker,int turnumber);

}

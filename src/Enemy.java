import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Enemy extends Mover {

	public Enemy(){
		setColor(null);
	}
	
	public int function(){
		Grid g = getGrid();
		Location loc = getLocation();
		Location newwloc = loc.getAdjacentLocation(getDirection());
	    if(g.get(newwloc) instanceof MainCharacter){
				Death DEAD = new Death();  //MainCharacter eaten by Enemy ++ put SKULL where MainCharacter was
				DEAD.putSelfInGrid(g, newwloc);
				Empty NOTHING = new Empty();
				NOTHING.putSelfInGrid(g, loc);
				return -1;
			}
	    else {             //enemy randomly rotates and MOVES if empty
			int d = getDirection(); 
			int DEGREES=d;
			while(d==DEGREES){
				Random RR = new Random();
				DEGREES = RR.nextInt(10); //any direction degree multiple of 90
				DEGREES*=90;              //generate random directions
				setDirection(DEGREES);
				if(g.get(newwloc) instanceof Empty){ //if empty, Enemy moves 
					moveTo(newwloc);
					Empty NOTHING = new Empty();
					NOTHING.putSelfInGrid(g, loc);
				}
			}
		}
		return 0;
	}
	
	
}


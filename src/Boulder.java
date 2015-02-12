import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;




import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Boulder extends Mover {

public Boulder(){
    setColor(null);
	}


public int function(){
	Grid<Actor> gr = getGrid();
	Location loc = getLocation();
	Location down = loc.getAdjacentLocation(180);
	Location downAgain = down.getAdjacentLocation(180);

	//statement where BOULDER crushes MainCharacter
	if(gr.get(down) instanceof Empty && gr.get(downAgain) instanceof MainCharacter){ //1 space below is empty, boulder falls
		//if 2nd space has MainCharacter on it, crushes him. Boulder will not fall on MainCharacter if 1 space above(has to be 2)
		moveTo(downAgain);
		Death DEAD = new Death();
		DEAD.putSelfInGrid(gr, downAgain);  //places Death to where MainCharacter was
		Empty NOTHING = new Empty();        //Death replaces Boulder thus needs EMPTY SPOT
		NOTHING.putSelfInGrid(gr, loc);
		return -1;
	}
	else if(gr.get(down) instanceof Empty){
		moveTo(down);
		Empty black= new Empty();
		black.putSelfInGrid(gr, down.getAdjacentLocation(0)); 	
	}
	
	return 0;
}
}

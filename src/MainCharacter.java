import info.gridworld.actor.ActorWorld;


import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;


import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;



public class MainCharacter extends Bug {
	
private int diamondsCount;
		
	public MainCharacter(){
		setColor(null);
			}
			
	public int getDiamond(){
		return diamondsCount;
	}
			
	public void move(){
		
				 Grid<Actor> g = getGrid();   
				 if (g == null) 
				      return; 
				    Location loc = getLocation(); 
				    Location next = loc.getAdjacentLocation(getDirection()); 
				    if (g.get(next) instanceof Boulder || g.get(next) instanceof Enemy || g.get(next) instanceof Wall ){
				    	return;
				    } 
				    if (g.get(next) instanceof Diamond){
				    	diamondsCount++;
				    }
				    
				    if (g.isValid(next)) {
				    	moveTo(next);
				    	Empty black = new Empty(); 
					    black.putSelfInGrid(g, loc);
				    }
				    else
				    removeSelfFromGrid(); 
				    
		}
			
			
			
			


	}


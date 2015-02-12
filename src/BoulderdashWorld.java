import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;



public class BoulderdashWorld extends ActorWorld {
    private Timer t;
    private ArrayList<Mover> MovingObjects;
    MainCharacter dude = new MainCharacter();
    int diamondsLeft;
	


    public BoulderdashWorld(){
		
	    t=new Timer(400, new GameMover());  //milliseconds how fast BOULDERS fall
	    setWorld(); //less time, harder the game
	}
	 
	public void setWorld() {
	    setGrid(new BoundedGrid<Actor>(20,20));
	    MovingObjects = new ArrayList<Mover>();
	    makeWalls();
	    setup(t);
	    makeMainCharacter();
        //makeDirt();
        //makeEnemy();
        //makeBoulder();    secondary methods all incorporated into larger SETUP METHOD
        //makeEmpty();
		}
	
	private void makeWalls() {
		// TODO Auto-generated method stub
		int rows=0;
		int columns=0;
		Grid g= this.getGrid();
		                            //makes walls on all four sides
		for(columns=0; columns<g.getNumCols(); columns++){
			Wall w = new Wall();
			add(new Location(0,columns), w);
			}
		for(rows=0; rows<g.getNumRows(); rows++){
			Wall w = new Wall();
			add(new Location (rows, 0), w);
			}
		for(rows=0; rows<g.getNumRows(); rows++){
			Wall w = new Wall();
			add(new Location(rows,g.getNumCols()-1), w);
		}
		for(columns=0; columns<g.getNumCols(); columns++){
			Wall w = new Wall();
			add(new Location(g.getNumRows()-1,columns), w);
		}
		
		}
	
	private void setup(Object requiredData) {
		 //TODO Auto-generated method stub
		Grid g=this.getGrid();
		for (int column = 1; column<g.getNumRows()-1; column++){	//outer bordered by walls
			for (int row = 1; row<g.getNumRows()-1; row++){         //thus rows-1, columns-1
				Random RR = new Random();
				int PERCENTAGE = RR.nextInt(100); //randomly generate percentages (100%)
				if (90<PERCENTAGE && PERCENTAGE<100){                    //around 10% Diamonds
					Diamond GEM = new Diamond();
					add(new Location (row,column), GEM);
				}
				else if (75<PERCENTAGE && PERCENTAGE<90) { //around 15% Boulders
					Boulder ROCK = new Boulder();
					MovingObjects.add(ROCK);
					add(new Location (row,column), ROCK);	
				}
				else if(72<PERCENTAGE && PERCENTAGE<75){   //around 3% enemies
					Enemy FOE = new Enemy();
					MovingObjects.add(FOE);
					add(new Location (row,column), FOE);
				}
				else if (45<PERCENTAGE && PERCENTAGE<72){    //around 27% empty spaces
					Empty BLACK = new Empty();
					add(new Location(row,column), BLACK);
				}
				else{                                           //45% Dirt
					Dirt TERRAIN = new Dirt();
					add(new Location (row,column), TERRAIN);
				}
			}
			
		}
		}
	
		private void makeMainCharacter() {
		// TODO Auto-generated method stub
		Grid g=this.getGrid();
		add(new Location (g.getNumRows()/2, g.getNumCols()/2), dude); 
		//Places MainCharacter at the VERY middle of the map
	}
		
			
	public boolean keyPressed(String s, Location loc){
	    System.out.println(s);
		t.start();
		
		if(s.equals("UP")){
			dude.move();
		}
		if(s.equals("DOWN")){
				dude.setDirection(Location.SOUTH);
				dude.move();
				dude.setDirection(Location.NORTH);
		}
		if(s.equals("RIGHT")){
			dude.setDirection(Location.EAST);
			dude.move();
			dude.setDirection(Location.NORTH);
		}
		if(s.equals("LEFT")){
			dude.setDirection(Location.WEST);
			dude.move();
			dude.setDirection(Location.NORTH);
		}
	
			return true;
}
	
	class GameMover implements ActionListener{

		@Override
		
		public void actionPerformed(ActionEvent arg0) {
		
			int num = 10- dude.getDiamond();   //need 10 Diamonds to win level
			setMessage("Level One. You still need " +num+ " diamonds to win.");
			if(num==0){
				setMessage("LEVEL COMPLETE!!! PROCEED TO PORTAL...CAUTIOUSLY.");
				t.stop();
				Portal DOOR = new Portal();
			    add(new Location(10, 10), DOOR);   //spawns portal in MIDDLE of map, MainCharacter must navigate to portal and "go in"..WIN!
		                                       //NOTE: on the way to portal, MUST NOT GET ANY ADDITIONAL DIAMONDS!!!	
			}
			
			for(int x=0; x<MovingObjects.size(); x++){
			Mover m = MovingObjects.get(x);
			if (m.function() == -1){
				t.stop();
				setMessage("YOU HAVE DIED!!! GAME OVER. ");  //Boulder or Enemy kill MainCharacter
			}
			else if (m.function()==0){
				Location currentPlace = m.getLocation();
				m.moveTo(currentPlace);
			}
			
		}
		show();   //updates
		
		
}
		
		
	}	
//	private void makeEmpty() {
//		// TODO Auto-generated method stub
//		Grid g= this.getGrid(); 
//		
//		for(int x=0; x<50; x++){
//			Random gen = new Random();
//			Empty nothing = new Empty();
//		int rr = gen.nextInt((g.getNumRows()-3))+1;
//		int rc = gen.nextInt((g.getNumCols()-3))+1;
//			add(new Location (rr,rc), nothing);
//		}	
//	}
			
//	private void makeBoulder() {
//		// TODO Auto-generated method stub
//		Grid g= this.getGrid();
//		for (int x=0; x<50; x++){
//			Boulder b = new Boulder();
//			boulders.add(b);
//			Random gen = new Random();
//			int sr = gen.nextInt((g.getNumRows()-3))+1;
//			int sc = gen.nextInt((g.getNumCols()-3))+1;
//			add(new Location (sr, sc), b);
//		}
//}


//	private void makeEnemy() {
//		// TODO Auto-generated method stub
//		Grid g= this.getGrid(); 
//		for(int x=0; x<3; x++){
//		Random gen = new Random();
//		Enemy e = new Enemy();
//		int rr = gen.nextInt((g.getNumRows()-3))+1;
//		int rc = gen.nextInt((g.getNumCols()-3))+1;
//		    add(new Location (rr,rc), e);	
//		}	
//}
//	private void makeDirt() {
//		// TODO Auto-generated method stub
//		Grid g= this.getGrid(); 
//		int rows=0;
//		int columns=0;
//		int x = 0;
//		for(columns=1; columns<g.getNumCols()-1;columns++){
//			for(rows=1; rows<g.getNumRows()-1; rows++){
//				Dirt d=new Dirt();
//				add(new Location(rows, columns), d);
//			}	
//		}
//	}
	

		
		
}		
			
	
	
	
				
	


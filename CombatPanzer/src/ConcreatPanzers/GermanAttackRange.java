package ConcreatPanzers;

import AbstractPanzers.AbstractAttackRange;
import AbstractPanzers.AbstractPanzer;

public class GermanAttackRange extends AbstractAttackRange{

	private final int xrange = 2;
	private final int yrange = 2;
	
	public GermanAttackRange(){}
	
	@Override
	public boolean getAttackRange(AbstractPanzer panzer, int x, int y) {
		if(x<=panzer.getX()+this.xrange && x>=panzer.getX()-this.xrange && y==panzer.getY()){
			return true;
		}else if(y<=panzer.getY()+this.yrange && y>=panzer.getY()-this.yrange && x==panzer.getX()){
			return true;
		}else if(x==panzer.getX()+1 && y == panzer.getY()-2){
			return true;
		}else if(x==panzer.getX()+2 && y == panzer.getY()+1){
			return true;
		}else if(x==panzer.getX()-1 && y == panzer.getY()+2){
			return true;
		}else if(x==panzer.getX()-2 && y == panzer.getY()-1){
			return true;
		}else{
			return false;
		}
	}

}

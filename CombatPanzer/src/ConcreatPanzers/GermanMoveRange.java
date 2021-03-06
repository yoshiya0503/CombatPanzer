package ConcreatPanzers;

import AbstractPanzers.AbstractMoveRange;
import AbstractPanzers.AbstractPanzer;

public class GermanMoveRange extends AbstractMoveRange{
	
	private final int xrange = 3;
	private final int yrange = 3;

	@Override
	public boolean getMoveRange(AbstractPanzer panzer, int x, int y) {
		if(x<=panzer.getX()+this.xrange && x>=panzer.getX()-this.xrange && y==panzer.getY()){
			return true;
		}else if(y<=panzer.getY()+this.yrange && y>=panzer.getY()-this.yrange && x==panzer.getX()){
			return true;
		}else{
			return false;
		}
	}

}

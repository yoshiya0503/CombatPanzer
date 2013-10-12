package ConcreatPanzers;

import AbstractPanzers.*;

public class JapaneseAttackRange extends AbstractAttackRange{
	
	private final int xrange = 2;
	private final int yrange = 2;
	
	public JapaneseAttackRange(){}

	@Override
	public boolean getAttackRange(AbstractPanzer panzer, int x, int y) {
		if(x<=panzer.getX()+this.xrange && x>=panzer.getX()-this.xrange && y==panzer.getY()){
			return true;
		}else if(y<=panzer.getY()+this.yrange && y>=panzer.getY()-this.yrange && x==panzer.getX()){
			return true;
		}else{
			return false;
		}
	}

}

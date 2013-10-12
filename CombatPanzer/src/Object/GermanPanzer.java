package Object;

import AbstractPanzers.*;
import ConcreatPanzers.*;

public class GermanPanzer extends AbstractPanzer{

	//戦車の名前とコスト
	private final String panzername = "German";
	private final static int cost = 60;
	
	//AbstractFactoryで戦車を設計
	private AbstractPanzerFactory gfactory;
	private AbstractMoveRange gmove;
	private AbstractAttackRange grange;
	
	public GermanPanzer() {
		//戦車は見えない領域いておいておく
		super(100,100,cost);
		this.gfactory = new GermanPanzerFactory();
		this.gmove = this.gfactory.createMove();
		this.grange = this.gfactory.createRange();
	}

	@Override
	public boolean movable(int x, int y) {
		if(this.gmove.getMoveRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public boolean attackable(int x, int y) {
		if(this.grange.getAttackRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public String getPanzerName() {
		return this.panzername;
	}

}

package Object;

import AbstractPanzers.*;
import ConcreatPanzers.*;

public class USAPanzer extends AbstractPanzer{

	//戦車の名前とコスト
	private final String panzername = "USA";
	private final static int cost = 50;
	
	//AbstractFactoryで戦車を設計
	private AbstractPanzerFactory ufactory;
	private AbstractMoveRange umove;
	private AbstractAttackRange urange;
	
	public USAPanzer() {
		//戦車はとにかく見えない領域においておく
		super(100,100,cost);
		this.ufactory = new USAPanzerFactory();
		this.umove = this.ufactory.createMove();
		this.urange = this.ufactory.createRange();
	}

	@Override
	public boolean movable(int x, int y) {
		if(this.umove.getMoveRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public boolean attackable(int x, int y) {
		if(this.urange.getAttackRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public String getPanzerName() {
		return this.panzername;
	}

}

package Object;

import AbstractPanzers.*;
import ConcreatPanzers.JapanesePanzerFactory;

public class JapanesePanzer extends AbstractPanzer{

	//戦車の名前とコスト
	private final String panzername = "Japanese";
	private final static int cost = 30;
	
	//AbstractFactoryで戦車を設計
	private AbstractPanzerFactory jfactory;
	private AbstractMoveRange jmove;
	private AbstractAttackRange jrange;
	
	public JapanesePanzer() {
		//戦車はとりあえず見えない領域においておく
		super(100,100,cost);
		jfactory = new JapanesePanzerFactory();
		jrange = jfactory.createRange();
		jmove = jfactory.createMove();
	}
	
	@Override
	public boolean movable(int x,int y){
		if(this.jmove.getMoveRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public boolean attackable(int x, int y) {
		if(this.jrange.getAttackRange(this,x,y))
			return true;
		else
			return false;
	}

	@Override
	public String getPanzerName() {
		return this.panzername;
	}
}

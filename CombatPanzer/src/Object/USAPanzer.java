package Object;

import AbstractPanzers.*;
import ConcreatPanzers.*;

public class USAPanzer extends AbstractPanzer{

	//��Ԃ̖��O�ƃR�X�g
	private final String panzername = "USA";
	private final static int cost = 50;
	
	//AbstractFactory�Ő�Ԃ�݌v
	private AbstractPanzerFactory ufactory;
	private AbstractMoveRange umove;
	private AbstractAttackRange urange;
	
	public USAPanzer() {
		//��Ԃ͂Ƃɂ��������Ȃ��̈�ɂ����Ă���
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

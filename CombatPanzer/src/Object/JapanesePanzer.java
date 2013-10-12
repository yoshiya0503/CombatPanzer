package Object;

import AbstractPanzers.*;
import ConcreatPanzers.JapanesePanzerFactory;

public class JapanesePanzer extends AbstractPanzer{

	//��Ԃ̖��O�ƃR�X�g
	private final String panzername = "Japanese";
	private final static int cost = 30;
	
	//AbstractFactory�Ő�Ԃ�݌v
	private AbstractPanzerFactory jfactory;
	private AbstractMoveRange jmove;
	private AbstractAttackRange jrange;
	
	public JapanesePanzer() {
		//��Ԃ͂Ƃ肠���������Ȃ��̈�ɂ����Ă���
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

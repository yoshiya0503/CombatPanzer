package ConcreatPanzers;

import AbstractPanzers.*;

public class USAPanzerFactory extends AbstractPanzerFactory{

	@Override
	public AbstractAttackRange createRange() {
		return new USAAttackRange();
	}

	@Override
	public AbstractMoveRange createMove() {
		return new USAMoveRange();
	}

}

package ConcreatPanzers;

import AbstractPanzers.*;

public class GermanPanzerFactory extends AbstractPanzerFactory{

	@Override
	public AbstractMoveRange createMove() {
		return new GermanMoveRange();
	}

	@Override
	public AbstractAttackRange createRange() {
		return new GermanAttackRange();
	}

}

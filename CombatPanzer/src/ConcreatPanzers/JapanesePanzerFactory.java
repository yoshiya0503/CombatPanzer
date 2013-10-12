package ConcreatPanzers;

import AbstractPanzers.*;

public class JapanesePanzerFactory extends AbstractPanzerFactory{

	@Override
	public AbstractMoveRange createMove() {
		return new JapaneseMoveRange();
	}

	@Override
	public AbstractAttackRange createRange() {
		return new JapaneseAttackRange();
	}

}

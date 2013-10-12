package Algorithm;

import AbstractPanzers.*;

public class RamdomAI extends AIAlgorithm{
	
	public RamdomAI(){
		super();
	}
	
	@Override
	public void moveAction() {
	}

	@Override
	public void attackAction() {
		
	}

	@Override
	protected AbstractPanzer selectPanzer(){
		//�G�l�~�[��Ԃ̂��������ꂩ���I������
		int panzerrand = (int)(Math.random()*10%this.enemy.getPanzer().size());
		return this.enemy.getPanzer().get(panzerrand);
	}

	@Override
	protected void selectAction() {
		int actionrand = (int)(Math.random()*10);
		//�����_���ɍs�������߂� �U�����ړ���
		if(actionrand<=5){
			this.phase.setPhaseFlag(this.phase.getMovePhase());
		}else if(actionrand>5 && actionrand<=10){
			this.phase.setPhaseFlag(this.phase.getAttackPhase());
		}
	}
	
}

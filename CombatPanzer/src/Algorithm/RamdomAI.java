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
		//エネミー戦車のうちいずれか一つを選択する
		int panzerrand = (int)(Math.random()*10%this.enemy.getPanzer().size());
		return this.enemy.getPanzer().get(panzerrand);
	}

	@Override
	protected void selectAction() {
		int actionrand = (int)(Math.random()*10);
		//ランダムに行動を決める 攻撃か移動か
		if(actionrand<=5){
			this.phase.setPhaseFlag(this.phase.getMovePhase());
		}else if(actionrand>5 && actionrand<=10){
			this.phase.setPhaseFlag(this.phase.getAttackPhase());
		}
	}
	
}

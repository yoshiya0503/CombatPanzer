package Algorithm;

import AbstractPanzers.*;
import Object.Enemy;
import Object.Phase;

abstract public class AIAlgorithm {

	protected Enemy enemy;
	protected Phase phase;
	
	protected AIAlgorithm(){
		this.enemy = Enemy.getInstance();
		this.phase = new Phase();
	}
	
	abstract protected void moveAction();
	abstract protected void attackAction();
	abstract protected void selectAction();
	abstract protected AbstractPanzer selectPanzer();
	protected void action(){
		
		//Ç‹Ç∏çsìÆÇåàÇﬂÇÈ
		if(this.phase.getPhaseFlag()==this.phase.getDefaultPhase()){
			this.selectAction();
		}else if(this.phase.getPhaseFlag()==this.phase.getMovePhase()){
			this.moveAction();
		}else if(this.phase.getPhaseFlag()==this.phase.getAttackPhase()){
			this.attackAction();
		}
	}
	
}

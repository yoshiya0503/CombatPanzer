package Algorithm;

import AbstractPanzers.AbstractPanzer;

public class SimpleStrategyAI extends AIAlgorithm{

	@Override
	public void moveAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AbstractPanzer selectPanzer() {
		int panzerrand = (int)(Math.random()*10%this.enemy.getPanzer().size());
		return this.enemy.getPanzer().get(panzerrand);
	}

	@Override
	protected void selectAction() {

		
	}
	
	@Override
	protected void action(){
		for(int i=0;i<this.enemy.getPanzer().size();i++){
			
		}
	}

}

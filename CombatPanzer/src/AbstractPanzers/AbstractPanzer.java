package AbstractPanzers;

import Object.Phase;

abstract public class AbstractPanzer {
	//戦車の座標
	protected int x,y;
	//戦車のコスト
	protected int cost;
	//戦車の行動フェーズの記述
	protected Phase phase;
	
	//初期化
	public AbstractPanzer(int x,int y,int cost){
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.phase = new Phase();
		this.phase.setPhaseFlag(this.phase.getDefaultPhase());
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getCost(){
		return this.cost;
	}
	public Phase getPanzerPhase(){
		return this.phase;
	}
	
	//戦車の移動可能範囲
	abstract public boolean movable(int x,int y);
	
	//戦車の攻撃可能範囲
	abstract public boolean attackable(int x,int y);
	
	//戦車の名前を取得する
	abstract public String getPanzerName();
	
	//戦車の移動
	public void movePanzer(int x,int y){
		this.x = x;
		this.y = y;
	}
	//フェーズの変更
	public void setPanzerPhase(Phase phase){
		this.phase.setPhaseFlag(phase.getPhaseFlag());
	}
}

package Object;

import java.util.ArrayList;

import AbstractPanzers.AbstractPanzer;

public class Player {
	//プレイヤーのライフ
	private int life;
	//スタック用のフラグ
	private int top;
	//プレイヤーの戦車のコスト合計、100以下でなければならない
	private int totalcost;
	//プレイヤーの持つ戦車
	private ArrayList<AbstractPanzer> panzer;
	//プレイヤーはSingletonで設計する
	private static Player instance = new Player();
	public static Player getInstance(){
		return instance;
	}
	
	private Player(){
		this.panzer = new ArrayList<AbstractPanzer>();
		this.top = 0;
		//プレイヤーのライフは100
		//ゲームスタート時に100-totalcostを合算する
		this.life = 100;
	}
	
	public void addPanzer(AbstractPanzer panzer){
		this.panzer.add(panzer);
		this.totalcost += panzer.getCost();
	}
	
	public void clearPanzer(){
		this.top=0;
		//シャローコピーの回避 ディープコピーを用いない
		for(int i=0;i<this.panzer.size();i++){
			this.panzer.get(i).movePanzer(100,100);
		}
		this.panzer.clear();
	}
	
	public int getTotalCost(){
		return this.totalcost;
	}
	
	public void initTotalCost(){
		this.totalcost = 0;
	}
	
	public void setPanzer(int x,int y){
		this.panzer.get(this.top).movePanzer(x, y);
		this.totalcost -= this.panzer.get(top).getCost();
		this.top++;
	}
	
	//選ばれた戦車を破壊し、ライフを減らす
	public void destroyPanzer(int x,int y){
		for(int i=0;i<this.panzer.size();i++){
			if(this.panzer.get(i).getX()==x && this.panzer.get(i).getY()==y){
				life -= 2*this.panzer.get(i).getCost();
				this.panzer.get(i).movePanzer(100,100);
				this.panzer.remove(i);
				System.out.println(this.life);
			}
		}	
	}
	
	public int getTop(){
		return this.top;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public void resetLife(){
		this.life = 100;
	}

	public ArrayList<AbstractPanzer> getPanzer(){
		return this.panzer;
	}	
}

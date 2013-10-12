package Object;

import java.util.ArrayList;

import AbstractPanzers.AbstractPanzer;
import Algorithm.*;

public class Enemy {
	//エネミーのライフ
	private int life;
	//スタック用のフラグ
	private int top;
	//エネミーの戦車のコスト合計、通常100以下でなければならないが敵は自由に選べる
	private int totalcost;
	//エネミーの持つ戦車
	private ArrayList<AbstractPanzer> panzer;
	//エネミーのAI
	private AIAlgorithm AI;
	
	//敵はシングルトンで設計
	private static Enemy instance = new Enemy();
	
	public static Enemy getInstance(){
		return instance;
	}
	
	private Enemy(){
		this.panzer = new ArrayList<AbstractPanzer>();
		this.top = 0;
		this.AI = new RamdomAI();
		//敵のライフは100
		//ゲームスタート時に100-totalcostを合算する
		this.life = 100;
	}
	
	public void addPanzer(AbstractPanzer panzer){
		this.panzer.add(panzer);
		this.totalcost += panzer.getCost();
	}
	
	public void clearPanzer(){
		this.top = 0;
		//シャローコピーの回避 ディープコピーを用いない
		//戦車の位置を画面外に移動してクリア
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
	
	//そのうちAIを作る
	public void action(){
		
	}
}

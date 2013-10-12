package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import AbstractPanzers.AbstractPanzer;
import MainApp.MainFrame;
import Object.Enemy;
import Object.Map;
import Object.Phase;
import Object.Player;

public class BattlePanel extends JPanel implements MouseListener{
	//画面の大きさ
	final int SIZE = 480;
	//マスの大きさ
	final int SELL_SIZE = 30;
	static int W;
	static int H;
	
	private Map map;
	private Player player;
	private Enemy enemy;
	private MainFrame mainframe;
	private Phase phase;
	
	public BattlePanel(MainFrame frame){
		this.mainframe = frame; 
		map = Map.getInstance();
		this.enemy = Enemy.getInstance();
		this.player = Player.getInstance();
		this.W = map.getRow()*this.SELL_SIZE;
		this.H = map.getVertical()*this.SELL_SIZE;
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		addMouseListener(this);
		this.phase = new Phase();
		//プレイヤーのターンからスタート
		this.phase.setTurnFlag(this.phase.getPlayerTurn());
	}
	
	public void paintComponent(Graphics g){
		
		//ライフが０または全滅で終了
		//各クラスの初期化はリザルト画面で行うものとする
		//敵を殲滅したら勝利
		if(this.enemy.getPanzer().size()==0){
			this.player.clearPanzer();
			this.enemy.clearPanzer();
			this.map.clearMap();
			mainframe.getContentPane().add(mainframe.result);
			mainframe.getContentPane().remove(mainframe.battle);
			mainframe.battle.setVisible(false);
			mainframe.result.setVisible(true);
			mainframe.pack();
		}else if(this.player.getPanzer().size()==0 || this.player.getLife()<=0){
			this.enemy.clearPanzer();
			this.player.clearPanzer();
			this.map.clearMap();
			mainframe.getContentPane().add(mainframe.result);
			mainframe.getContentPane().remove(mainframe.battle);
			mainframe.battle.setVisible(false);
			mainframe.result.setVisible(true);
			mainframe.pack();
		}
		
		//マップの基本形を描画
		g.setColor(Color.GREEN);
		g.fillRect(0,0,W,H);
		
		//セルに区切る
		for(int i=0;i<this.map.getRow();i++){
			g.setColor(Color.BLACK);
			g.drawLine(0,i*this.SELL_SIZE,W,i*this.SELL_SIZE);
			g.drawLine(i*this.SELL_SIZE,0,i*this.SELL_SIZE,H);
		}
		
		//いけない領域(川を作る)
		for(int y=0;y<this.map.getVertical();y++){
			for(int x=0;x<this.map.getRow();x++){
				if(!this.map.getMap()[x][y]){
					g.setColor(Color.BLUE);
					g.fillRect(x*this.SELL_SIZE,y*this.SELL_SIZE,this.SELL_SIZE,this.SELL_SIZE);
					g.setColor(Color.BLACK);
					g.drawLine(0,y*this.SELL_SIZE,W,y*this.SELL_SIZE);
					g.drawLine(x*this.SELL_SIZE,0,x*this.SELL_SIZE,H);
				}
			}
		}
		
		if(this.phase.getTurnFlag()==this.phase.getPlayerTurn()){
			//移動phaseのときに動ける領域を描画
			if(this.phase.getPhaseFlag()==phase.getMovePhase()){
				for(int y=0;y<this.map.getVertical();y++){
					for(int x=0;x<this.map.getRow();x++){
						if(this.map.getMap()[x][y]==true){
							for(int i=0;i<this.player.getPanzer().size();i++){
								if(player.getPanzer().get(i).movable(x,y) && this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getMovePhase()){
									g.setColor(Color.LIGHT_GRAY);
									g.fillRect(x*this.SELL_SIZE,y*this.SELL_SIZE,this.SELL_SIZE,this.SELL_SIZE);
									g.setColor(Color.BLACK);
									g.drawLine(0,y*this.SELL_SIZE,W,y*this.SELL_SIZE);
									g.drawLine(x*this.SELL_SIZE,0,x*this.SELL_SIZE,H);	
								}
							}
						}
					}
				}
			}
		
			//攻撃フェーズの描画
			if(this.phase.getPhaseFlag()==phase.getAttackPhase()){
				for(int y=0;y<this.map.getVertical();y++){
					for(int x=0;x<this.map.getRow();x++){
						if(this.map.getMap()[x][y]==true){
							for(int i=0;i<this.player.getPanzer().size();i++){
								if(player.getPanzer().get(i).attackable(x,y) && this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getAttackPhase()){
									g.setColor(Color.LIGHT_GRAY);
									g.fillRect(x*this.SELL_SIZE,y*this.SELL_SIZE,this.SELL_SIZE,this.SELL_SIZE);
									g.setColor(Color.BLACK);
									g.drawLine(0,y*this.SELL_SIZE,W,y*this.SELL_SIZE);
									g.drawLine(x*this.SELL_SIZE,0,x*this.SELL_SIZE,H);	
								}
							}
						}
					}
				}
			}
		}else if(this.phase.getTurnFlag()==this.phase.getEnemyTurn()){
			//敵のターンでの行動を描画する
			//enemy.action();のように
			//enemy.action();
			//デフォルトアクションに設定
			this.phase.setActionFlag(this.phase.getDefaultAction());
			System.out.println("enemy turn");
			
			//まずエネミーの戦車の攻撃範囲を探索し、その範囲にプレイヤーの戦車があれば撃破し、フェーズをひとつ進める
			//複数の戦車がいれば近くのものから破壊する(3回行動するまで続ける)
			for(int i=0;i<this.enemy.getPanzer().size();i++){
				for(int j=0;j<this.player.getPanzer().size();j++){
					int xtemp = this.player.getPanzer().get(j).getX();
					int ytemp = this.player.getPanzer().get(j).getY();
					if(this.enemy.getPanzer().get(i).attackable(xtemp,ytemp)){
						this.player.destroyPanzer(xtemp,ytemp);
						this.enemy.getPanzer().get(i).setPanzerPhase(this.phase);
						//２かいの行動を制御する
						if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
							this.phase.setActionFlag(this.phase.getAction1());
						}else if(this.phase.getActionFlag()==this.phase.getAction1()){
							this.phase.setActionFlag(this.phase.getAction2());
						}else if(this.phase.getActionFlag()==this.phase.getAction2()){
							break;
						}
					}
				}
			}
			
			//攻撃範囲に戦車がいない場合、自分の持っている戦車をランダムに選ぶ
			//射程範囲にプレイヤーの戦車が入る最小限の位置まで移動し、攻撃するのが理想
			//射程範囲にプレイヤーの戦車が入らない場合、移動可能な位置のうちで味方の戦車の距離が最小になる場所に移動する
			//アクションフラグが3になるまで続ける
			//まずランダムなAIで適当に動くようにする
			while(true){
				int panzerrand = (int)(Math.random()*10%this.enemy.getPanzer().size());
				int xrand = (int)(Math.random()*10%W);
				int yrand = (int)(Math.random()*10%H);
				//ゲームが終了してもパネルの処理は残ることに留意
				if(this.map.getMap()[xrand][yrand]==true && this.enemy.getPanzer().get(panzerrand).movable(xrand,yrand)){
					this.map.setMovable(this.enemy.getPanzer().get(panzerrand).getX(),this.enemy.getPanzer().get(panzerrand).getY());
					this.enemy.getPanzer().get(panzerrand).movePanzer(xrand,yrand);
					this.map.setUnmovable(xrand,yrand);
					this.enemy.getPanzer().get(panzerrand).setPanzerPhase(this.phase);
					//２かいの行動を制御する
					if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
						this.phase.setActionFlag(this.phase.getAction1());
					}else if(this.phase.getActionFlag()==this.phase.getAction1()){
						this.phase.setActionFlag(this.phase.getAction2());
					}else if(this.phase.getActionFlag()==this.phase.getAction2()){
						break;
					}
				}
			}
			
			//再描画
			this.mainframe.getContentPane().setVisible(false);
			this.mainframe.getContentPane().setVisible(true);
			
			//プレイヤーのターンに移行する
			this.phase.setTurnFlag(this.phase.getPlayerTurn());
			this.phase.setActionFlag(this.phase.getDefaultAction());
			
		}
				
		//戦車がおかれた場所をイエロー表示する Topは戦車の台数
		for(int j=0;j<player.getPanzer().size();j++){
			g.setColor(Color.YELLOW);
			g.fillRect(player.getPanzer().get(j).getX()*SELL_SIZE,player.getPanzer().get(j).getY()*SELL_SIZE,SELL_SIZE,SELL_SIZE);
			g.setColor(Color.BLACK);
			g.drawLine(0,player.getPanzer().get(j).getY()*this.SELL_SIZE,W,player.getPanzer().get(j).getY()*this.SELL_SIZE);
			g.drawLine(player.getPanzer().get(j).getX()*this.SELL_SIZE,0,player.getPanzer().get(j).getX()*this.SELL_SIZE,H);
		}
		
		//敵の戦車がおかれている場所をレッド表示するTopは戦車の台数
		for(int j=0;j<enemy.getPanzer().size();j++){
			g.setColor(Color.RED);
			g.fillRect(enemy.getPanzer().get(j).getX()*SELL_SIZE,enemy.getPanzer().get(j).getY()*SELL_SIZE,SELL_SIZE,SELL_SIZE);
			g.setColor(Color.BLACK);
			g.drawLine(0,enemy.getPanzer().get(j).getY()*this.SELL_SIZE,W,enemy.getPanzer().get(j).getY()*this.SELL_SIZE);
			g.drawLine(enemy.getPanzer().get(j).getX()*this.SELL_SIZE,0,enemy.getPanzer().get(j).getX()*this.SELL_SIZE,H);
		}
		
	}
	
	//クリックされた位置が戦車の時と、動ける範囲のいずれかがクリックされた場合
	@Override
	public void mouseClicked(MouseEvent click) {
		//x,yはクリックされた場所
		int X = click.getX()/this.SELL_SIZE;
		int Y = click.getY()/this.SELL_SIZE;
		
		//相手のターンであれば反応なし
		if(this.phase.getTurnFlag()==this.phase.getEnemyTurn())
			return;
		
		//クリックされた戦車がデフォルトフェーズであれば
		//まずデフォルトではすぐ移動に移る
		if(this.phase.getPhaseFlag()==this.phase.getDefaultPhase()){
			//クリックされると選択された戦車の移動領域を表示する
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(X==player.getPanzer().get(i).getX() && Y==player.getPanzer().get(i).getY()){
					this.phase.setPhaseFlag(this.phase.getMovePhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
				}
			}
		}else if(this.phase.getPhaseFlag()==this.phase.getMovePhase()){
			//movephaseになった戦車の動かせる領域を表示し、そのどこかに戦車を移動
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(this.map.getMap()[X][Y]==true && this.player.getPanzer().get(i).movable(X,Y) && (this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getMovePhase())){
					this.map.setMovable(player.getPanzer().get(i).getX(),player.getPanzer().get(i).getY());
					this.player.getPanzer().get(i).movePanzer(X, Y);
					this.map.setUnmovable(X,Y);
					this.phase.setPhaseFlag(this.phase.getDefaultPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
					//3回の行動を制御する
					if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
						this.phase.setActionFlag(this.phase.getAction1());
					}else if(this.phase.getActionFlag()==this.phase.getAction1()){
						this.phase.setActionFlag(this.phase.getAction2());
					}else if(this.phase.getActionFlag()==this.phase.getAction2()){
						this.phase.setActionFlag(this.phase.getDefaultAction());
						this.phase.setTurnFlag(this.phase.getEnemyTurn());
					}
					
					//同じ場所をクリックされるとアタックフェーズに移行する
				}else if(X==this.player.getPanzer().get(i).getX() && Y==this.player.getPanzer().get(i).getY()){
					this.phase.setPhaseFlag(this.phase.getAttackPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
				}
			}
		}
		
		//攻撃を選択された時、とりあえず移動終了の後、
		else if(this.phase.getPhaseFlag()==this.phase.getAttackPhase()){
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(this.player.getPanzer().get(i).attackable(X,Y) && this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getAttackPhase()){
					//敵の戦車を破壊する
					this.enemy.destroyPanzer(X,Y);
					this.phase.setPhaseFlag(this.phase.getDefaultPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
					//3回の行動を制御する
					if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
						this.phase.setActionFlag(this.phase.getAction1());
					}else if(this.phase.getActionFlag()==this.phase.getAction1()){
						this.phase.setActionFlag(this.phase.getAction2());
					}else if(this.phase.getActionFlag()==this.phase.getAction2()){
						this.phase.setActionFlag(this.phase.getDefaultAction());
						this.phase.setTurnFlag(this.phase.getEnemyTurn());
					}
				}
			}
		}
		
		//再描画
		this.mainframe.getContentPane().setVisible(false);
		this.mainframe.getContentPane().setVisible(true);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}

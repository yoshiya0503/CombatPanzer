package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import AbstractPanzers.AbstractPanzer;
import MainApp.MainFrame;
import Object.*;

public class SelectPanel extends JPanel{
	final int SIZE = 500;
	private MainFrame mainframe;
	private JButton OK;
	private ArrayList<AbstractPanzer> panzers;
	private Player player;
	private Enemy enemy;
	
	//選べる戦車を表示するパネル
	private class PanzerList extends JScrollPane {
		private ArrayList<AbstractPanzer> panzers;
		private JList panzerlist;
		//Jlistの動的な操作のための変数
		private DefaultListModel model;
		
		public PanzerList(ArrayList<AbstractPanzer> tmp_panzers){
			super();
			this.panzers = tmp_panzers;
			this.model = new DefaultListModel();
			this.panzerlist = new JList(this.model);
			for(int i=0;i<panzers.size();i++){
				model.addElement(this.panzers.get(i).getPanzerName() + "  cost:"+this.panzers.get(i).getCost());
			}
			//戦車リストをクリックした場合、その戦車をプレイヤーの手駒に追加する
			panzerlist.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//敵と自分の戦車オブジェクトを共有することによるバグが出る
					//戦車オブジェクトを別々にして回避
					Player player = Player.getInstance();
					player.addPanzer(panzers.get(panzerlist.getSelectedIndex()));
					
					//選ばれた戦車を取り除く
					//永久に取り除くか、２週目のプレイで復元するかは未定
					//とりあえず永久に取り除く
					panzers.remove(panzerlist.getSelectedIndex());
					model.remove(panzerlist.getSelectedIndex());
					System.out.println("yourpanzer selected");
					
					//コストオーバーした時の反応
					if(player.getTotalCost()>100){
						//プレイヤーの状態を初期化
						player.clearPanzer();
						player.initTotalCost();
						
						//画面表示の初期化
						System.out.println("コストオーバーしてますけど　戦車を選び直してください");
						panzers.clear();
						model.clear();
						for(int i=0;i<3;i++){
							panzers.add(new JapanesePanzer());
							panzers.add(new USAPanzer());
							panzers.add(new GermanPanzer());
						}
						for(int i=0;i<panzers.size();i++){
							model.addElement(panzers.get(i).getPanzerName() + "  cost:"+panzers.get(i).getCost());
						}
						return;
					}
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {}}
			);
			
			getViewport().setView(panzerlist);
			setPreferredSize(new Dimension(150,200));
		}
	}
	
	//選択した戦車のリストを表示
	private class EnemyPanzerList extends JScrollPane{
		
		private ArrayList<AbstractPanzer> panzers; //この変数は必要か？
		private JList panzerlist;
		//Jlistの動的な操作のための変数
		private DefaultListModel model;
		private Enemy enemy;
		
		public EnemyPanzerList(ArrayList<AbstractPanzer> tmp_panzers){
			super();
			this.enemy = Enemy.getInstance();
			this.panzers = tmp_panzers;
			this.model = new DefaultListModel();
			this.panzerlist = new JList(this.model);
			for(int i=0;i<enemy.getPanzer().size();i++){
				model.addElement(this.enemy.getPanzer().get(i).getPanzerName() + " cost:" + this.enemy.getPanzer().get(i).getCost());
			}
			getViewport().setView(panzerlist);
			setPreferredSize(new Dimension(150,200));
		}
	}
	
	//選択した戦車のスペックを表示するパネル(絵素材不足)
	/*
	private class PanzerPaformance extends JPanel{
		private ArrayList<AbstractPanzer> panzers;
		
		public PanzerPaformance(ArrayList<AbstractPanzer> panzers){
			super();
			this.panzers = panzers;
			setPreferredSize(new Dimension(400,250));		
		}
	}
	*/
	
	public SelectPanel(MainFrame frame){
		
		//選択可能な戦車を全てパネルに追加する
		this.panzers = new ArrayList<AbstractPanzer>();
		
		//とりあえず日本製3,アメリカ製3,ドイツ製3つくる
		for(int i=0;i<3;i++){
			panzers.add(new JapanesePanzer());
			panzers.add(new USAPanzer());
			panzers.add(new GermanPanzer());
		}
		
		//プレイヤーとエネミーを生成する
		this.player = Player.getInstance();
		this.enemy = Enemy.getInstance();
		
		//敵はとりあえず、戦車を３種類用意する
		enemy.addPanzer(new JapanesePanzer());
		enemy.addPanzer(new USAPanzer());
		enemy.addPanzer(new GermanPanzer());
		
		//ライフをコンソールに表示(デバッグ)
		//戦車を設置してからライフを減らすのは良くない仕様
		//エラーメッセージをポップアップにする
		System.out.println(player.getLife());
		System.out.println(enemy.getLife());
		
		//画面の表示
		mainframe = frame;
		setPreferredSize(new Dimension(SIZE,SIZE));
		setBackground(Color.WHITE);
		
		//ボタンを表示
		OK = new JButton("決定");
		add(OK);
		
		this.OK.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//選んだ戦車を表示する
				//戦車の選び方が不適切な場合にポップアップを表示する
				if(player.getTotalCost()==0){
					System.out.println("戦車をひとつでも選べばいいと思うよ");
					return;
				}else if(player.getTotalCost()>100){
					System.out.println("コストオーバーです");
					return;			
				}	
				
				mainframe.getContentPane().add(mainframe.briefing);
				mainframe.getContentPane().remove(mainframe.select);
				mainframe.select.setVisible(false);
				mainframe.briefing.setVisible(true);
				mainframe.pack();
				}
			});
		
		//選択できる戦車の表示
		PanzerList pl = new PanzerList(this.panzers);
		add(pl);		
		//敵の戦車の表示
		EnemyPanzerList epl = new EnemyPanzerList(this.panzers);
		add(epl);		
		//選択された戦車のスペックを表示(絵素材不足)
		//PanzerPaformance pp = new PanzerPaformance(this.panzers);
		//add(pp);

	}
}
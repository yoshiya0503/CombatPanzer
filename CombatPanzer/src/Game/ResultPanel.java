package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import MainApp.MainFrame;
import Object.Enemy;
import Object.GermanPanzer;
import Object.JapanesePanzer;
import Object.Map;
import Object.Player;
import Object.USAPanzer;

public class ResultPanel extends JPanel{
	final int SIZE = 500;
	private MainFrame mainframe;
	private JButton title;
	private Map map;
	private Player player;
	private Enemy enemy;
	
	public ResultPanel(MainFrame frame){
		this.mainframe = frame;
		this.map = Map.getInstance();
		this.enemy = Enemy.getInstance();
		this.player = Player.getInstance();
		//パネルの生成
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		setBackground(Color.WHITE);
		
		this.title = new JButton("タイトルへ");
		add(title);
		this.title.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//ライフを初期化する
				player.resetLife();
				enemy.resetLife();
				
				//敵の戦車を初期化する
				enemy.addPanzer(new JapanesePanzer());
				enemy.addPanzer(new USAPanzer());
				enemy.addPanzer(new GermanPanzer());
				if(enemy.getTotalCost()!=0){
					//とりあえず戦車全てを適当に置く。
					enemy.setPanzer(2,2);
					enemy.setPanzer(3,3);
					enemy.setPanzer(1,1);
					map.setUnmovable(2,2);
					map.setUnmovable(3,3);
					map.setUnmovable(1,1);
				}
				
				mainframe.getContentPane().add(mainframe.start);
				mainframe.getContentPane().remove(mainframe.result);
				mainframe.result.setVisible(false);
				mainframe.start.setVisible(true);
				mainframe.pack();
			}
		});
	}
}

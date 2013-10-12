package MainApp;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Game.BattlePanel;
import Game.ConfigPanel;
import Game.HelpPanel;
import Game.BriefingPanel;
import Game.ResultPanel;
import Game.SelectPanel;
import Game.StartPanel;
import Object.Map;

public class MainFrame extends JFrame{
	public StartPanel start;
	public SelectPanel select;
	public ConfigPanel config;
	public HelpPanel help;
	public BriefingPanel briefing;
	public BattlePanel battle;
	public ResultPanel result;
	
	public MainFrame(){
		start = new StartPanel(this);
		select = new SelectPanel(this);
		config = new ConfigPanel(this);
		help = new HelpPanel(this);
		briefing = new BriefingPanel(this);
		battle = new BattlePanel(this);
		result = new ResultPanel(this);
		
		setTitle("CombatPanzer!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		//初期画面のパネルを生成
		getContentPane().add(this.start);
		//getContentPane().add(this.briefing);
		pack();
	}
	
	public static void main(String[] args){
		//フレームの生成
		MainFrame appframe = new MainFrame();
		appframe.setVisible(true);
	}
}

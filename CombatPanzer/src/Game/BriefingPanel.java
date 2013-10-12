package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import AbstractPanzers.AbstractPanzer;
import MainApp.MainFrame;
import Object.Enemy;
import Object.Map;
import Object.Player;

public class BriefingPanel extends JPanel implements MouseListener{
	final int SIZE = 480;
	//�}�X�̑傫��
	final int SELL_SIZE = 30;
	//�Z���̏c���̐�
	private int W;
	private int H;
	private MainFrame mainframe;
	private Map map;
	private Player player;
	private Enemy enemy;
	
	public BriefingPanel(MainFrame frame){
		this.mainframe = frame;
		//�}�b�v�𐶐�
		this.map = Map.getInstance();
		this.W = map.getRow()*this.SELL_SIZE;
		this.H = map.getVertical()*this.SELL_SIZE;
		//�v���C���[�Ɛ�Ԃ��V���O���g���Ő���
		this.player = Player.getInstance();
		this.enemy = Enemy.getInstance();
		//�p�l���̐���
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		//�}�b�v�̊�{�`��`��
		g.setColor(Color.GREEN);
		g.fillRect(0,0,W,H);
		
		//�Z���ɋ�؂�
		for(int i=0;i<this.map.getRow();i++){
			g.setColor(Color.BLACK);
			g.drawLine(0,i*this.SELL_SIZE,W,i*this.SELL_SIZE);
			g.drawLine(i*this.SELL_SIZE,0,i*this.SELL_SIZE,H);
		}
		
		//�����Ȃ��̈�(������)
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
		
		//�܂��G�̐�Ԃ�ݒu����
		if(enemy.getTotalCost()!=0){
			//�Ƃ肠������ԑS�Ă�K���ɒu���B
			enemy.setPanzer(2,2);
			enemy.setPanzer(3,3);
			enemy.setPanzer(1,1);
			this.map.setUnmovable(2,2);
			this.map.setUnmovable(3,3);
			this.map.setUnmovable(1,1);
		}
		
		//�z�u�\�ȗ̈���O���[�\������ �R�X�g0,��Ԃ��ݒu���I������\�����Ȃ��B
		if(player.getTotalCost()!=0){
			for(int y=12;y<this.map.getVertical();y++){
				for(int x=12;x<this.map.getRow();x++){
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x*this.SELL_SIZE,y*this.SELL_SIZE,this.SELL_SIZE,this.SELL_SIZE);
					g.setColor(Color.BLACK);
					g.drawLine(0,y*this.SELL_SIZE,W,y*this.SELL_SIZE);
					g.drawLine(x*this.SELL_SIZE,0,x*this.SELL_SIZE,H);	
				}
			}
		}
		
		//�����̐�Ԃ������ꂽ�ꏊ���C�G���[�\������ Top�͐�Ԃ̑䐔
		for(int j=0;j<player.getPanzer().size();j++){
			g.setColor(Color.YELLOW);
			g.fillRect(player.getPanzer().get(j).getX()*SELL_SIZE,player.getPanzer().get(j).getY()*SELL_SIZE,SELL_SIZE,SELL_SIZE);
			g.setColor(Color.BLACK);
			g.drawLine(0,player.getPanzer().get(j).getY()*this.SELL_SIZE,W,player.getPanzer().get(j).getY()*this.SELL_SIZE);
			g.drawLine(player.getPanzer().get(j).getX()*this.SELL_SIZE,0,player.getPanzer().get(j).getX()*this.SELL_SIZE,H);
		}
		
		//�G�̐�Ԃ�������Ă���ꏊ�����b�h�\������Top�͐�Ԃ̑䐔
		for(int j=0;j<enemy.getPanzer().size();j++){
			g.setColor(Color.RED);
			g.fillRect(enemy.getPanzer().get(j).getX()*SELL_SIZE,enemy.getPanzer().get(j).getY()*SELL_SIZE,SELL_SIZE,SELL_SIZE);
			g.setColor(Color.BLACK);
			g.drawLine(0,enemy.getPanzer().get(j).getY()*this.SELL_SIZE,W,enemy.getPanzer().get(j).getY()*this.SELL_SIZE);
			g.drawLine(enemy.getPanzer().get(j).getX()*this.SELL_SIZE,0,enemy.getPanzer().get(j).getX()*this.SELL_SIZE,H);
		}
		
		//�R�X�g���̐�Ԃ�ݒu������Q�[���X�^�[�g
		if(player.getTotalCost()==0){
			mainframe.getContentPane().add(mainframe.battle);
			mainframe.getContentPane().remove(mainframe.briefing);
			mainframe.briefing.setVisible(false);
			mainframe.battle.setVisible(true);
			mainframe.pack();
		}
	}

	//�N���b�N���ꂽ�ʒu�ɐ�Ԃ�u��
	@Override
	public void mouseClicked(MouseEvent click) {
		//x,y�̓N���b�N���ꂽ�ꏊ
		int x = click.getX()/this.SELL_SIZE;
		int y = click.getY()/this.SELL_SIZE;
		//System.out.println("this.movable"+"["+x+"]"+"["+y+"]"+" "+"="+" "+"false;");
		//�O���[�̈悾���I���\ ��Ԃ̍��v�R�X�g�����ݒu�ł���
		if(x>=12 && y>=12 && player.getTotalCost()>0){
			if(this.map.getMap()[x][y] == true){
				this.player.setPanzer(x,y);
				this.map.setUnmovable(x,y);
			}
		}
		//�ĕ`��
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

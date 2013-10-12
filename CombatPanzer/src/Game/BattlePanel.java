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
	//��ʂ̑傫��
	final int SIZE = 480;
	//�}�X�̑傫��
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
		//�v���C���[�̃^�[������X�^�[�g
		this.phase.setTurnFlag(this.phase.getPlayerTurn());
	}
	
	public void paintComponent(Graphics g){
		
		//���C�t���O�܂��͑S�łŏI��
		//�e�N���X�̏������̓��U���g��ʂōs�����̂Ƃ���
		//�G��r�ł����珟��
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
		
		if(this.phase.getTurnFlag()==this.phase.getPlayerTurn()){
			//�ړ�phase�̂Ƃ��ɓ�����̈��`��
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
		
			//�U���t�F�[�Y�̕`��
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
			//�G�̃^�[���ł̍s����`�悷��
			//enemy.action();�̂悤��
			//enemy.action();
			//�f�t�H���g�A�N�V�����ɐݒ�
			this.phase.setActionFlag(this.phase.getDefaultAction());
			System.out.println("enemy turn");
			
			//�܂��G�l�~�[�̐�Ԃ̍U���͈͂�T�����A���͈̔͂Ƀv���C���[�̐�Ԃ�����Ό��j���A�t�F�[�Y���ЂƂi�߂�
			//�����̐�Ԃ�����΋߂��̂��̂���j�󂷂�(3��s������܂ő�����)
			for(int i=0;i<this.enemy.getPanzer().size();i++){
				for(int j=0;j<this.player.getPanzer().size();j++){
					int xtemp = this.player.getPanzer().get(j).getX();
					int ytemp = this.player.getPanzer().get(j).getY();
					if(this.enemy.getPanzer().get(i).attackable(xtemp,ytemp)){
						this.player.destroyPanzer(xtemp,ytemp);
						this.enemy.getPanzer().get(i).setPanzerPhase(this.phase);
						//�Q�����̍s���𐧌䂷��
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
			
			//�U���͈͂ɐ�Ԃ����Ȃ��ꍇ�A�����̎����Ă����Ԃ������_���ɑI��
			//�˒��͈͂Ƀv���C���[�̐�Ԃ�����ŏ����̈ʒu�܂ňړ����A�U������̂����z
			//�˒��͈͂Ƀv���C���[�̐�Ԃ�����Ȃ��ꍇ�A�ړ��\�Ȉʒu�̂����Ŗ����̐�Ԃ̋������ŏ��ɂȂ�ꏊ�Ɉړ�����
			//�A�N�V�����t���O��3�ɂȂ�܂ő�����
			//�܂������_����AI�œK���ɓ����悤�ɂ���
			while(true){
				int panzerrand = (int)(Math.random()*10%this.enemy.getPanzer().size());
				int xrand = (int)(Math.random()*10%W);
				int yrand = (int)(Math.random()*10%H);
				//�Q�[�����I�����Ă��p�l���̏����͎c�邱�Ƃɗ���
				if(this.map.getMap()[xrand][yrand]==true && this.enemy.getPanzer().get(panzerrand).movable(xrand,yrand)){
					this.map.setMovable(this.enemy.getPanzer().get(panzerrand).getX(),this.enemy.getPanzer().get(panzerrand).getY());
					this.enemy.getPanzer().get(panzerrand).movePanzer(xrand,yrand);
					this.map.setUnmovable(xrand,yrand);
					this.enemy.getPanzer().get(panzerrand).setPanzerPhase(this.phase);
					//�Q�����̍s���𐧌䂷��
					if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
						this.phase.setActionFlag(this.phase.getAction1());
					}else if(this.phase.getActionFlag()==this.phase.getAction1()){
						this.phase.setActionFlag(this.phase.getAction2());
					}else if(this.phase.getActionFlag()==this.phase.getAction2()){
						break;
					}
				}
			}
			
			//�ĕ`��
			this.mainframe.getContentPane().setVisible(false);
			this.mainframe.getContentPane().setVisible(true);
			
			//�v���C���[�̃^�[���Ɉڍs����
			this.phase.setTurnFlag(this.phase.getPlayerTurn());
			this.phase.setActionFlag(this.phase.getDefaultAction());
			
		}
				
		//��Ԃ������ꂽ�ꏊ���C�G���[�\������ Top�͐�Ԃ̑䐔
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
		
	}
	
	//�N���b�N���ꂽ�ʒu����Ԃ̎��ƁA������͈͂̂����ꂩ���N���b�N���ꂽ�ꍇ
	@Override
	public void mouseClicked(MouseEvent click) {
		//x,y�̓N���b�N���ꂽ�ꏊ
		int X = click.getX()/this.SELL_SIZE;
		int Y = click.getY()/this.SELL_SIZE;
		
		//����̃^�[���ł���Δ����Ȃ�
		if(this.phase.getTurnFlag()==this.phase.getEnemyTurn())
			return;
		
		//�N���b�N���ꂽ��Ԃ��f�t�H���g�t�F�[�Y�ł����
		//�܂��f�t�H���g�ł͂����ړ��Ɉڂ�
		if(this.phase.getPhaseFlag()==this.phase.getDefaultPhase()){
			//�N���b�N�����ƑI�����ꂽ��Ԃ̈ړ��̈��\������
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(X==player.getPanzer().get(i).getX() && Y==player.getPanzer().get(i).getY()){
					this.phase.setPhaseFlag(this.phase.getMovePhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
				}
			}
		}else if(this.phase.getPhaseFlag()==this.phase.getMovePhase()){
			//movephase�ɂȂ�����Ԃ̓�������̈��\�����A���̂ǂ����ɐ�Ԃ��ړ�
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(this.map.getMap()[X][Y]==true && this.player.getPanzer().get(i).movable(X,Y) && (this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getMovePhase())){
					this.map.setMovable(player.getPanzer().get(i).getX(),player.getPanzer().get(i).getY());
					this.player.getPanzer().get(i).movePanzer(X, Y);
					this.map.setUnmovable(X,Y);
					this.phase.setPhaseFlag(this.phase.getDefaultPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
					//3��̍s���𐧌䂷��
					if(this.phase.getActionFlag()==this.phase.getDefaultAction()){
						this.phase.setActionFlag(this.phase.getAction1());
					}else if(this.phase.getActionFlag()==this.phase.getAction1()){
						this.phase.setActionFlag(this.phase.getAction2());
					}else if(this.phase.getActionFlag()==this.phase.getAction2()){
						this.phase.setActionFlag(this.phase.getDefaultAction());
						this.phase.setTurnFlag(this.phase.getEnemyTurn());
					}
					
					//�����ꏊ���N���b�N�����ƃA�^�b�N�t�F�[�Y�Ɉڍs����
				}else if(X==this.player.getPanzer().get(i).getX() && Y==this.player.getPanzer().get(i).getY()){
					this.phase.setPhaseFlag(this.phase.getAttackPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
				}
			}
		}
		
		//�U����I�����ꂽ���A�Ƃ肠�����ړ��I���̌�A
		else if(this.phase.getPhaseFlag()==this.phase.getAttackPhase()){
			for(int i=0;i<this.player.getPanzer().size();i++){
				if(this.player.getPanzer().get(i).attackable(X,Y) && this.player.getPanzer().get(i).getPanzerPhase().getPhaseFlag()==this.phase.getAttackPhase()){
					//�G�̐�Ԃ�j�󂷂�
					this.enemy.destroyPanzer(X,Y);
					this.phase.setPhaseFlag(this.phase.getDefaultPhase());
					this.player.getPanzer().get(i).setPanzerPhase(this.phase);
					//3��̍s���𐧌䂷��
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

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
	
	//�I�ׂ��Ԃ�\������p�l��
	private class PanzerList extends JScrollPane {
		private ArrayList<AbstractPanzer> panzers;
		private JList panzerlist;
		//Jlist�̓��I�ȑ���̂��߂̕ϐ�
		private DefaultListModel model;
		
		public PanzerList(ArrayList<AbstractPanzer> tmp_panzers){
			super();
			this.panzers = tmp_panzers;
			this.model = new DefaultListModel();
			this.panzerlist = new JList(this.model);
			for(int i=0;i<panzers.size();i++){
				model.addElement(this.panzers.get(i).getPanzerName() + "  cost:"+this.panzers.get(i).getCost());
			}
			//��ԃ��X�g���N���b�N�����ꍇ�A���̐�Ԃ��v���C���[�̎��ɒǉ�����
			panzerlist.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//�G�Ǝ����̐�ԃI�u�W�F�N�g�����L���邱�Ƃɂ��o�O���o��
					//��ԃI�u�W�F�N�g��ʁX�ɂ��ĉ��
					Player player = Player.getInstance();
					player.addPanzer(panzers.get(panzerlist.getSelectedIndex()));
					
					//�I�΂ꂽ��Ԃ���菜��
					//�i�v�Ɏ�菜�����A�Q�T�ڂ̃v���C�ŕ������邩�͖���
					//�Ƃ肠�����i�v�Ɏ�菜��
					panzers.remove(panzerlist.getSelectedIndex());
					model.remove(panzerlist.getSelectedIndex());
					System.out.println("yourpanzer selected");
					
					//�R�X�g�I�[�o�[�������̔���
					if(player.getTotalCost()>100){
						//�v���C���[�̏�Ԃ�������
						player.clearPanzer();
						player.initTotalCost();
						
						//��ʕ\���̏�����
						System.out.println("�R�X�g�I�[�o�[���Ă܂����ǁ@��Ԃ�I�ђ����Ă�������");
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
	
	//�I��������Ԃ̃��X�g��\��
	private class EnemyPanzerList extends JScrollPane{
		
		private ArrayList<AbstractPanzer> panzers; //���̕ϐ��͕K�v���H
		private JList panzerlist;
		//Jlist�̓��I�ȑ���̂��߂̕ϐ�
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
	
	//�I��������Ԃ̃X�y�b�N��\������p�l��(�G�f�ޕs��)
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
		
		//�I���\�Ȑ�Ԃ�S�ăp�l���ɒǉ�����
		this.panzers = new ArrayList<AbstractPanzer>();
		
		//�Ƃ肠�������{��3,�A�����J��3,�h�C�c��3����
		for(int i=0;i<3;i++){
			panzers.add(new JapanesePanzer());
			panzers.add(new USAPanzer());
			panzers.add(new GermanPanzer());
		}
		
		//�v���C���[�ƃG�l�~�[�𐶐�����
		this.player = Player.getInstance();
		this.enemy = Enemy.getInstance();
		
		//�G�͂Ƃ肠�����A��Ԃ��R��ޗp�ӂ���
		enemy.addPanzer(new JapanesePanzer());
		enemy.addPanzer(new USAPanzer());
		enemy.addPanzer(new GermanPanzer());
		
		//���C�t���R���\�[���ɕ\��(�f�o�b�O)
		//��Ԃ�ݒu���Ă��烉�C�t�����炷�̂͗ǂ��Ȃ��d�l
		//�G���[���b�Z�[�W���|�b�v�A�b�v�ɂ���
		System.out.println(player.getLife());
		System.out.println(enemy.getLife());
		
		//��ʂ̕\��
		mainframe = frame;
		setPreferredSize(new Dimension(SIZE,SIZE));
		setBackground(Color.WHITE);
		
		//�{�^����\��
		OK = new JButton("����");
		add(OK);
		
		this.OK.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//�I�񂾐�Ԃ�\������
				//��Ԃ̑I�ѕ����s�K�؂ȏꍇ�Ƀ|�b�v�A�b�v��\������
				if(player.getTotalCost()==0){
					System.out.println("��Ԃ��ЂƂł��I�ׂ΂����Ǝv����");
					return;
				}else if(player.getTotalCost()>100){
					System.out.println("�R�X�g�I�[�o�[�ł�");
					return;			
				}	
				
				mainframe.getContentPane().add(mainframe.briefing);
				mainframe.getContentPane().remove(mainframe.select);
				mainframe.select.setVisible(false);
				mainframe.briefing.setVisible(true);
				mainframe.pack();
				}
			});
		
		//�I���ł����Ԃ̕\��
		PanzerList pl = new PanzerList(this.panzers);
		add(pl);		
		//�G�̐�Ԃ̕\��
		EnemyPanzerList epl = new EnemyPanzerList(this.panzers);
		add(epl);		
		//�I�����ꂽ��Ԃ̃X�y�b�N��\��(�G�f�ޕs��)
		//PanzerPaformance pp = new PanzerPaformance(this.panzers);
		//add(pp);

	}
}
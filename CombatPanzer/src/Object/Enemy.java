package Object;

import java.util.ArrayList;

import AbstractPanzers.AbstractPanzer;
import Algorithm.*;

public class Enemy {
	//�G�l�~�[�̃��C�t
	private int life;
	//�X�^�b�N�p�̃t���O
	private int top;
	//�G�l�~�[�̐�Ԃ̃R�X�g���v�A�ʏ�100�ȉ��łȂ���΂Ȃ�Ȃ����G�͎��R�ɑI�ׂ�
	private int totalcost;
	//�G�l�~�[�̎����
	private ArrayList<AbstractPanzer> panzer;
	//�G�l�~�[��AI
	private AIAlgorithm AI;
	
	//�G�̓V���O���g���Ő݌v
	private static Enemy instance = new Enemy();
	
	public static Enemy getInstance(){
		return instance;
	}
	
	private Enemy(){
		this.panzer = new ArrayList<AbstractPanzer>();
		this.top = 0;
		this.AI = new RamdomAI();
		//�G�̃��C�t��100
		//�Q�[���X�^�[�g����100-totalcost�����Z����
		this.life = 100;
	}
	
	public void addPanzer(AbstractPanzer panzer){
		this.panzer.add(panzer);
		this.totalcost += panzer.getCost();
	}
	
	public void clearPanzer(){
		this.top = 0;
		//�V�����[�R�s�[�̉�� �f�B�[�v�R�s�[��p���Ȃ�
		//��Ԃ̈ʒu����ʊO�Ɉړ����ăN���A
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
	
	//���̂���AI�����
	public void action(){
		
	}
}

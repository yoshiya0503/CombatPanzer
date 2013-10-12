package Object;

import java.util.ArrayList;

import AbstractPanzers.AbstractPanzer;

public class Player {
	//�v���C���[�̃��C�t
	private int life;
	//�X�^�b�N�p�̃t���O
	private int top;
	//�v���C���[�̐�Ԃ̃R�X�g���v�A100�ȉ��łȂ���΂Ȃ�Ȃ�
	private int totalcost;
	//�v���C���[�̎����
	private ArrayList<AbstractPanzer> panzer;
	//�v���C���[��Singleton�Ő݌v����
	private static Player instance = new Player();
	public static Player getInstance(){
		return instance;
	}
	
	private Player(){
		this.panzer = new ArrayList<AbstractPanzer>();
		this.top = 0;
		//�v���C���[�̃��C�t��100
		//�Q�[���X�^�[�g����100-totalcost�����Z����
		this.life = 100;
	}
	
	public void addPanzer(AbstractPanzer panzer){
		this.panzer.add(panzer);
		this.totalcost += panzer.getCost();
	}
	
	public void clearPanzer(){
		this.top=0;
		//�V�����[�R�s�[�̉�� �f�B�[�v�R�s�[��p���Ȃ�
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
	
	//�I�΂ꂽ��Ԃ�j�󂵁A���C�t�����炷
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

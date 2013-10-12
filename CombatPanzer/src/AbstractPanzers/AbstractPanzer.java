package AbstractPanzers;

import Object.Phase;

abstract public class AbstractPanzer {
	//��Ԃ̍��W
	protected int x,y;
	//��Ԃ̃R�X�g
	protected int cost;
	//��Ԃ̍s���t�F�[�Y�̋L�q
	protected Phase phase;
	
	//������
	public AbstractPanzer(int x,int y,int cost){
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.phase = new Phase();
		this.phase.setPhaseFlag(this.phase.getDefaultPhase());
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getCost(){
		return this.cost;
	}
	public Phase getPanzerPhase(){
		return this.phase;
	}
	
	//��Ԃ̈ړ��\�͈�
	abstract public boolean movable(int x,int y);
	
	//��Ԃ̍U���\�͈�
	abstract public boolean attackable(int x,int y);
	
	//��Ԃ̖��O���擾����
	abstract public String getPanzerName();
	
	//��Ԃ̈ړ�
	public void movePanzer(int x,int y){
		this.x = x;
		this.y = y;
	}
	//�t�F�[�Y�̕ύX
	public void setPanzerPhase(Phase phase){
		this.phase.setPhaseFlag(phase.getPhaseFlag());
	}
}

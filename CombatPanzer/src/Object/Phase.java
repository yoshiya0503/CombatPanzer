package Object;

public class Phase {

	//move�t�F�[�Y�A�U���t�F�[�Y���
	private enum PhaseFlag{
		defaultphase(0),movephase(1),attackphase(2);
		int phasevalue;
		private PhaseFlag(int phasevalue){
			this.phasevalue = phasevalue;
		}
	}
	
	//����^�[���A�v���C���[�^�[�����
	private enum TurnFlag{
		defaultturn(0),playerturn(1),enemyturn(2);
		int turnvalue;
		private TurnFlag(int turnvalue){
			this.turnvalue = turnvalue;
		}
	}
	
	//�s���̉񐔂͂Q���z��
	private enum ActionFlag{
		defaultaction(0),action1(1),action2(2);
		int actionvalue;
		private ActionFlag(int actionvalue){
			this.actionvalue = actionvalue;
		}
	}
	
	private PhaseFlag phase;
	private TurnFlag turn;
	private ActionFlag action;
	
	public Phase(){
		this.phase = PhaseFlag.defaultphase;
		this.turn = TurnFlag.defaultturn;
		this.action = ActionFlag.defaultaction;
	}
	
	//�^�[���̋L�q
	public void setTurnFlag(TurnFlag turn){
		this.turn = turn;
	}
	public TurnFlag getPlayerTurn(){
		return this.turn.playerturn;
	}
	public TurnFlag getEnemyTurn(){
		return this.turn.enemyturn;
	}
	public TurnFlag getDefaultTurn(){
		return this.turn.defaultturn;
	}
	public TurnFlag getTurnFlag(){
		return this.turn;
	}

	//�t�F�[�Y�̋L�q
	public void setPhaseFlag(PhaseFlag phase){
		this.phase = phase;
	}	
	public PhaseFlag getAttackPhase(){
		return this.phase.attackphase;
	}
	public PhaseFlag getMovePhase(){
		return this.phase.movephase;
	}
	public PhaseFlag getDefaultPhase(){
		return this.phase.defaultphase;
	}
	public PhaseFlag getPhaseFlag(){
		return this.phase;
	}
	
	//�s�����Q��ł���̂ł��̋L�q
	public void setActionFlag(ActionFlag action){
		this.action = action;
	}
	public ActionFlag getAction1(){
		return this.action.action1;
	}
	public ActionFlag getAction2(){
		return this.action.action2;
	}
	public ActionFlag getDefaultAction(){
		return this.action.defaultaction;
	}
	public ActionFlag getActionFlag(){
		return this.action;
	}
	
}

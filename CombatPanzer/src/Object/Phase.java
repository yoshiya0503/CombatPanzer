package Object;

public class Phase {

	//moveフェーズ、攻撃フェーズを列挙
	private enum PhaseFlag{
		defaultphase(0),movephase(1),attackphase(2);
		int phasevalue;
		private PhaseFlag(int phasevalue){
			this.phasevalue = phasevalue;
		}
	}
	
	//相手ターン、プレイヤーターンを列挙
	private enum TurnFlag{
		defaultturn(0),playerturn(1),enemyturn(2);
		int turnvalue;
		private TurnFlag(int turnvalue){
			this.turnvalue = turnvalue;
		}
	}
	
	//行動の回数は２回を想定
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
	
	//ターンの記述
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

	//フェーズの記述
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
	
	//行動が２回できるのでその記述
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

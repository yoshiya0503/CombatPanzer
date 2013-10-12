package Object;

public class Map {
	//マップの大きさ
	private final int row = 16;
	private final int vertical = 16;
	//マップ上のいける場所と行けない場所
	private boolean[][] movable;
	private static Map instance = new Map();
	
	public static Map getInstance(){
		return instance;
	}
	//シングルトンで初期化
	private Map(){
		this.movable = new boolean[row][vertical];
		for(int i=0;i<row;i++)
			for(int j=0;j<vertical;j++)
				this.movable[i][j] = true;
		this.makeRiver();
	}
	public boolean[][] getMap(){
		return this.movable;
	}
	public int getRow(){
		return this.row;
	}
	public int getVertical(){
		return this.vertical;
	}
	//マップを初期化する
	public void clearMap(){
		this.movable = new boolean[row][vertical];
		for(int i=0;i<row;i++)
			for(int j=0;j<vertical;j++)
				this.movable[i][j] = true;
		this.makeRiver();
	}
	
	//いけない場所にする
	public void setUnmovable(int x,int y){
		this.movable[x][y] = false;
	}
	
	//行ける場所にする
	public void setMovable(int x,int y){
		this.movable[x][y] = true;
	}
	
	//元々いけない川を作る
	public void makeRiver(){
		this.movable[0][8] = false;
		this.movable[0][9] = false;
		this.movable[1][9] = false;
		this.movable[1][8] = false;
		this.movable[2][7] = false;
		this.movable[2][8] = false;
		this.movable[3][6] = false;
		this.movable[3][7] = false;
		this.movable[4][5] = false;
		this.movable[4][6] = false;
		this.movable[5][6] = false;
		this.movable[5][5] = false;
		this.movable[8][5] = false;
		this.movable[8][6] = false;
		this.movable[9][4] = false;
		this.movable[9][5] = false;
		this.movable[10][4] = false;
		this.movable[10][5] = false;
		this.movable[11][4] = false;
		this.movable[11][5] = false;
		this.movable[12][3] = false;
		this.movable[12][4] = false;
		this.movable[13][2] = false;
		this.movable[13][3] = false;
		this.movable[14][2] = false;
		this.movable[14][3] = false;
		this.movable[15][2] = false;
		this.movable[15][3] = false;
		this.movable[3][11] = false;
		this.movable[4][10] = false;
		this.movable[5][11] = false;
		this.movable[4][12] = false;
		this.movable[4][11] = false;
		this.movable[5][10] = false;
		this.movable[3][12] = false;
		this.movable[12][8] = false;
		this.movable[13][8] = false;
		this.movable[11][9] = false;
		this.movable[12][9] = false;
		this.movable[12][10] = false;
		this.movable[13][10] = false;
		this.movable[8][12] = false;
		this.movable[7][12] = false;
		this.movable[7][13] = false;
		this.movable[8][13] = false;
		this.movable[9][6] = false;
	}
	
}

package ai;
//dieses package können sie ignorieren
public class node {
	
	public node parent;
	public int col;
	public int row;
	public int gCost;
	public int hCost;
	public int fCost;
	public boolean solid;
	public boolean open;
	public boolean checked;
	
	public node(int col,int row) {
		this.col = col;
		this.row = row;
		
	} 

}

package ai;

import java.util.ArrayList;

import entity.entity;
import main.gamepanel;

public class pathfinder {
	
	gamepanel gp;
	node[][] node;
	ArrayList<node> openList = new ArrayList<>();
	public ArrayList<node> pathlist = new ArrayList<>();
	node startnode,goalnode,currentnode;
	boolean goalreached = false;
	int step = 0;
	
	public pathfinder(gamepanel gp) {
		
		this.gp = gp;
		instantiatenodes();
	}
	public void instantiatenodes() {
		
		node = new node [gp.maxworldcol][gp.maxworldrow];
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			node[col][row] = new node(col,row);
			
			col++;
			if(col == gp.maxworldcol) {
				col = 0;
				row++;
			
			}
		}
	}
	public void resetnodes() {
		

		int col = 0;
		int row = 0;
		
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			

			col++;
			if(col == gp.maxworldcol) {
				col = 0;
				row++;
			
			}
			
			}
	
			openList.clear();
			pathlist.clear();
			goalreached = false;
			step = 0;
		}
	
	public void setnodes(int startcol, int startrow,int goalcol,int goalrow, entity entity) {
		
		resetnodes();
		
		startnode = node[startcol][startrow];
		currentnode = startnode;
		goalnode = node[goalcol][goalrow];
		openList.add(currentnode);
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			
			int tilenum = gp.tileM.mapTilenum[col][row];
			if(gp.tileM.tile[tilenum].collision = true) {
				node[col][row].solid = true;
			}
			
			getcost(node[col][row]);
			
			col++;
			if(col == gp.maxworldcol) {
				col = 0 ;
				row++;
				
			}
		}
	}
	public void getcost(node node) {
		
		int xdistance = Math.abs(node.col - startnode.col);
		int ydistance = Math.abs(node.row - startnode.row);
		node.gCost = xdistance + ydistance;
		
		xdistance = Math.abs(node.col - goalnode.col);
		ydistance = Math.abs(node.row - goalnode.row);
		node.hCost = xdistance + ydistance;
		
		node.fCost = node.gCost + node.hCost;
		
	}
	public boolean search() {
		
		while(goalreached == false && step < 500) {
			int col = currentnode.col;
			int row = currentnode.row;
			
			currentnode.checked = true;
			openList.remove(currentnode);
			
			if(row-1 >= 0) {
				opennode(node[col][row-1]);
			}

			if(col-1 >= 0) {
				opennode(node[col-1][row]);
			}

			if(row+1 >= 0) {
				opennode(node[col][row+1]);
			}

			if(col+1 >= 0) {
				opennode(node[col+1][row]);
			}
			int bestnodeIndex = 0;
			int bestnodefCost = 999;
			
			for(int i = 0; i <openList.size(); i++) {
				
				if(openList.get(i).fCost < bestnodefCost) {
					bestnodeIndex = i;
					bestnodefCost = openList.get(i).fCost;
					
				}
				else if(openList.get(i).fCost == bestnodefCost) {
					if(openList.get(i).gCost < openList.get(bestnodeIndex).gCost) {
						bestnodeIndex = i;
					}
				}
			}
			if(openList.size() == 0) {
				break;
			}
			currentnode = openList.get(bestnodeIndex);
			
			if(currentnode == goalnode) { 
				goalreached = true;
				trackthepath();
			}
			step++;
		}
		return goalreached;
	}
	public void opennode(node node) {
		
		if(node.open == false && node.checked == false && node.solid == false) {
			
			node.open = true;
			node.parent = currentnode;
			openList.add(node);
		}
		
	}
	public void trackthepath() {
		
		node current = goalnode;
		
		while(current!= startnode) {
			
			pathlist.add(0,current);
			current = current.parent;
		}
	}
}
	
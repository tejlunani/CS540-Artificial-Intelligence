import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;

	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}

	public State[] getSuccessors() {
				
		//find index of empty space
		State[] successors = new State[4];
		int index = 0;
		for (int i = 0; i < this.board.length; i++) {
			  if (this.board[i] == 0) {
			    index = i;
			    break;
			  }
		}
		
		//generate successors based on location of empty space
		switch (index) {
			case 0:
				successors[0] = succ(this.board, 0, 1);
				successors[1] = succ(this.board, 0, 2);
				successors[2] = succ(this.board, 0, 3);
				successors[3] = succ(this.board, 0, 6);
				break;
			case 1:
				successors[0] = succ(this.board, 1, 0);
				successors[1] = succ(this.board, 1, 2);
				successors[2] = succ(this.board, 1, 4);
				successors[3] = succ(this.board, 1, 7);
				break;
			case 2:
				successors[0] = succ(this.board, 2, 0);
				successors[1] = succ(this.board, 2, 1);
				successors[2] = succ(this.board, 2, 5);
				successors[3] = succ(this.board, 2, 8);
				break;
			case 3: 
				successors[0] = succ(this.board, 3, 0);
				successors[1] = succ(this.board, 3, 4);
				successors[2] = succ(this.board, 3, 5);
				successors[3] = succ(this.board, 3, 6);
				break;
			case 4:
				successors[0] = succ(this.board, 4, 1);
				successors[1] = succ(this.board, 4, 3);
				successors[2] = succ(this.board, 4, 5);
				successors[3] = succ(this.board, 4, 7);
				break;
			case 5:
				successors[0] = succ(this.board, 5, 2);
				successors[1] = succ(this.board, 5, 3);
				successors[2] = succ(this.board, 5, 4);
				successors[3] = succ(this.board, 5, 8);
				break;
			case 6:
				successors[0] = succ(this.board, 6, 0);
				successors[1] = succ(this.board, 6, 3);
				successors[2] = succ(this.board, 6, 7);
				successors[3] = succ(this.board, 6, 8);
				break;
			case 7:
				successors[0] = succ(this.board, 7, 1);
				successors[1] = succ(this.board, 7, 4);
				successors[2] = succ(this.board, 7, 6);
				successors[3] = succ(this.board, 7, 8);
				break;
			case 8:
				successors[0] = succ(this.board, 8, 2);
				successors[1] = succ(this.board, 8, 5);
				successors[2] = succ(this.board, 8, 6);
				successors[3] = succ(this.board, 8, 7);
				break;
		}
		
		Arrays.sort(successors, new Comparator<State>() {
	        @Override
	        public int compare(State a, State b) {
	    		return Arrays.toString(a.board).compareTo(Arrays.toString(b.board));
	    	}
	    });
		
		return successors;
	}
	

	
	public int compare(State a, State b) {
		return Arrays.toString(a.board).compareTo(Arrays.toString(b.board));
	}
	
	//function to move a tile into the empty space
	public State succ(int[] board1, int i, int j){
		int[] bb = Arrays.copyOf(board1, board1.length); 
		int temp = bb[i];
		bb[i] = bb[j];
		bb[j] = temp;
		State xx = new State(bb);
		xx.depth = this.depth + 1;
		xx.parentPt = this;
		return xx;
	}
	
	public void printState(int option) {
		
		if (option == 1 || option == 2) {
			System.out.println(getBoard());
		} else if (option == 3) {
			if (parentPt == null) {
				System.out.println(getBoard() + " parent 0 0 0 0 0 0 0 0 0");
			} else {
				System.out.println(getBoard() + " parent " + parentPt.getBoard());
			}
		}
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

public class Torus {

	public static boolean contains(List<State> list, State s) {
	    for(State x : list) {
	        if(Arrays.equals(x.board, s.board))
	            return true;
	    }
	    return false;
	}
	
	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;
			int currentStackSize = 0;
			
			while (true) {				
				stack.push(init);
				currentStackSize++;
				while (!stack.isEmpty()) {
					maxStackSize = Integer.max(maxStackSize, currentStackSize);
					State curr = stack.pop();
					currentStackSize--;
					if (curr.parentPt != null) 
						prefix = new ArrayList<State>(prefix.subList(0, prefix.indexOf(curr.parentPt)+1));
					prefix.add(curr);
					curr.printState(option);
					goalChecked++;
					if (curr.isGoalState()){
						if (option == 5) {
							for (State s : prefix)
								s.printState(1);
							System.out.println("Goal-check " + goalChecked);
							System.out.println("Max-stack-size " + maxStackSize);
							return;
						}
						break;
					}
					if (curr.depth < cutoff){
						State[] successors = curr.getSuccessors();
						for (State successor : successors) {
							if (!contains(prefix, successor)){
								stack.push(successor);
								currentStackSize++;
							}
						}
					} else if (option == 4 && curr.depth == cutoff) {
						for (State s : prefix)
							s.printState(1);
						break;
					} 	
				}
				
				if (option != 5)
					break;
				
				prefix.clear();
				cutoff++;
				
				
			}
		}
	}
}
package intParti;

import java.util.*;

/**
 * Kreher's algorithm3.4
 * This class generate integer partition in standard form
 * 5
 * 4+1
 * 3+2
 * 2+2+1
 * 2+1+1+1
 * 1+1+1+1+1
 * 
 * Data element starts from index 1.
 * @author cs50
 *
 */
public class GenPartition3 {
	int [] a;
	int m;
	Vector <int[]>list; 
	private void recPartition2(int m, int b, int n) {
		if(m==0) {
			//ArrayViewer.view1DIntArray(a);
			int [] bb = ConjPartition.conjPartition(a); // data starts from index 1
			//ArrayViewer.view1DIntArray(bb);
			a[n]=0;
			list.add(bb);
		} else {
			for(int i=1; i<=Math.min(b, m); i++) {
				a[n+1] = i;
				recPartition2(m-i, i, n+1);
			}
		}
	}
	/*
	 * @param	b	number of blocks
	 */
	public Vector<int[]> getPartitions(int b) {
		list.removeAllElements();
		for(int i=0; i<a.length; i++)
			a[i]=0;
		a[1]=b;
		recPartition2(m-b, b, 1);		
		return list;
	}
	/**
	 * Get all partitions
	 * @param mm
	 */
	public Vector<int[]> getAllPartitions() {
		list.removeAllElements();
		for(int i=1; i<=m; i++) {
			a[1] = i;
			recPartition2(m-i, i, 1);
		}
		return list;
	}
	/**
	 * Constructor
	 * 
	 * @param mm	number of agents
	 */
	public GenPartition3(int mm) {
		m = mm;
		a = new int[m+1];
		list = new Vector<int[]>();
	}
	private void run() {
		for(int i=1; i<=m; i++) {
			a[1]=i;
			recPartition2(m-i, i, 1);
			//Logger.logOut("---\n");
		}
		for(int i=0; i<list.size(); i++) {
			ArrayViewer.view1DIntArray(list.elementAt(i));
		}
	}
	public ArrayList<int[]> getPartition(){
		ArrayList<int[]> partition = new ArrayList<int[]>();
		for(int i=1; i<=m; i++) {
			a[1]=i;
			recPartition2(m-i, i, 1);
			//Logger.logOut("---\n");
		}
		for(int i=0; i<list.size(); i++) {
			int[] row = new int[list.elementAt(i).length-1];
			for (int j = 1; j < list.elementAt(i).length; j++) {
				row[j-1] = list.elementAt(i)[j];
			}
			partition.add(row);
		}
		return partition;
	}
	public static void main(String [] args) {
		int m = 0;
		GenPartition3 g;
		
		try {
			m = Integer.parseInt("5");
		} catch(Exception ex) {
			Logger.logErr("Usage: lib.GenPartition3 m\n");
			ex.printStackTrace();
		}
		g = new GenPartition3(5);
		//g.run();
		ArrayList<int[]> res = g.getPartition();
		for(int[]i : res) {
			for(int j : i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
}

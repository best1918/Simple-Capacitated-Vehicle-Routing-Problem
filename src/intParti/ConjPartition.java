package intParti;

public class ConjPartition {
	/**
	 * 
	 * @param a	is in form a1, a2, a3; ie., a0 is not used.
	 * @return
	 */
	public static int [] conjPartition(int [] a) {
		int [] b = new int[a.length];
		for(int i=1; i<=a[1]; i++)
			b[i]=1;
		int nn= a[1];
		int n = a.length-1;
		for(int j=2; j<=n; j++) 
			 for(int i=1; i<=a[j]; i++)
				 b[i]+=1;
		int [] bb = new int[nn+1];
		for(int i=1; i<=nn; i++)
			bb[i]=b[i];
		return bb;
	}
}

package intParti;

import java.math.BigInteger;
import java.util.ArrayList;
/*
 * This classes does not use element [0]. 
 */
public class NChooseR {
		int [] c;
		
	/*
	 * Return next subset in increment order.
	 */
	public static int [] nextR(int [] t, int n, int r) {
		int [] u = new int[t.length];
		for(int i=0; i<t.length; i++)
			u[i] = t[i];
		int i=r;
		while(i>=1 && t[i]==n-r+i)
			i=i-1;
		if(i>0) {
			for(int j=i; j<=r; j++) {
				u[j]=t[i]+1+j-i;
			}
			return u;
		}
		return null;
	}

	public ArrayList<int[]> getNCR(int n, int r) {
		ArrayList<int[]> NCR = new ArrayList<int[]>();
		c = new int[r+1];
		for(int i=0; i<c.length; i++)
			c[i] = i;
		do {
			NCR.add(c);
			c = nextR(c, n, r);
		} while(c!=null);
		
		return NCR;
	}
	public static long noNCR(int n, int r) {
		long ncr = (fact(n)/(fact(n-r)*fact(r)));
		//System.out.println("fact(n="+n+"):"+fact(n)+
		//		", fact(r="+r+"):"+fact(r)+
		//		", fact("+n+"-"+r+"):"+fact(n-r)+
		//		", ncr("+n+","+r+"):"+ncr);
		return ncr;
	}

	public static long fact(int n) {
		long f = 1;
		for (int i = 1; i <= n; i++)
			f *= i;
		return f;
	}
	
	public static BigInteger noNCRBigInt(int n, int r) {
		BigInteger ncr = factBigInt(n).divide(factBigInt(n-r).multiply(factBigInt(r)));
		return ncr;
	}
	public static BigInteger factBigInt(int n) {
		BigInteger f = new BigInteger("1");
		for(int i=1; i<=n; i++)
			f = f.multiply(new BigInteger(String.valueOf(i)));
		return f;
	}

	public static void main(String [] args) {
		int n = 8;
		int r = 4;
		try{
			n = Integer.parseInt("5");
			r = Integer.parseInt("1");
			if(r>n||r==0||n==0)
				Logger.logOut("Invalid n, r");
			
//			new NChooseR(n, r);
			NChooseR chooseR = new NChooseR();
			
			System.out.println();
			ArrayList<int[]> res = chooseR.getNCR(n,r);
			for (int[] is : res) {
				ArrayViewer.view1DIntArray(is);
			}
		} catch(Exception e) {
			Logger.logOut("Usage: java lib.NChooseR n r");
//			new NChooseR(n, r);
		}
	}
}

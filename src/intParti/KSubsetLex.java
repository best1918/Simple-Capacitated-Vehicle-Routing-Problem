package intParti;
/**
 * This class generates k subset in lex order, using 1, 2, 3,... 
 * @author cs50
 *
 */

public class KSubsetLex {
	int n, k;
	public KSubsetLex(int nn, int kk) {
		k = kk;
		n = nn;
	}
	public void run() {
		int [] tmp = new int[k+1];
		for(int i=1; i<tmp.length; i++)
			tmp[i] = i;
		do {
			ArrayViewer.view1DIntArray(tmp);
			tmp = KSubsetLexSuccessor(tmp,n,k);			
		}while(tmp!=null);
	}
	private int[] KSubsetLexSuccessor(int [] t, int n, int k) {
		int [] u = new int[t.length];
		for(int i=0; i<u.length; i++)
			u[i]=t[i];
		int i = k;
		while(i>=1&&t[i]==n-k+i)
			i-=1;
		if(i==0)
			return null;
		else
			for(int j=i; j<=k; j++)
				u[j]=t[i]+1+j-i;
		return u;
	}
	public static void main(String [] args) {
		int n, k;
		try {
			n = Integer.parseInt("4");
			if(args.length>1) {
				k = Integer.parseInt("0");
				KSubsetLex sub = new KSubsetLex(n, k);
				sub.run();
			} else {
				for(int i=1; i<=n; i++) {
					k=i;
					KSubsetLex sub = new KSubsetLex(n, k);
					sub.run();
				}
			}
			
		}catch(Exception ex) {
			Logger.logErr("Usage: java KSubsetLex n k\n");
			ex.printStackTrace();
		}
	}
}

package intParti;

import java.util.ArrayList;

public class HelloWorldBeforMap {
	static ArrayList<int[]> distance = new ArrayList<int[]>();
	static ArrayList<int[]> partition = new ArrayList<int[]>();
	static ArrayList<int[]> permutation = new ArrayList<int[]>();
	static String[] vehicleName = { "vehicle1", "vehicle2", "vehicle3", "vehicle4" };
	static String[] customerName = { "Depot", "Cust1", "Cust2", "Cust3", "Cust4", "Cust5" };
	static int dist[][] = { { 25, 25 }, { 5, 7 }, { 9, 12 }, { 34, 47 }, { 20, 32 }, { 42, 29 } };
	static int customerNumber = dist.length - 1;
	static int vehicleNumber = vehicleName.length;
	/*
	 * EX-4
	 * TODO:
	 * data preparation phase
	 * 1. find Distance array between any customer
	 * 2. Customer permutation
	 * 3. Create customer group (Partition)
	 * 
	 * Brute force phase
	 * 1. Find every possibility it can be and the condition is vehicle < customer 
	 * 	1.1 Permutation -> Partition
	 * 	1.2 Check m<n
	 * 	1.3 Pair vehicles and Customers with nCr
	 * 2. Map and calculate Distance from distance array
	 * 
	 * TIPS: 
	 * use NChooseR 
	 * Ex. we already have 4 Vehicles but we have 5 customers 
	 * we have to use [partition.get(j).length] for nCr to pair Customer and vehicle
	 * Customer Group: 4,1 
	 * Vehicle nCr: 1,2 
	 * Meaning: 
	 * Vehicle1 -> Group4 
	 * Vehicle2 -> Group1
	 * 
	 * PS: In this code, the result depends on the data vehicle and Customer [vehicleName[],dist[][]]
	 *
	 * */

	public static void main(String[] args) {
		// Math.round(Math.sqrt(Math.pow((dist[j][0] - dist[i][0]),2)+
		// Math.pow((dist[j][1] - dist[i][1]),2)))

		HelloWorldBeforMap helloWorld = new HelloWorldBeforMap();
		GenPartition3 genPartition3 = new GenPartition3(customerNumber);

		partition = genPartition3.getPartition(); // Customer Group
		distance = helloWorld.findDistance(dist); // find Distance between any customer
		permutation = helloWorld.usePermutation(customerNumber); // Customer Permutation without Depot

		System.out.println("Find Distance()");
		helloWorld.ArrayListIntArrViewer(distance, true);
		System.out.println("partition()");
		helloWorld.ArrayListIntArrViewer(partition, false);
//		System.out.println("Permutation()");
//		helloWorld.ArrayListIntArrViewer(purmutation, false);

		helloWorld.Bruteforce();
	}

	public void Bruteforce() {
		NChooseR nChooseR = new NChooseR();
		int step = 0;
		System.out.println("Bruteforce()");
		for (int i = 0; i < permutation.size(); i++) { // i = Permutation List Index
			System.out.println("Permutation: " + (i + 1));
			for (int j = 0; j < partition.size(); j++) { // j = Partition List Index
				if (partition.get(j).length <= vehicleNumber) { // Check Vehicle < Customers
					System.out.println("Partition: " + (j + 1));
					ArrayList<int[]> nCr = nChooseR.getNCR(vehicleNumber, partition.get(j).length);
					for (int k = 0; k < nCr.size(); k++) { // k = All nCr Index
						int inPermute_Index = 0; // inPermute_Index = Permutation Index
						for (int l = 0; l < partition.get(j).length; l++) { // k = Partition Index
							System.out.print(++step +": "+ vehicleName[nCr.get(k)[l + 1]-1]+" -> ");
							for (int m = 0; m < partition.get(j)[l]; m++) {
								System.out.print(permutation.get(i)[inPermute_Index] + " ");
								inPermute_Index++;
							}
							
							System.out.println();

						}
						System.out.println();
						
					}
					
					System.out.println("========================");
				}

				
			}
		}
		System.out.println("Permute & Partition & nCr: "+step);
	}

	public void ArrayListIntArrViewer(ArrayList<int[]> arrList, boolean tab) {
		System.out.println("ArrayList Size: " + arrList.size());
		for (int[] is : arrList) {
			for (int i = 0; i < is.length; i++) {
				System.out.print((i < is.length - 1) ? (is[i] + "," + ((tab) ? ("\t") : (""))) : (is[i]));
			}
			System.out.println();
		}
	}

	public ArrayList<int[]> usePermutation(int customerNumber) {
		ArrayList<int[]> purmutation = new ArrayList<int[]>();
		PermutationGenerator permutationGenerator = new PermutationGenerator(customerNumber);
		while (permutationGenerator.hasMore()) {
			short[] t = permutationGenerator.getNext();
			int[] temp = new int[t.length];
			for (int i = 0; i < t.length; i++) {
				temp[i] = t[i];
			}
			purmutation.add(temp);
		}
		return purmutation;
	}

	public ArrayList<int[]> findDistance(int[][] dist) {
		ArrayList<int[]> distance = new ArrayList<int[]>();
		for (int i = 0; i < dist.length; i++) {
			int[] row = new int[dist.length];
			for (int j = 0; j < dist.length; j++) {
				row[j] = (int) Math.round(
						Math.sqrt(Math.pow((dist[j][0] - dist[i][0]), 2) + Math.pow((dist[j][1] - dist[i][1]), 2)));
			}
			distance.add(row);
		}
		return distance;
	}
}

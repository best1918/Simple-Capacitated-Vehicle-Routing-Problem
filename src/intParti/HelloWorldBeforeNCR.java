package intParti;

import java.util.ArrayList;

public class HelloWorldBeforeNCR {
	static ArrayList<int[]> distance = new ArrayList<int[]>();
	static ArrayList<int[]> partition = new ArrayList<int[]>();
	static ArrayList<int[]> permutation = new ArrayList<int[]>();
	static int vehicleNumber = 4;
	static String[] customerName = { "Depot", "Cust1", "Cust2", "Cust3", "Cust4", "Cust5" };
	static int dist[][] = { { 25, 25 }, { 5, 7 }, { 9, 12 }, { 34, 47 }, { 20, 32 }, { 42, 29 } };
	static int customerNumber = dist.length - 1;

	public static void main(String[] args) {
		// Math.round(Math.sqrt(Math.pow((dist[j][0] - dist[i][0]),2)+
		// Math.pow((dist[j][1] - dist[i][1]),2)))

		HelloWorldBeforeNCR helloWorld = new HelloWorldBeforeNCR();
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

		helloWorld.groupUsers();
	}

	public void groupUsers() {
		NChooseR nChooseR = new NChooseR(); 
		int step = 0;
		System.out.println("groupUsers()");
		for (int i = 0; i < permutation.size(); i++) { // i = Permutation List Index
			System.out.println("Permutation: " + (i + 1));
			for (int j = 0; j < partition.size(); j++) { // j = Partition List Index
				int inPermute_Index = 0; // inPermute_Index = Permutation Index
				System.out.println("Partition: " + (j + 1));
				for (int k = 0; k < partition.get(j).length; k++) { // k = Partition Index
					/*
					 * TODO:
					 * use NChooseR
					 * Ex. 
					 * we already have 4 Vehicles but we have 5 customers
					 * we have to use [partition.get(j).length] for nCr to pair Customer and vehicle
					 * Customer Group: 4,1
					 * Vehicle nCr: 1,2
					 * Meaning: 
					 * Vehicle1 -> Group4
					 * Vehicle2 -> Group1
					 *  
					 * */
					if (partition.get(j).length <= vehicleNumber) { // Check Vehicle < Customers
						for (int l = 0; l < partition.get(j)[k]; l++) {
							System.out.print(permutation.get(i)[inPermute_Index] + " ");
							inPermute_Index++;
						}
						System.out.println();
					}

				}
				System.out.println("========================");
				step++;
			}
		}
		System.out.println(step);
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

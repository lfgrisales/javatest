package test2;

import java.util.HashMap;
import java.util.Map;

public class NonDivisibleSubset {
	
	static int nonDivisibleSubset(int k, int[] S) {
       Map<Integer,Integer> nonDivisibleNumbers = new HashMap<>();
	   for (int i = 0; i < S.length; i++) {
		     for (int j = i+1; j < S.length; j++) {
				  if(((S[i]+S[j]) % k) != 0) {
					  addNonDivisibleNumber(nonDivisibleNumbers, S[i], k);
					  addNonDivisibleNumber(nonDivisibleNumbers, S[j], k);
					 
				  }
			}
	     }
	   
	   System.out.println(nonDivisibleNumbers.toString());
       return nonDivisibleNumbers.size();
    }
	
	public static void addNonDivisibleNumber(Map<Integer,Integer> nonDivisibleNumbers,
										int candidateNumber, int k){
		boolean numberCanBeAdded = true;
		if(!nonDivisibleNumbers.containsKey(candidateNumber)){
			for (Map.Entry<Integer, Integer> entry: nonDivisibleNumbers.entrySet()) {
				 if((entry.getValue() + candidateNumber) % k == 0) {
					 numberCanBeAdded = false;
					 break;
				 }
			}
			
			if(numberCanBeAdded)
				nonDivisibleNumbers.put(candidateNumber, candidateNumber);
		}
		
		
	}
	
	public static void main(String[] args) {
		//int[] S = {1, 7, 2, 4};
		int n = 15;
		int[] S = new int[n];
		String[] SItems="278 576 496 727 410 124 338 149 209 702 282 718 771 575 436".split(" ");
	    int k=7;
		for (int i = 0; i < 15; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }
		System.out.println(nonDivisibleSubset(k, S));
		
		int[] x = {19, 10, 12, 10, 24, 25, 22};
		int[] x2 = {22, 25, 24, 10, 12, 10, 19};
		int[] x1 = {1, 7, 2, 4};
		int var = 3;
		for(int i=0; i<x1.length; i++)
			for(int j=i+1;j<x1.length; j++)
				System.out.println(x1[i]+" + "+x1[j]+" mod "+var+" = "+(x1[i]+x1[j])%var);
		
		
		System.out.println(nonDivisibleSubset(var, x2));
		
		System.out.println(21836421%13);
	}

}

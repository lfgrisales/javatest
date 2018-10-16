package test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MagicSquare {
	
	public static final int magicNumber = 15;
	
	static int formingMagicSquare(int[][] s) {
        int cost=0;
        int valueToExchange=0;
        LinkedList<Integer> listMissingNumbers= (LinkedList)getMissingNumbersInSquare(s);
        System.out.println("missingNumbers: "+listMissingNumbers.toString());
		while(!isMagicSquareValid(s,magicNumber)){
			for(int i=0; i< s.length; i++){
				for(int j=0; j< s[i].length; j++) {
					if(positionCanBeChanged(s, i, j, magicNumber)) {
						Iterator<Integer> it = listMissingNumbers.iterator();
						 
					    while(it.hasNext()){
					    	valueToExchange = s[i][j];
					    	s[i][j] = it.next();
					    	//if after the exchanged the value, that position can no be changed,
					    	//means that the exchanged number should be there
					    	if(!positionCanBeChanged(s, i, j, magicNumber)
					    			&& !changeCausedValueGTMagicNumber(s, i, j, magicNumber)) {
					    		cost+=Math.abs(s[i][j]-valueToExchange);
					    		it.remove();//element is removed from remainingnumbers and inserted in matrix
					    		if(!valueExistInSquare(s,valueToExchange))
					    			listMissingNumbers.add(valueToExchange);
					    		break;
					    	}else {
					    		//like the exchange did not have any effect, then we revert the exchange
					    		//in the matrix
					    		s[i][j] = valueToExchange;
					    	}
					       //System.out.println(it.next());
					    }
					}
					printMatrix(s);
					System.out.println("missingNumbers: "+listMissingNumbers.toString());
				}
			}
		}
		
      return cost;
    }
	
	static boolean valueExistInSquare(int[][] s, int value) {
		for(int i=0; i< s.length; i++){
			for(int j=0; j< s[i].length; j++) {
				if(s[i][j]==value)
					return true;
			}
		}
		return false;
	}
	
	static boolean isMagicSquareValid(int[][] s, int magicNumber) {
          boolean isMagicSquareValid = true;
		
          //validate rows and columns, that the sum must result in the magic number
          for(int pointer = 0; pointer < s.length; pointer++){
              if((s[pointer][0] + s[pointer][1] + s[pointer][2]) != magicNumber) 
            	  return false;
              if((s[0][pointer] + s[1][pointer] + s[2][pointer]) != magicNumber) 
            	  return false;
          }
          
          if((s[0][0] + s[1][1] + s[2][2]) != magicNumber 
        		  || (s[0][2] + s[1][1] + s[2][0]) != magicNumber) 
        	  return false;
          
	      return isMagicSquareValid;
	}
	
	static List<Integer> getMissingNumbersInSquare(int[][] s) {
		List<Integer> listMissingNumbers = new LinkedList<>();
		Map<Integer,Integer> mapExistingNumbers = new HashMap<>();
		//loop the matrix and add all unique numbers in the map
		for(int i=0; i< s.length; i++){
			for(int j=0; j< s[i].length; j++) {
				mapExistingNumbers.put(s[i][j], s[i][j]);
			}
		}
		
		for(int x=1; x <=9; x++) {
			if(!mapExistingNumbers.containsKey(x))
				listMissingNumbers.add(x);
		}
		
	    return listMissingNumbers;
	}
	
	static boolean positionCanBeChanged(int[][] s, int posX, int posY, int magicNumber) {

		boolean posCanBeChanged = true;
		
		//validate if summing horizontal values result in the magic number
		if(s[posX][0] + s[posX][1] + s[posX][2] == magicNumber ) 
			posCanBeChanged = false;
		//validate if summing horizontal values result in the magic number
		if(s[0][posY] + s[1][posY] + s[2][posY] == magicNumber ) 
			posCanBeChanged = false;
			 //validate if de position is a vertex
			// in that case, it must validate diagonals
		 if((posX==0 && posY==0)
			      || (posX==2 && posY==2)
			      || (posX==1 && posY==1)){
			    	  
			     if(s[0][0] + s[1][1] + s[2][2] == magicNumber ) 
			        	posCanBeChanged = false;
			     }
		 if((posX==0 && posY==2)
		          || (posX==2 && posY==0) 
		          || (posX==1 && posY==1)){
			  
			  if(s[0][2] + s[1][1] + s[2][0] == magicNumber ) 
		        	posCanBeChanged = false;
			 
		 }
		
	     return posCanBeChanged;
	}
	
	
	static boolean changeCausedValueGTMagicNumber(int[][] s, int posX, int posY, int magicNumber) {

		
		//validate if summing horizontal values result in the magic number
		if(s[posX][0] + s[posX][1] + s[posX][2] > magicNumber ) 
			return true;
		//validate if summing horizontal values result in the magic number
		if(s[0][posY] + s[1][posY] + s[2][posY] > magicNumber ) 
			return true;
			 //validate if de position is a vertex
			// in that case, it must validate diagonals
		 if((posX==0 && posY==0)
			      || (posX==2 && posY==2)
			      || (posX==1 && posY==1)){
			    	  
			     if(s[0][0] + s[1][1] + s[2][2] > magicNumber ) 
			    	 return true;
			     }
		 if((posX==0 && posY==2)
		          || (posX==2 && posY==0) 
		          || (posX==1 && posY==1)){
			  
			  if(s[0][2] + s[1][1] + s[2][0] > magicNumber ) 
				  return true;
			 
		 }
		
	     return false;
	}
	
	static void printMatrix(int[][] matrix) {
		String result="";
		for(int i=0; i< matrix.length; i++){
			for(int j=0; j< matrix[i].length; j++) {
			    result+=matrix[i][j]+" ";
			}
			System.out.println(result);
			result="";
		}
	}
	
	public static void main(String[] args) {
	
		//int[][] matrix = {{4, 9, 2}, {3, 5, 7}, {8, 1, 5}};
		//int[][] matrix = {{4, 8, 2}, {4, 5, 7}, {6, 1, 6}};
		//int[][] matrix = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}};
		int[][] matrix = {{1, 1, 1}, {4, 5, 4}, {6, 4, 2}};
		//System.out.println(formingMagicSquare(matrix));
		
		List<Integer> arrlist = new ArrayList<>();
		List<Integer> llist = new LinkedList<>();
		
		for (int i = 1; i < 7; i++) {
			arrlist.add(i);
			llist.add(i);
		}
		
		System.out.println(arrlist.toString());
		System.out.println(llist.toString());
		
		
		
	}
	
	

}

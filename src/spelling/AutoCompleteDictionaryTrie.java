package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		//root is initalized with the alphabet
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char[] alphabetArray = alphabet.toCharArray();
		for (char c : alphabetArray) {
			root.insert(c);
		}
		//letters a and i are words as well
//		root.getChild('a').setEndsWord(true);
//		root.getChild('i').setEndsWord(true);
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		System.out.println(word);
		char[] wordArray = word.toLowerCase().toCharArray();
		TrieNode currChild = root.getChild(wordArray[0]);
		TrieNode childAdded = null;
		boolean  wordAdded = false;
		if(wordArray.length > 1 && currChild != null) {
			for (int i = 1; i < wordArray.length; i++) {
				childAdded = currChild.insert(wordArray[i]);
				currChild = currChild.getChild(wordArray[i]);
				if(childAdded != null){
					if(i == (wordArray.length-1)){
					  childAdded.setEndsWord(true);
					  this.size++;
					}
					else
					  childAdded.setEndsWord(false);
				}//child not added because already exist
				else {
					 if(i == (wordArray.length-1) && !currChild.endsWord()){
						  currChild.setEndsWord(true);
						  this.size++;
						}
				}
				
				
				
				
					
			}
		}//is a word with only one letter, probably a or i
		else {
			 if(currChild != null && !currChild.endsWord()) {
				 currChild.setEndsWord(true);
				 this.size++;
			 }
		}
			
	    return wordAdded;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		boolean  isWord = false;
		if(!s.isEmpty()) {
			char[] wordArray = s.toLowerCase().toCharArray();
			TrieNode currChild = root.getChild(wordArray[0]);
			
			if(wordArray.length > 1) {
				for (int i = 1; i < wordArray.length; i++) {
					currChild = currChild.getChild(wordArray[i]);
					if(currChild == null){
						break;
					}
	
						
				}
			}
			
			if(currChild != null && currChild.endsWord()){
				isWord = true;
			}
		}
	    return isWord;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	LinkedList<String> listOfPredictCompletions = new LinkedList<>();
    	if(prefix.isEmpty()) {
    		prefix = "a";
    	}
	    	char[] prefixArray = prefix.toLowerCase().toCharArray();
	 		TrieNode currChild = root.getChild(prefixArray[0]);
	 		//find steam for to look childs for predictions
	 		if(prefixArray.length > 1 && currChild != null) {
	 			for (int i = 1; i < prefixArray.length; i++) {
	 				currChild = currChild.getChild(prefixArray[i]);
	 				if(currChild == null){
	 					break;
	 				}
	
	 					
	 			}
	 		}
	 		
	 		//the stem was found
	 		if(currChild != null){
	 			Queue<TrieNode> q = new LinkedList<>();
	 			Set<Character> currChildChilds = null;
	 			TrieNode tempChildOfChilds = null;
	 			q.add(currChild);
	 			if(currChild.endsWord())
	 				listOfPredictCompletions.add(currChild.getText());
	 			
	 			while(!q.isEmpty() && listOfPredictCompletions.size() < numCompletions) {
	 				currChild = q.remove();
	 				currChildChilds = currChild.getValidNextCharacters();
	 				for (Character c : currChildChilds) {
	 					tempChildOfChilds = currChild.getChild(c);
	 					q.add(tempChildOfChilds);
	 					if(tempChildOfChilds.endsWord()){
	 						listOfPredictCompletions.add(tempChildOfChilds.getText());
	 						//validate if the last word added complete the required number of 
	 						//completions
	 						if(listOfPredictCompletions.size() == numCompletions)
	 							break;
	 						
	 					}
					}
	 			}
	 			
	 			
	 		
    	}
 		
 	   
    	 
    	 
         return listOfPredictCompletions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		if(curr.endsWord())
 		  System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

 	public static void main(String[] args) {
 		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();
		

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		smallDict.addWord("vacation");
		
		System.out.println(smallDict.size());
		System.out.println(smallDict.isWord(""));
		
		smallDict.printTree();
		
		AutoCompleteDictionaryTrie largeDict = new AutoCompleteDictionaryTrie();

	
		
		DictionaryLoader.loadDictionary(largeDict, "data/words.small.txt");
		
		List<String> completions = smallDict.predictCompletions("",  0);
		System.out.println(completions.toString());
		
		System.out.println(completions.size());
		
	}
	
}
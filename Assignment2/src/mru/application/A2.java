package mru.application;


import java.util.Comparator;
import java.util.Scanner;

/** 
 * COMP 2503 Fall 2023 Assignment 2 
 * 
 * This program reads a input stream and keeps track of the statistics for avengers
 * mentioned by name, alias, or performer's last name.
 *
 * @author Maryam Elahi
 * @date Fall 2023
*/

public class A2 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	
	private SinglyLinkedList<Avenger> mentionList = new SinglyLinkedList<Avenger>();
	private SinglyLinkedList<Avenger> alphabticalList = new SinglyLinkedList<Avenger>();
	private SinglyLinkedList<Avenger> mostPopularAvenger = new SinglyLinkedList<Avenger>();
	private SinglyLinkedList<Avenger> mostPopularPerformer = new SinglyLinkedList<Avenger>();
	
	/**
	 * creates an instance of A2 and runs it
	 * @param args arguments 
	 * @throws FileNotFoundException if issue with file handling
	 */
	public static void main(String[] args) {
		A2 a1 = new A2();
		a1.run();
	}

	/**
	 * runs the program. reads input, loading text files and creating ordered lists 
	 * @throws FileNotFoundException if issue with file handling
	 */
	public void run() {
		readInput();
		createdOrderedLists();
		printResults();
	}

	/**
	 * made for easier testing purposes, functions the same as readInput just takes file path for faster tests
	 * @param FILE_PATH
	 * @throws FileNotFoundException
	 */ 

	/**
	 * creates a searcher within the mentionList to then add to the other lists
	 */
	private void createdOrderedLists() {
		// TODO: 
		/* Create a mover and traverse through the mentionList.
		// Add each avenger to the other three lists. 
		// Hint: For this assignment you can expose the reference 
		// 	     to the first element in the linked list, and create a mover to traverse the list.
		//       (The better solution is to create an iterator, but we haven't learned about them, 
		// 		  will talk about iterators later.)
		*/ 
		Node<Avenger> current = mentionList.getFirst();
		while(current != null) {
			Avenger a = current.getData();
			Comparator<Avenger> alphabeticalOrder = Comparator.naturalOrder();
			Comparator<Avenger> popularAvengerOrder = new AvengerComparator();
			Comparator<Avenger> popularPerformerOrder = new PerformerComparator();
			alphabticalList.addInOrder(a, alphabeticalOrder);
			mostPopularAvenger.addInOrder(a, popularAvengerOrder);
			mostPopularPerformer.addInOrder(a, popularPerformerOrder);
			
			current = current.getNext();
			
		}
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput() {
		/*
		 In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name, or performer last name then
				- Create a new avenger object with the corresponding alias and last name and performer last name.
				- if this avenger has already been mentioned, increase the corresponding frequency count for the object already in the mentionList.
				- if this avenger has not been mentioned before, add the newly created avenger to the mentionList, remember to update the corresponding frequency.
		*/
		while (input.hasNext()) {

			String word = cleanWord(input.next());

			if (word.length() > 0) {
				
				totalwordcount++;
				updateAvengerList(word);
			}
		}
	}
	
	/**
	 * takes the cleaned word and searches the list to either create a 
	 * new object or increase an existing frequency
	 * @param word
	 */
	private void updateAvengerList(String word) {
		
		for(int i = 0; i < avengerRoster.length; i++) {
			if(word.equals(avengerRoster[i][0]) || word.equals(avengerRoster[i][1]) || word.equals(avengerRoster[i][2])) {
				Avenger a = findAvengerInMentionList(word);
				
				if(a != null) {
					if (word.equals(avengerRoster[i][0])) 
						a.setAliasFreq(a.getAliasFreq() + 1);
					else if (word.equals(avengerRoster[i][1])) 
						a.setNameFreq(a.getNameFreq() + 1);   
					else if (word.equals(avengerRoster[i][2]))
			            a.setPerformerFreq(a.getPerformerFreq() + 1); 
				} else {
					a = new Avenger();
					
                    a.setHeroAlias(avengerRoster[i][0]);
                    a.setHeroName(avengerRoster[i][1]);
                    a.setPerformer(avengerRoster[i][2]);
                    
                    if (word.equals(avengerRoster[i][0])) 
                    	a.setAliasFreq(1);
                    else if (word.equals(avengerRoster[i][1])) 
                    	a.setNameFreq(1);
                    else if (word.equals(avengerRoster[i][2])) 
                    	a.setPerformerFreq(1);
                    
                    mentionList.addToEnd(a);
				}			
			}
		}
	}

	/**
	 * runs through the mentionList to check if the parameter matches
	 * @param word
	 * @return word from array otherwise null if it doesn't exist
	 */
	private Avenger findAvengerInMentionList(String a) {
		Node<Avenger> current = mentionList.getFirst();
		
		while(current != null) {
			Avenger foundA = current.getData();
			
	        if (foundA.getHeroAlias().equalsIgnoreCase(a) || foundA.getHeroName().equalsIgnoreCase(a) || foundA.getPerformer().equalsIgnoreCase(a)) {
	                return foundA;
	            }

	            current = current.getNext();
	        }
		return null;
		}



	/**
	 * cleans a word by cleaning apostrophes, converting the word to lowercase, 
	 * removes all other non-alphabetical words 
	 * @param next the word to clean and process 
	 * @return the cleaned and processed word 
	 */
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		printList(mentionList);

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		printTopN(mostPopularAvenger);
		
		System.out.println();

		System.out.println("Top " + topN + " most popular performers:");
		// Todo: Print the most popular performers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		printTopN(mostPopularPerformer);
		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		printList(alphabticalList);
		
		System.out.println();
	}

	/**
	 * prints the the results depending on the given list
	 * made for repetitive coding
	 * @param SinglyLinkedList
	 */
	private void printList(SinglyLinkedList<Avenger> list) {
		Node<Avenger> current = list.getFirst();
		while(current != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
	}
	
	/**
	 * prints the the results depending on the given list
	 * made for repetitive coding
	 * @param SinglyLinkedList
	 */
	private void printTopN(SinglyLinkedList<Avenger> list) {
		Node<Avenger> current = list.getFirst();
		int count = 0;
		
		while(current != null && count < topN) {
			System.out.println(current.getData());
			current = current.getNext();
			count++;
		}
	}
}


package com.CardDeck.main;

import java.util.*;

public class Deck {

	public int numberOfCards = 52;
	private int eachRank = numberOfCards/4;
	private Random r = new Random();
	
	public ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		
		for(int i = 1; i <= 4; i++) { 
			for(int j = 1; j <= eachRank; j++) {
				deck.add(new Card(i, j));
			}
		}

	}
	
	public void printDeck() {
		for (Card t: deck) {
			t.printStats();
		}
		
	}
	
	public void Shuffle() {
		//System.out.println("Shuffling...");
		
		for(int i = 0; i < deck.size(); i++) {
			Card tempCard = deck.get(i);
			deck.remove(i);
			deck.add(r.nextInt(52), tempCard);
		}
	}
	
	public static void main(String[] args) {

	}

}

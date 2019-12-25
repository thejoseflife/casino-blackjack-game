package com.CardDeck.main;

import java.util.*;

public class Player {

	public ArrayList<Card> hand = new ArrayList<Card>();
	
	public int bet = 0;
	public int index = 0;
	
	public int turns = 0;
	
	public Player(int index) {
		this.index = index;
	}
	
	public int cardSum() { //Returns sum of cards' numeric values
		int total = 0;
		for(Card t: hand) {
			total += t.getNumValue();
		}
		return total;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}
	
	public void takeCard(int numberOfCards, ArrayList<Card> deck) { //Takes last card of a deck
		for(int i = 0; i < numberOfCards; i++) {
			hand.add(deck.get(deck.size() - 1));
			deck.remove(deck.size() - 1);
			deck.add(0, deck.get(i));
		}
		
	}
	
	public void printHand() { //Prints all the cards in the hand
		for(Card t: hand) {
			t.printStats();
		}
	}
	
	public static void main(String[] args) {
		
	}

}

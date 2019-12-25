package com.CardDeck.main;

public class Card {
	
	public enum SUIT {
		DIAMONDS,
		SPADES,
		HEARTS,
		CLUBS
	}
	public int faceValue;
	public int numericValue;
	public SUIT suit;
	
	public Card(int s, int val) {
		if(val > 13) val = 13;
		if(val < 1) val = 1;
		faceValue = val;
		numericValue = val;
		
		if(s == 1) suit = SUIT.CLUBS;
		else if(s == 2) suit = SUIT.DIAMONDS;
		else if(s == 3) suit = SUIT.HEARTS;
		else if(s == 4) suit = SUIT.SPADES;
	}
	
	public String stringValue() { //Gives value example 1, jack, ace
		if(faceValue == 11) return "Jack";
		else if(faceValue == 12) return "Queen";
		else if(faceValue == 13) return "King";
		else if(faceValue == 1) return "Ace";
		
		return String.valueOf(faceValue);
	}
	
	public int getNumValue() {
		return numericValue;
	}
	
	public void setNumValue(int val) {
		numericValue = val;
	}
	
	public void printStats() {
		System.out.println(stringValue() + " of " + suit);
	}
	
	public static void main(String[] args) {

	}

}

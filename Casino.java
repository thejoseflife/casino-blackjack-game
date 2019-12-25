package com.CardDeck.main;

import java.util.*;

public class Casino {
	
	Scanner s = new Scanner(System.in);
	
	public void playBlackJack() {
		BlackJackGame bjg = new BlackJackGame();
		bjg.startGame();
	}
	
	public static void main(String[] args) {
		Casino casino = new Casino();
		
		System.out.println("What would you like to play?");
		System.out.println("Games: BlackJack");
		String input = "";
		while(!input.equalsIgnoreCase("blackjack")) {
			input = casino.s.next();
		}
		
		if(input.equalsIgnoreCase("blackjack")) casino.playBlackJack();
	}

}

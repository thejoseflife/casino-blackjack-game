//SIDENOTE:
//I didn't know how to play BlackJack, so I did some online research, but I still may have made a few gameplay mistakes.

package com.CardDeck.main;

import java.util.*;

public class BlackJackGame {

	private boolean gameOver = false;

	private ArrayList<Card> deck = new ArrayList<Card>();
	private LinkedList<Player> players = new LinkedList<Player>();
	
	private final int MAXIMUM_PLAYERS = 5;
	private final int MINIMUM_BET = 5;
	private final int MAXIMUM_BET = 500;
	
	private int numberOfPlayers = 0;
	
	private Scanner s = new Scanner(System.in);
	
	public void combineDecks(int numberOfDecks) { //combines decks into main deck, also sets values of cards
		for(int i = 0; i < numberOfDecks; i++) {
			Deck Deck = new Deck();
			Deck.Shuffle();
			
			for(int j = 0; j < Deck.numberOfCards; j++) {
				if(Deck.deck.get(j).getNumValue() > 10) {
					Deck.deck.get(j).setNumValue(10);
				}
				deck.add(Deck.deck.get(j));
			}
		}
	}

	
	
	public void hit(Player player) {
		if(player.cardSum() < 17) {
			player.turns++;
			player.takeCard(1, deck);
			setAceValue(player);
			player.printHand();
			print("Total: " + player.cardSum() + "\n");
			checkForBust(player);
			while(player.cardSum() < 17) {
				displayOptions(player); 
			}
		} else {
			print("Cannot hit!");
			displayOptions(player);
		}
		
		
	}
	
	//No stand method needed, as it does nothing.
	
	public void doubleDown(Player player) {
		if(player.getTurns() == 0) {
			print("Player " + player.getIndex() + "'s bet is doubled and is given a card.");
			player.bet *= 2;
			player.takeCard(1, deck);
			player.printHand();
			print("Total: " + player.cardSum() + "\n");
			checkForBust(player);
		} else {
			print("Can only double down on first turn!");
		}
	}
	
	public void split(Player player) {
		if(player.hand.size() > 1) {
			if(player.hand.get(0).getNumValue() == player.hand.get(1).getNumValue()) {
				addPlayer(player.getIndex(), new Player(player.getIndex()));
				players.get(player.getIndex()).takeCard(1, player.hand);
				print("Player " + player.getIndex() + "'s turn.");
				player.printHand();
				print("Total: " + player.cardSum() + "\n");
				displayOptions(player);
			} else {
				print("Can only split with two cards of the same value!");
				displayOptions(player);
			}
		} else {
			print("Can only split if you have two cards!");
			displayOptions(player);
		}
	}
	
	public void surrender(Player player) {
		player.bet /= 2;
		print("Player " + player.getIndex() + " has lost $" + player.getBet() + "...");
		removePlayer(player);
	}
	
	public void dealerMove(Player dealer) {
		gameOver = true;
		print("Dealer's turn.");
		print("Card given to dealer.");
		dealer.takeCard(1, deck);
		dealer.printHand();
		print("Total: " + dealer.cardSum() + "\n");
		for(int i = 0; i < numberOfPlayers; i++) {
			checkWhoWon(players.get(i), dealer);
		}
		
	}
	
	public void hideDealerHand() {
		print("(Facedown Card)");
		for(int i = 1; i < players.get(players.size() - 1).hand.size(); i++) {
			players.get(players.size() - 1).hand.get(i).printStats();
		}
	}
	
	public void checkForBust(Player player) {
		if(player.cardSum() > 21) {
			print("Player " + player.getIndex() + " has lost $" + player.getBet() + "...\n");
			player.setIndex(0);
			
		} if(player.cardSum() == 21) {
			print("Player " + player.getIndex() + " has won $" + player.getBet() * 1.5 + "!\n");
			player.setIndex(0);
			
		}
	}
	
	public void checkWhoWon(Player player, Player dealer) {
		if(player.cardSum() <= 21 && dealer.cardSum() < player.cardSum()) {
			print("Player " + player.getIndex() + " has won $" + player.getBet() * 1.5 + "!");
		} else if(dealer.cardSum() == 21 && player.cardSum() != 21) {
			print("Player " + player.getIndex() + " has lost $" + player.getBet() + "...");
		} else if(player.cardSum() == 21) {
			print("Player " + player.getIndex() + " has won $" + player.getBet() * 1.5 + "!");
		} else if(dealer.cardSum() <= 21 && player.cardSum() < dealer.cardSum()) {
			print("Player " + player.getIndex() + " has lost $" + player.getBet() + "...");
		} else if(dealer.cardSum() > 21 && player.cardSum() <= 21) {
			print("Player " + player.getIndex() + " has won $" + player.getBet() * 1.5 + "!");
		} else if(dealer.cardSum() < 21 && dealer.cardSum() == player.cardSum()) {
			print("Player " + player.getIndex() + " has been refunded $" + player.getBet() + ".");
		}
	}
	
	public void displayOptions(Player player) {
		print("Hit(hit) | Stand(stand) | Double Down(dd) \nSplit(split) | Surrender(surr)");
		while(!gameOver) {
			String input = s.next();
			if(input.equalsIgnoreCase("hit")) {
				hit(player);
				break;
			} else if(input.equalsIgnoreCase("stand")) {
				break;
			} else if(input.equalsIgnoreCase("dd")) {
				doubleDown(player);
				break;
			} else if(input.equalsIgnoreCase("surr")) {
				surrender(player);
				break;
			} else if(input.equalsIgnoreCase("split")) {
				split(player);
				break;
			}
		}
	}
	
	public void setAceValue(Player player) {
		int total = player.cardSum();
		for(int i = 0; i < player.hand.size(); i++) {
			if(player.hand.get(i).getNumValue() == 1) {
				if(total + 10 <= 21) {
					player.hand.get(i).setNumValue(11);
				}
			}
		}
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
		//numberOfPlayers--;
	}
	
	public void addPlayer(int index, Player player) {
		players.add(index, player);
		numberOfPlayers++;
	}

	public void startGame() {
		combineDecks(6);
		print("===================================================");
		print("How many players? (Maximum 5)");
		while(numberOfPlayers <= 0 || numberOfPlayers > MAXIMUM_PLAYERS) {
			numberOfPlayers = s.nextInt();
		}
		for(int i = 1; i <= numberOfPlayers; i++) {
			players.add(new Player(i));
		}
		players.add(new Player(players.size()));
		
		print("How much would each player like to bet? (Min is 5, Max is 500)");
		for(int i = 0; i < numberOfPlayers; i++) {
			System.out.print("Player " + players.get(i).getIndex() + ": ");
			int bet = 0;
			while(bet < MINIMUM_BET || bet > MAXIMUM_BET) {
				bet = s.nextInt();
				players.get(i).setBet(bet);
			}
		}

		for(int i = 0; i < numberOfPlayers; i++) {
			print("Player " + players.get(i).getIndex() + " given 2 cards.");
			players.get(i).takeCard(2, deck);
			setAceValue(players.get(i));
			players.get(i).printHand();
			print("Total: " + players.get(i).cardSum() + "\n");
		}
		
		print("Dealer given 2 cards.");
		players.get(players.size() - 1).takeCard(2, deck);
		setAceValue(players.get(players.size() - 1));
		hideDealerHand();
		print("");
		
		for(int i = 0; i < numberOfPlayers; i++) {
			if(players.get(i).cardSum() == 21) {
				print("Player " + players.get(i).getIndex() + " has won $" + players.get(i).bet * 1.5 + "! \n");
				players.get(i).setIndex(0);
			}
		}

		for(int i = 0; i < numberOfPlayers; i++) {
			if(players.get(i).getIndex() > 0) {
				print("Player " + players.get(i).getIndex() + "'s turn.");
				players.get(i).printHand();
				print("Total: " + players.get(i).cardSum() + "\n");
				displayOptions(players.get(i));
			}
			
		}
		
		for(int i = 0; i < numberOfPlayers; i++) {
			if(players.get(i).getIndex() == 0) {
				numberOfPlayers--;
				removePlayer(players.get(i));
			}
		}
		
		dealerMove(players.get(players.size() - 1));
		
		print("Type 'restart' to play again, or type 'quit' to quit.");
		String input = "";
		while(true) {
			input = s.next();
			if(input.equalsIgnoreCase("restart")) {
				deck.clear();
				players.clear();
				numberOfPlayers = 0;
				gameOver = false;
				break;
			} else if(input.equalsIgnoreCase("quit")) {
				System.exit(0);
			}
			
		}
		startGame();
		
	}
	
	public void print(Object o) {
		System.out.println(o);
	}
	
}

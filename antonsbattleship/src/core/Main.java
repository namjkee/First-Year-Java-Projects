package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import util.Ship;
import ajur4521.Berlin;
import ajur4521.BloodHound;
import ajur4521.Circumspectre;
import ajur4521.Coprolite;
import ajur4521.Mnemosyne;
import ajur4521.Naive;
import ajur4521.Naive2;
import ajur4521.Outlander;
import ajur4521.Parsimonious;
import ajur4521.Predictable;
import ajur4521.Spacious;
import ajur4521.Stromatolite;
import ajur4521.StromatoliteA;
import ajur4521.StromatoliteB;
import ajur4521.StromatoliteC;
import ajur4521.Synaesthetic;
import ajur4521.Synthetic;
import ajur4521.Voltron;

/**
 * A Main class to run the game from.
 * 
 * If runTournament is false, it will run duelIterations games between two bots.
 * If runTournament is true, it will run numIterations games between each pair of bots.
 * 
 * @author Anton Jurisevic
 * @sid 311180051
 * @date 2014.6.7
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		int numIterations = 100;
		int duelIterations = 10000;
		// If debugging, probably don't want to run more than one game.
		if (BattleShips.DEBUG) {
			numIterations = 1;
			duelIterations = 1;
		}
		
		//Standard Battleship settings.
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship('c', 5));
		ships.add(new Ship('b', 4));
		ships.add(new Ship('f', 3));
		ships.add(new Ship('l', 3));
		ships.add(new Ship('s', 2));
		
		GameConfiguration config = new GameConfiguration(10, 10, ships);
		config = null;
		ArrayList<Player> players = new ArrayList<Player>();
		
		//Players to play in the tournament.
		players.add(new Coprolite());
		players.add(new Voltron());
		players.add(new Circumspectre());
		players.add(new Outlander());
		players.add(new BloodHound());
		players.add(new Berlin());
		players.add(new Naive());
		players.add(new Naive2());
		players.add(new Parsimonious());
		players.add(new Spacious());
		players.add(new Stromatolite());
		players.add(new StromatoliteA());
		players.add(new StromatoliteB());
		players.add(new StromatoliteC());
		players.add(new Synthetic());
		players.add(new Synaesthetic());
		players.add(new Mnemosyne());
		players.add(new Predictable());
		
		//Each player has an entry in the hash table for its number of wins.
		Hashtable<String, Integer> wins = new Hashtable<String, Integer>();
		
		for (Player p : players) {
			wins.put(p.getClass().getSimpleName(), 0);
		}
		wins.put("Draw", 0);
		
		//Whether to run the tournament or not.
		boolean runTournament = true;
		
		//Players who will compete in the duel if runTournament is false.
		Player player1s = new StromatoliteC();
		Player player2s = new Mnemosyne();
		
		// The tournament
		if (runTournament) {
			ArrayList<Player> done = new ArrayList<Player>();
			
			// For each player in the player list, play it numIterations times
			//   against every other player in the same list, unless they have
			//   played each other before, or are the same player.
			for (Player player1 : players) {
				done.add(player1);
				for (Player player2 : players) {
					if (player1 != player2 && !done.contains(player2)) {
						BattleShips battleships = new BattleShips(config, player1, player2);
						
						System.out.println("Now playing " + player1.getClass().getSimpleName() + " against " + player2.getClass().getSimpleName());
						
						Player winner;
						
						for (int i = 0; i < numIterations; i++) {
							
							winner = battleships.run();
							String winnerName;
							
							if (winner != null) {
								winnerName = winner.getClass().getSimpleName();
							}
							else {
								winnerName = "Draw";
							}
							
							int temp = (int)wins.get(winnerName);
							temp++;
							wins.put(winnerName, temp);						
						}					
					}
				}
			}	
		}
		// The duel
		else {
			BattleShips battleships = new BattleShips(config, player1s, player2s);
			
			System.out.println("Now playing " + player1s.getClass().getSimpleName() + " against " + player2s.getClass().getSimpleName());
			
			Player winner;
			
			Date date = new Date();
			
			long startTime = date.getTime();
			
			for (int i = 0; i < duelIterations; i++) {
				if (i%1000 == 0) System.out.println("game " + i);
				
				winner = battleships.run();
				String winnerName;
				
				if (winner != null) {
					winnerName = winner.getClass().getSimpleName();
				}
				else {
					winnerName = "Draw";
				}
				
				int temp = (int)wins.get(winnerName);
				temp++;
				wins.put(winnerName, temp);
			}
			
			date = new Date();
			long elapsedTime = date.getTime() - startTime;
			System.out.println("Run complete in " + elapsedTime/(double)1000 + " seconds");
			System.out.println("Average time per game: " + elapsedTime/(double)duelIterations + " ms");
		}
		
		// Report the results.
		System.out.println("\nWins: ");
		Enumeration<String> winCount = wins.keys();
		
		while (winCount.hasMoreElements()) {
			String currElem = winCount.nextElement();
			if ((int)wins.get(currElem) != 0 || runTournament) {
				System.out.println("\t" + currElem + ": " + wins.get(currElem));				
			}
		}

	}

}

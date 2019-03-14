package connect_four_gui;

import java.awt.Color;

public class Player implements Comparable<Player>{
	private String playerName;
	private String playerSymbol;
	private int numGames;
	private int numWins;
	private int numLosses;
	private Color wow;
	
	public Player(){
		playerName = "PlayDoe";
		playerSymbol = " * ";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
	}
	public Player(String name, String symbol, Color x){
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		playerName = name;
		playerSymbol = symbol;
		wow = x;
	}
	public Color getWow() {
		return wow;
	}
	public void setWow(Color wow) {
		this.wow = wow;
	}
	
	public void addNumWins(){
		numWins++;
		numGames++;
	}
	public void addNumLosses(){
		numLosses++;
		numGames++;
	}
	public void addDraw(){
		numGames++;
	}
	public int getNumWins(){
		return numWins;
	}
	public int getNumLosses(){
		return numLosses;
	}
	public int getNumGames(){
		return numGames;
	}
	public String getSymbol(){
		return playerSymbol;
	}
	public String getName(){
		return playerName;
	}
	
	public boolean equals(Object o){
		if(o instanceof Player){
			Player otherPlayer = (Player)o;
			if(this.playerName.equalsIgnoreCase(otherPlayer.playerName)){
				if(this.playerSymbol.equalsIgnoreCase(otherPlayer.playerSymbol)){
					if(this.numGames == otherPlayer.numGames){
						if(this.numLosses == otherPlayer.numLosses){
							if(this.numWins == otherPlayer.numWins){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public String toString(){
		String s = "Player [ name= " + playerName + ", " + 
				" symbol= " + playerSymbol + ", " +
				" wins= " + numWins + ", " + 
				" losses= " + numLosses + ", " + 
				" total games= " + numGames + " ]";
		return s;
	}
	
	@Override
	public int compareTo(Player otherP){
		if(this.numWins > otherP.numWins){
			return 1;
		}
		else if(this.numWins < otherP.numWins){
			return -1;
		}
		else{ 
			return 0;
		}
		
	}
	
	
}
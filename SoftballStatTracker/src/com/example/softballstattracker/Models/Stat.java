package com.example.softballstattracker.Models;

public class Stat {
	private long statId;
	private long playerId;
	private String playerName;
	private int atBats;
	private int hits;
	private float average;
	private int singles;
	private int doubles;
	private int triples;
	private int homeRuns;
	private int rbis;
	private int putOuts;
	private int beersDrank;
	private long gameId;
	private String dateCreated;
	
	public long getStatId() {
		return statId;
	}
	
	public long getPlayerId() {
	  return playerId;
	}
	
	public String getPlayerName() {
		  return playerName;
		}
	
	public int getAtBats() {
		  return atBats;
		}
	
	public int getHits() {
		  return hits;
		}
	
	public int getSingles() {
		  return singles;
		}
	
	public float getAverage(int hits, int atBats) {
		  return hits / (float)atBats;
		}
	
	public int getDoubles() {
		  return doubles;
		}
	
	public int getTriples() {
		  return triples;
		}
	
	public int getHomeRuns() {
		  return homeRuns;
		}
	
	public int getRunsBattedIn() {
		  return rbis;
		}
	
	public int getPutOuts() {
		  return putOuts;
		}
	
	public int getBeersDrank() {
		  return beersDrank;
		}
	
	public long getGameId() {
		  return gameId;
		}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}
	
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public void setAverage(int atBats, int hits) {
		this.average = (hits / (float)atBats);
	}
	
	public void setSingles(int singles) {
		this.singles = singles;
	}
	
	public void setDoubles(int doubles) {
		this.doubles = doubles;
	}
	
	public void setTriples(int triples) {
		this.triples = triples;
	}
	
	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}
	
	public void setRbis(int rbis) {
		this.rbis = rbis;
	}
	
	public void setPutOuts(int putOuts) {
		this.putOuts = putOuts;
	}
	
	public void setBeerDrank(int beersDrank) {
		this.beersDrank = beersDrank;
	}
	
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public void setGameId(long currentGameId) {
		this.gameId = currentGameId;
	}
}

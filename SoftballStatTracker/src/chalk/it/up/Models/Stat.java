package chalk.it.up.Models;

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
	private int walks;
	private int runs;
	private int rbis;
	private int putOuts;
	private int sacFlys;
	private String opponent;
	private int beersDrank;
	private long gameId;
	private String playerImage;
	private String dateCreated;
	private int errors;
	
	public long getStatId() {
		return statId;
	}
	
	public long getPlayerId() {
	  return playerId;
	}
	
	public String getPlayerName() {
		  return playerName;
		}
	
	public String getPlayerImage() {
		  return playerName;
		}
	
	public int getAtBats() {
		  return atBats;
		}
	
	public float getOnBasePercentage(int bases, int plateAppearances)
	{
		return bases / (float)plateAppearances;
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

	public int getWalks() {
		  return walks;
		}
	
	public int getRuns() {
		  return runs;
		}
	
	public int getRunsBattedIn() {
		  return rbis;
		}
	
	public int getPutOuts() {
		  return putOuts;
		}
	
	public int getSacFlys() {
		  return sacFlys;
		}
	
	public int getErrors() {
		  return errors;
		}
	
	public int getBeersDrank() {
		  return beersDrank;
		}
	
	public long getGameId() {
		  return gameId;
		}
	
	public String getOpponent() {
		return opponent;
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
	
	public void setHits(int singles, int doubles, int triples, int homeRuns) {
		this.hits = (singles + doubles + triples + homeRuns);
	}
	
	public void setAverage(int atBats, int hits) {
		this.average = (hits / (float)atBats);
	}
	
	public void setOpponent(String opponent)
	{
		this.opponent = opponent;
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
	
	public void setPlayerImage(String image) {
		this.playerImage = image;
	}
	
	public void setWalks(int walks) {
		this.walks = walks;
	}
	
	public void setRuns(int runs) {
		this.runs = runs;
	}
	
	public void setPutOuts(int putOuts) {
		this.putOuts = putOuts;
	}
	
	public void setSacFlys(int sacFlys) {
		this.sacFlys = sacFlys;
	}
	
	public void setErrors(int errors) {
		this.errors = errors;
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
package com.example.softballstattracker.Models;

public class Game {
	
	private long gameId; 
	private String name;
	private String dateCreated;
	
	 public long getId() {
		return gameId;
		  }

	  public void setId(long GameId) {
	    this.gameId = GameId;
	  }

	  public String getname() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
		  
	  public String getDateCreated() {
		return dateCreated;
	  }
		  
	  public void setDateCreated(String dateCreated) {
		    this.dateCreated = dateCreated;
		  }
		  
	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() 
	  {
	    return name;
	  }

}

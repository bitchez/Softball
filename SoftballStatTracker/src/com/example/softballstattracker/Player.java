package com.example.softballstattracker;

public class Player {
	
	private long playerId; 
	public String name;
	
	 public long getId() {
		    return playerId;
		  }

		  public void setId(long playerId) {
		    this.playerId = playerId;
		  }

		  public String getname() {
		    return name;
		  }

		  public void setName(String name) {
		    this.name = name;
		  }
		  
		  // Will be used by the ArrayAdapter in the ListView
		  @Override
		  public String toString() 
		  {
		    return name;
		  }

}

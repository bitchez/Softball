package com.example.softballstattracker.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Player implements Parcelable {
	
	private static final String TAG = "**player**";
	private long playerId; 
	private String name;
	private String dateCreated;
	public boolean isPlayerChosen; 

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

	//below logic is used for the purpose of serializing object through intent

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.v(TAG, "writeToParcel..." + flags);
		dest.writeLong(playerId);
		dest.writeString(name);
		dest.writeString(dateCreated);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	public static final Parcelable.Creator<Player> CREATOR 
	= new Parcelable.Creator<Player>() {
		
		@Override
		public Player createFromParcel(Parcel source) {
			return new Player(source);
		}

		@Override
		public Player[] newArray(int size) {
		    return new Player[size];
		}
	};
	
	 public Player(Parcel source) {
		Log.v(TAG, "Player(Player source): time to put back the parcel data");
		playerId = source.readInt();
		name = source.readString();
		dateCreated = source.readString();
	}

	public Player() {
		Log.d(TAG, "new player");
	}
}

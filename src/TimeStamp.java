
public abstract class TimeStamp {
	private int userID;
	
	public int getUserID() { return userID; }
	
	public void setUserID( int userID ) {
		this.userID = userID;
	}
	
	static public int compare( TimeStamp a, TimeStamp b){ 
		return -2;
	}
	
	public void addByOneTick() {}
	
	public void syncTime( TimeStamp a) {}
	
}


public abstract class TimeStamp {
	private int userID;
	
	public TimeStamp() {
		userID = -1;
	}
	
	public TimeStamp(int userID) {
		this.userID = userID;
	}
	
	public TimeStamp(int userID, int nodeNumber) {
		this.userID = userID;
	}
	
	
	
	public int getUserID() { return userID; }
	
	public void setUserID( int userID ) {
		this.userID = userID;
	}
	
	 public  int compare( TimeStamp a, TimeStamp b){ 
		return -2;
		
	}
	
	public void addByOneTick() {}
	
	public void syncTime( TimeStamp a) {}
	
}

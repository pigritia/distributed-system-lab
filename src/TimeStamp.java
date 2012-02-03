import java.io.FileNotFoundException;
import java.io.Serializable;


public abstract class TimeStamp implements Serializable{
	private int userID;
	
	public TimeStamp() {
		userID = -1;
	}
	public TimeStamp(TimeStamp t) {
		this.userID = t.userID;
	}
	
	public TimeStamp(int userID) {
		this.userID = userID;
	}
	
	public TimeStamp(int userID, int nodeNumber) {
		this.userID = userID;
	}
	
	public TimeStamp clone() {
		if (this instanceof VectorTimeStamp) {
			return  (VectorTimeStamp)this.clone();
		} else if (this instanceof LogicalTimeStamp) {
			return  (LogicalTimeStamp)this.clone();
		} else {
			return null;
		}
		
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
	
	public static void main(String[] args) {
		LogicalTimeStamp v = new LogicalTimeStamp(1);
		v.addByOneTick();
		v.addByOneTick();
		
		TimeStamp q = v; 
		q.addByOneTick();
		
		TimeStamp qq = q.clone();
		qq.addByOneTick();
		qq.addByOneTick();
		
		q.addByOneTick();
		
		System.out.println(v);
		System.out.println(q);
		System.out.println(qq);
	}

	
}

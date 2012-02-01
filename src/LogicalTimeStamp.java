import java.io.Serializable;


public class LogicalTimeStamp extends TimeStamp implements Serializable {
	private long clock;
	
	public LogicalTimeStamp() {
		super();
	}
	
	public LogicalTimeStamp(int userID) {
		super(userID);
	}
	
	public LogicalTimeStamp(int userID, int nodeNumber) {
		super(userID, nodeNumber);
	}
	
	public long getClock() { return clock; }
	
	public void setClock( long currentTime ) {
		this.clock = currentTime;
	}
	
	public  int compare( TimeStamp a, TimeStamp b) { 
		long aa = ((LogicalTimeStamp)a).getClock();
		long bb = ((LogicalTimeStamp)b).getClock();
		
		return aa > bb ? 1 : ( aa == bb ? 0 : -1);		
	}
	
	public void syncTime( TimeStamp newTimeStamp) {
		long newTime = ((LogicalTimeStamp)newTimeStamp).getClock();
		
		clock = clock > newTime ? clock : newTime;
	}
	
	public void addByOneTick() {
		this.clock++;
	}
	
	public String toString() {
		return ((Long)clock).toString();
	}
}

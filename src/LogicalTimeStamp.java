
public class LogicalTimeStamp extends TimeStamp {
	private long currentTime;
	
	public long getCurrentTime() { return currentTime; }
	
	public void setCurrentTime( long currentTime ) {
		this.currentTime = currentTime;
	}
	
	public static int compare( TimeStamp a, TimeStamp b) { 
		long aa = ((LogicalTimeStamp)a).getCurrentTime();
		long bb = ((LogicalTimeStamp)b).getCurrentTime();
		
		return aa > bb ? 1 : ( aa == bb ? 0 : -1);		
	}
	
	public void syncTime( TimeStamp newTimeStamp) {
		long newTime = ((LogicalTimeStamp)newTimeStamp).getCurrentTime();
		
		currentTime = currentTime > newTime ? currentTime : newTime;
	}
	
	public void addByOneTick() {
		this.currentTime++;
	}
}

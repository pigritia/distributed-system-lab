import java.io.Serializable;


public class TimeStampMessage extends Message implements Comparable<TimeStampMessage>,Serializable{
	private TimeStamp timeStamp;
	
	public TimeStampMessage(String src, String dest, String kind, TimeStamp timeStamp, Object data) {
		super(src, dest, kind, data);
		this.timeStamp = timeStamp;
	}
	
	public TimeStamp getTimeStamp() { return timeStamp; }
	
	public void setTimeStamp (TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String toString()	{
		return "From " + src + " to " + dest + " " +kind + " id=" +id+"\r\n" +
				"TimeStamp: " +timeStamp.toString() + "\r\n" +
				"Message: " + message + "\r\n";
	}

	@Override
	public int compareTo(TimeStampMessage o) {
		int compareResult = timeStamp.compare(this.timeStamp,o.timeStamp);
		if(compareResult == 0)	{
			if(this.timeStamp.getUserID() < o.timeStamp.getUserID())
				compareResult = -1;
			
			else 
				compareResult=1;
				
		}
		System.out.println(this.timeStamp + " " + o.timeStamp  + " " +
				timeStamp.getUserID() + " " + o.timeStamp.getUserID()+ "\t" +compareResult);

		return compareResult;
	}
   
}

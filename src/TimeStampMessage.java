
public class TimeStampMessage extends Message{
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
				timeStamp.toString() + "\r\n" +
				message;
	}
   
}

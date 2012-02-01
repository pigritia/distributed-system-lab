
public class Event implements Comparable<Event>{
	
	private String eventString;
	private TimeStamp timeStamp;
	
	public String toString()	{
		return timeStamp + "\t" +  eventString;
	}

	@Override
	public int compareTo(Event arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

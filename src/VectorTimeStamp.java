
public class VectorTimeStamp extends TimeStamp {
	private long [] clock;
	
	public VectorTimeStamp() {
		super();
	}
	
	public VectorTimeStamp(int userID) {
		super(userID);
	}

	public long [] getClock() { return this.clock; }
	
	public void setClock( long [] vector) {
		this.clock = vector;
	}
	
	public static int compare( TimeStamp a, TimeStamp b) { 
		long [] aa = ((VectorTimeStamp)a).getClock();
		long [] bb = ((VectorTimeStamp)b).getClock();
		
		int countGreater = 0, countLess = 0;
		boolean flagGreater = false, flageLess = false;
		
		int length = aa.length;
		
		if (length == bb.length) {
			for (int i = 0; i < length; i++) {
				if (aa[i] == bb[i]) {
					countGreater++;
					countLess++;
				} else if (aa[i] > bb[i]) {
					countGreater++;
					flagGreater = true;
				} else {
					 countLess++;
					 flageLess = true;
				}
			}
			
			return (countGreater == length && flagGreater) ? 1 : 
						((countLess == length&&flageLess)? -1 : 0 );
			
		} else {
			System.err.println("the time vector mismatch");
			return -2;
		}
	}
	
	public void syncTime( TimeStamp newTimeStamp) {
		long [] newTime = ((VectorTimeStamp)newTimeStamp).getClock();
		
		if (newTime.length == clock.length) {
			for (int i = 0; i < newTime.length; i++) {
				clock[i] = Math.max(clock[i], newTime[i]);
			}
		} else {
			System.err.println("vector length mismatch");
		}
		
	}
	
	public void addByOneTick() {
		clock[getUserID()]++;
	}
	
}

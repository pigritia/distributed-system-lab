import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class VectorTimeStamp extends TimeStamp {
	private List<Long> currentVector;

	public List<Long> getCurrentVector() { return this.currentVector; }
	
	public void setCurrentVector( List<Long> vector) {
		this.currentVector = vector;
	}
	
	public static int compare( TimeStamp a, TimeStamp b) { 
		List<Long> aa = ((VectorTimeStamp)a).getCurrentVector();
		List<Long> bb = ((VectorTimeStamp)b).getCurrentVector();
		
		Iterator<Long> aIter = aa.iterator();
		Iterator<Long> bIter = bb.iterator();

		int countGreater = 0, countLess = 0;
		boolean flagGreater = false, flageLess = false;
		if (aa.size() == bb.size()) {
			int length = aa.size();
			for (int i = 0; i < length; i++) {
				long ai = aIter.next();
				long bi = bIter.next();
				
				if (ai == bi) {
					countGreater++;
					countLess++;
				} else if (ai > bi) {
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
			System.out.println("the time vector mismatch");
			return -2;
		}
	}
	
	public void syncTime( TimeStamp newTimeStamp) {
		List<Long> newTime = ((VectorTimeStamp)newTimeStamp).getCurrentVector();
		Iterator<Long> newTimeIter = newTime.iterator();
		Iterator<Long> oldTimeIter = currentVector.iterator();
		
		if (newTime.size() == currentVector.size()) {
			for (int i = 0; i < newTime.size(); i++) {
				currentVector.set(i, Math.max(newTimeIter.next(), oldTimeIter.next()));
			}
		} else {
			System.err.println("vector length mismatch");
		}
		
	}
	
	public void addByOneTick() {
		long nodeTime = currentVector.get(getUserID());
		currentVector.set(this.getUserID(), ++nodeTime);
	}
	
}

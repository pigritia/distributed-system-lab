public class TimeStampFractory {

	public static TimeStamp getTimeStamp(String type) {
		if (type.equals("logical")) {
			return new LogicalTimeStamp();
		} else if (type.equals("vector")) {
			return new VectorTimeStamp();
		} else {
			System.err.println("clock type should be either logical or vector");
			return null;
		}
	}
	
	public static TimeStamp getTimeStamp(String type, int userID) {
		if (type.equals("logical")) {
			return new LogicalTimeStamp(userID);
		} else if (type.equals("vector")) {
			return new VectorTimeStamp(userID);
		} else {
			System.err.println("clock type should be either logical or vector");
			return null;
		}
	}
}


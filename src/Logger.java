import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.*;
import java.util.*;


public class Logger implements Observer {
	private MessagePasser messagePasser;

	private static String logDir = "log/";
	private List<Message> messages;
	public Logger(String configurationFilename)	{
		messagePasser = new MessagePasser(configurationFilename,"logger");
		messages = new ArrayList<Message>();
	}
	private void sortMessages() {
		
	}
	private void logEvents()	throws IOException{
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		String LogFileName = logDir + formatter.format(new Date()) + "_log.txt";
		FileWriter fw = new FileWriter(LogFileName);
		BufferedWriter bw = new BufferedWriter(fw);
		for(Message message:messages)	{
			bw.write(message.toString());
		}
		bw.close();
		fw.close();
	}
	
	@Override 
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Message message = (Message) arg1;
	}
	
	

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Logger(args[0]);
		
	}



}

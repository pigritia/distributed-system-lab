import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.*;
import java.util.*;


public class Logger implements Observer {
	private MessagePasser messagePasser;

	private static String logDir = "log/";
	private List<TimeStampMessage> messages;
	public Logger(String configurationFilename)	{
		messagePasser = new MessagePasser(configurationFilename,"logger");
		messagePasser.addObserver(this);
		try {
			messagePasser.listen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messages = new ArrayList<TimeStampMessage>();
	}
	private void sortMessages() {
		Collections.sort(messages);
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
	public void run() throws IOException	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		
		while(true)	{
			String command = br.readLine();
			if(command.equals("#log"))	{
				sortMessages();
				logEvents();
			}
		}
	}
	
	@Override 
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("updated");
		TimeStampMessage message = (TimeStampMessage) arg1;
		messages.add(message);
	}
	
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Logger logger = new Logger(args[0]);
		logger.run();
	}



}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class MessagePasser extends Observable{
	private String name;
	private int port = 8002;
	private LinkedList<Message> receivedMessages;
	private LinkedList<Message> toSendMessages;
	private  Configuration config;
	private int messageId = 0;
	private  String configurationFilename;
	private TimeStamp timeStamp;
	private int userId;
	public MessagePasser(String configurationFilename, String localName) {
		this.configurationFilename = configurationFilename;
		try {
			config = YalmParse.parse(configurationFilename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		port = config.getNode(localName).getPort();
		name = localName;
		receivedMessages = new LinkedList<Message>();
		toSendMessages = new LinkedList<Message>();

		userId = 0;
		for(Node node:config.getNodeList())	{
			if(node.getName().equals(name))	{
				break;
			}
			userId++;
		}
		timeStamp = TimeStampFactory.getTimeStamp(config.getClockType(),userId,config.getNodeList().size());
		System.out.println(timeStamp + " " + timeStamp.getUserID());
		//send register message
		
		//block until receive feedback
	}
	
	public String getName() { return name; }
	
	public void run() throws IOException	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		
		while(true)	{
			String command = br.readLine();
			if(command.equals("#send"))	{
				String toAlias = br.readLine();
				String type = br.readLine();
				String content = br.readLine();
				Message message = new TimeStampMessage(name,toAlias,type,timeStamp,content);
				send(message);
			}
			else if(command.equals("#receive"))	{
				receive();
			}
			else if(command.equals("#update"))	{
				try {
					config = YalmParse.parse(configurationFilename);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
			else if (command.equals("#event")) {
				String event = br.readLine();
				String send = br.readLine();
				timeStamp.addByOneTick();
				System.out.println(timeStamp + " " + event + " occured.");
				if(send.equals("yes"))	{
					Message message = new TimeStampMessage(name,"logger","event",timeStamp,event);
					send(message);
				}
			}
			else if (command.equals("#process")) {
				
			}
		}
	}
	void send(Message message) {
		message.setId(++messageId);
		Rule matchedRule = null;
		timeStamp.addByOneTick();
		for(Rule rule:config.getSendRules())	{
			if(rule.isMatch(message))	{
				//System.out.println(rule.toString() + " matched");
				matchedRule = rule;
			}
		}
		if(matchedRule != null)	{
			if(matchedRule.getAction().equals("drop"))	{
				System.out.println("Sending " + message.toString() + " is droped");
				return;
			}
			else if(matchedRule.getAction().equals("delay"))	{
				System.out.println("Sending " + message.toString() + " is delayed");
				toSendMessages.add(message);
				return;
			}
			else if(matchedRule.getAction().equals("duplicate"))	{
				System.out.println("Sending " + message.toString() + " is duplicated");
				toSendMessages.push(message);
				toSendMessages.push(message);
			}
				
		}
		else {				//not rule matched
			toSendMessages.push(message);
		}
		
		while(!toSendMessages.isEmpty())	{
			Message toSendMessage = toSendMessages.peek();
			Socket socket = null;
			
			Node node = config.getNode(toSendMessage.dest);
			try {
				socket = new Socket(node.getIp(),node.getPort());
				ObjectOutputStream  oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(toSendMessage);				
				socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Sending message failed.");
				return;
			}
			toSendMessages.remove();
		}
		
		
	}
	synchronized void  receive()	{// may block
		if(receivedMessages.isEmpty())	{
			System.out.println("No message");
			return;
		}			
		TimeStampMessage message = (TimeStampMessage) receivedMessages.pollFirst();
		timeStamp.syncTime(message.getTimeStamp());
		timeStamp.addByOneTick();
		System.out.println(message);
	}
	synchronized void addMessage(Message message)
	{
		//System.out.println("Get " + message.toString());
		Rule matchedRule = null;
		for(Rule rule:config.getReceiveRules())	{
			if(rule.isMatch(message))
				matchedRule = rule;
		}
		if(matchedRule != null)	{
			if(matchedRule.getAction().equals("drop"))	{
				System.out.println("Message droped.");
				return;
			}
			if(matchedRule.getAction().equals("duplicate"))	{
				System.out.println("Message duplicated.");
				receivedMessages.add(message);
				receivedMessages.add(message);				
			}
		}
		else{
			receivedMessages.add(message);
		}
		setChanged();
		notifyObservers(message);
	}
	void listen() throws Exception {
		System.out.println("Listening to " + port);
		Receiver reciver = new Receiver(port,this);
		reciver.start();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(args[1]);
		MessagePasser parser = new MessagePasser(args[0], args[1]);
		parser.listen();
		parser.run();
		
	}
}
class Receiver extends Thread	{
	private MessagePasser messageParser;
	Socket socket;
	static int port;
	Receiver(int port,MessagePasser messageParser)	{
		this.messageParser = messageParser;
		Receiver.port = port;
	}
	Receiver(Socket socket,MessagePasser messageParser)	{
		this.messageParser = messageParser;
		this.socket = socket;
	}
	public void run()	{
		if(socket == null)	{
			ServerSocket server = null;
			try {
				server = new ServerSocket(port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true ){
				Socket client = null;
				try {
					client = server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Receiver receiver = new Receiver(client,messageParser);
				receiver.run();		
				
			}
		}
		else	{
			BufferedReader in = null;
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				/*
				in = new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Message message = (Message) ois.readObject();
				messageParser.addMessage(message);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}

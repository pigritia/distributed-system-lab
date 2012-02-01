import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Configuration {
	private List<Node> nodeList;
	
	private List<Rule> sendRules;
	
	private List<Rule> receiveRules;
	
	private String clockType;

	Configuration(){
		this.nodeList = new ArrayList<Node>();
		this.sendRules = new ArrayList<Rule>();
		this.receiveRules = new ArrayList<Rule>();
	}
	
	public Node getNode(String name)	{
		for(Node node:nodeList)	{
			if(node.getName().equals(name))
				return node;
		}
		return null;
	}
	
	public List<Node> getNodeList() {return nodeList;}
	
	public List<Rule> getSendRules() {return sendRules;}
	
	public List<Rule> getReceiveRules() {return receiveRules;}
	
	public String getClockType() {return clockType;}
	
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	public void setSendRules(List<Rule> sendRules) {
		this.sendRules = sendRules;
	}
	
	public void setReceiveRules(List<Rule> receiveRules){
		this.receiveRules = receiveRules;
	}
	
	public void setClockType(String clockType) {
		this.clockType = clockType;
	}
	
	public static void main(String[] args) {
		String file = "conf/lab0.conf";
		Configuration config = new Configuration();
		try {
			config = YalmParse.parse(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.print(config.getNode("logger").getPort());
		System.out.print(config.getClockType());
	}
	
}
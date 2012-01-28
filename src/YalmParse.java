import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;



public class YalmParse {
	public static void main(String [] args) throws FileNotFoundException {
		String confFile = "conf/lab0.conf";
		
		Configuration conf = YalmParse.parse(confFile);
		for(Node node:conf.getNodeList())	{
			System.out.println(node.getName() + " " + node.getIp() + " " + node.getPort());
		}
		for(Rule rule:conf.getSendRules())	{
			System.out.println("Rule:");
			if(rule.getAction()!= null)
				System.out.println("action:"+rule.getAction());
			if(rule.getSrc()!= null)
				System.out.println("src:"+rule.getSrc());
			if(rule.getDest()!= null)
				System.out.println("dest:"+rule.getDest());
			if(rule.getKind()!= null)
				System.out.println("id:"+rule.getKind());
			if(rule.getId()!= null)
				System.out.println("id:"+rule.getId());
			if(rule.getNth()!= null)
				System.out.println("nth:"+rule.getNth());
		}
		System.out.println(conf.getNodeList().get(0));
		System.out.println(conf.getReceiveRules().get(0).getAction());
		System.out.println(conf.getSendRules().get(0).getAction());
		
	}
	
	public static Configuration parse(String file) throws FileNotFoundException {
		InputStream input = new FileInputStream(new File(file));
		
		Yaml yaml = new Yaml();
		return (Configuration)yaml.loadAs(input, Configuration.class);
		
	}

}
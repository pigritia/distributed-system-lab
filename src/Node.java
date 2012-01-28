
public class Node {
    private String name;
	private String ip;
	private Integer port;
	
	public String getName(){return name;}
	
	public String getIp() {return ip;}
	
	public Integer getPort() {return port;}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
}

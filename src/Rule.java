
public class Rule {
	private String action;
	private String src;
	private String dest;
	private String kind;
	private Integer id;
	private Integer nth;
	private int matchedCount = 0;
	public boolean isMatch(Message message){
		if(src != null && !src.equals(message.src))
			return false;
		if(dest != null && !dest.equals(message.dest))
			return false;
		if(kind != null && !kind.equals(message.kind))
			return false;
		if(id != null && id.intValue() != message.id)
			return false;
		if(nth != null)	{
			if(++matchedCount == nth.intValue())	{
				matchedCount = 0;
				return true;
			}
			else
				return false;
		}
		return true;
	}
	
	public String getAction() {return action;}
	
	public String getSrc() {return src;}
	
	public String getDest() {return dest;}
	
	public String getKind() {return kind;}
	
	public Integer getId() {return id;}
	
	public Integer getNth() {return nth;}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNth(Integer nth) {
		this.nth = nth;
	}
	   
}

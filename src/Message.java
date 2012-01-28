import java.io.Serializable;

public class Message implements Serializable {
	int id;
	String message;

	String src,dest,kind;
	public Message(String src, String dest, String kind, Object data) {

		message = data.toString();
		this.src = src;
		this.dest = dest;
		this.kind = kind;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString()	{
		return "From " + src + " to " + dest + " " +kind + " id=" +id+"\r\n" + message;
	}
}

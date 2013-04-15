package td2;

public class Definition {
	protected String word;
	protected String def;
	
	public Definition(String w, String d){
		this.word = w;
		this.def = d;
	}
	
	public String toString(){
		return word;
	}
	
	
	public String getDef(){
		return def;
	}
}

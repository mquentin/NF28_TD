public class Contact {
	protected String nom, mail, icon;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Contact(){
		nom = "unknown";
		mail = "unknown";
		icon = "unknown";
	}
	public Contact(String n, String m, String i){
		nom = n;
		mail = m;
		icon = i;
	}
	public String toString(){
		return nom;
		//return "<contact>\n<nom>"+ nom + "</nom>\n<mail>" + mail +"</mail>\n<icone>"+ icon+ "</icone>\n</contact>";
	}

	public String infoToString(){
		return "<contact>\n<nom>"+ nom + "</nom>\n<mail>" + mail +"</mail>\n<icone>"+ icon+ "</icone>\n</contact>";
	}
}

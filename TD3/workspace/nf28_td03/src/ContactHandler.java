import javax.swing.tree.DefaultMutableTreeNode;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ContactHandler extends DefaultHandler {
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode category;
	
	private Contact contact;
	//flags indiquant la position du curseur
	private boolean inCategory;
	//buffer permettant de récupérer les données
	private StringBuffer buffer;
	
	public ContactHandler()
	{
		super();
		buffer = new StringBuffer();
	}
	//----------Fonctions de parsing SAX
	//détection de début de balise
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
			throws SAXException{
				System.out.println("Début de balise: "+localName);
				if(localName.equals("contacts")){
					System.out.println("debut du parsing");
					root = new DefaultMutableTreeNode(localName);
					
				}else if(localName.equals("contact")){
					contact = new Contact();
					if(!inCategory){
						System.out.println("Parse un contact à la racine");
						root.add(new DefaultMutableTreeNode(contact));
					}else{
						System.out.println("Parse un contact dans "+ localName);
						category.add(new DefaultMutableTreeNode(contact));
					}
				}else if(!localName.equals("nom") && !localName.equals("mail") && !localName.equals("icone")){
					System.out.println("Creer category "+localName);
					category = new DefaultMutableTreeNode(localName);
					root.add(category);
					inCategory = true;
				}
	}
	
	//détection fin de balise
	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		System.out.println("Fin de balise: "+qName);
		if(localName.equals("nom")){
			contact.setNom(buffer.toString());
		}else if(localName.equals("mail")){		
			contact.setMail(buffer.toString());
		}else if(localName.equals("icone")){
			contact.setIcon(buffer.toString());
		}else if(!localName.equals("contact")){
			System.out.println("Ajoute category "+qName);
			inCategory = false;
		}else{
			System.out.println(contact.infoToString());
		}
		
		buffer.delete(0, buffer.length());
	}
	
	//remplit le buffer lors d'une detection de caractere
	public void characters(char[] ch,int start, int length)
			throws SAXException{
		String lecture = new String(ch,start,length);
		//System.out.println(buffer);
		if(buffer != null) buffer.append(lecture.trim());       
	}

	//début du parsing
	public void startDocument() throws SAXException {
		System.out.println("Début du parsing");
	}
	
	
	//fin du parsing
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
	}
	//----------Fin des fonctions de parsing SAX
	
	public ContactTreeModel getContactTreeModel(){
		return new ContactTreeModel(root);
	}

}

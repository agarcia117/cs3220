package models;

public class AlbumEntry {

	static int count = 0;
	
	int id;
	String name;
	String message;
	
	public AlbumEntry(String name, String message) {
		super();
		this.name = name;
		this.message = message;
		this.id = count++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}
	
	
	
}
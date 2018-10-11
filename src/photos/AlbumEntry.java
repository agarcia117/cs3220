package photos;

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

	public String getAlbumName() {
		return name;
	}

	public void setAlbumName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return message;
	}

	public void setDescription(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}
	
	
	
}
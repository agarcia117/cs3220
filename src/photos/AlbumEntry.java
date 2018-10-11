package photos;

public class AlbumEntry {

	static int count = 0;
	
	int id;
	String name;
	String description;
	
	public AlbumEntry(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.id = count++;
	}

	public String getAlbumName() {
		return name;
	}

	public void setAlbumName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	
	
}
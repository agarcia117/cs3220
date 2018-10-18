package photos;

public class AlbumEntry {

	static int count = 0;
	
	int id;
	String albumName;
	String description;
	
	public AlbumEntry(String name, String description) {
		super();
		this.albumName = name;
		this.description = description;
		this.id = count++;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String name) {
		this.albumName = name;
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
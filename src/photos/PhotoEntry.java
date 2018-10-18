package photos;

public class PhotoEntry {

	static int count = 1;
	
	int id;
	int albumId;
	String fileDir;
	String fileName;
	
	public PhotoEntry() {
		
	}
	public PhotoEntry(int albumId, String fileName, String fileDir) {
		super();
		this.id = count++;
		this.albumId = albumId;
		this.fileDir = fileDir;
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
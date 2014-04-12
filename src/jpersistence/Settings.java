package jpersistence;

public class Settings {
	
	public static StorageBackend getStorageBackend() {
		
		return new CSVBackend();
	}
}

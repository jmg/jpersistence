package jpersistence;

import java.util.ArrayList;

public interface StorageBackend {

	void save(String filename, ArrayList<String> properties, ArrayList<String> values);
	String[] getRows(String filename);
	String[] getRowValues(String row);
}

package jpersistence;

import java.util.ArrayList;

public class Entity {
	
	private StorageBackend storageBackend = Settings.getStorageBackend();
	
	private Integer id = Integer.valueOf(1);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Entity save() {
		
		ArrayList<String> properties = Reflector.getProperties(this);
		ArrayList<String> values = Reflector.getValues(this);
		
		properties.add(0, "id");
		values.add(0, this.getId().toString());
					
		storageBackend.save(Reflector.getFileName(this), properties, values);		
		
		return this;
	}
	
}

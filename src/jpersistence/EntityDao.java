package jpersistence;

import java.util.ArrayList;


public class EntityDao<T> {
	
	private StorageBackend storageBackend = Settings.getStorageBackend();

	public T find(int id) {
		
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public EntityList<T> findAll(Class<T> clazz) {
	
		T obj = (T) Reflector.newObject(clazz);
		
		String rows[] = storageBackend.getRows(Reflector.getFileName(obj));
		EntityList<T> objects = new EntityList<T>();
		ArrayList<String> properties = Reflector.getPropertiesFromHeader(rows[0]);
				
		for (int i = 1; i < rows.length; i++) {
			
			obj = (T) Reflector.newObject(clazz);
			Reflector.setAttrs((Entity) obj, properties, rows[i]);
			
			objects.add(obj);
		}
				
		return objects;
	}
}

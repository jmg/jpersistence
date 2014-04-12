package jpersistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Reflector {
	
	public static String getFileName(Object object) {
		
		return object.getClass().getName() + ".json";
	}
	
	public static HashMap<String, String> getFields(Object object) {
		
		HashMap<String, String> properties = new HashMap<String, String>();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
		    field.setAccessible(true);
		    Object value = null;
			try {
				value = field.get(object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null) {
				properties.put(field.getName(), value.toString());
			} else {
				properties.put(field.getName(), "");
			}		    
		}				
					
		return properties;
	}
	
	public static ArrayList<String> getProperties(Object object) {
		
		ArrayList<String> properties = new ArrayList<String>();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
		    field.setAccessible(true);
		    properties.add(field.getName());
		}
		
		return properties;
	}
	
	public static ArrayList<String> getValues(Object object) {
		
		ArrayList<String> values = new ArrayList<String>();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
		    field.setAccessible(true);
		    	
		    Object value = null;
			try {
				value = field.get(object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		    
		    values.add(value.toString());
		}
		
		return values;
	}
	
	public static Object newObject(Class<?> clazz) {
		
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) { 
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static ArrayList<String> getPropertiesFromHeader(String row) {
		
		ArrayList<String> properties = new ArrayList<String>();
		String[] props = Settings.getStorageBackend().getRowValues(row);
		for (int i = 0; i < props.length; i++) {
			properties.add(props[i]);					
		}
		
		return properties;
	}
	
	public static void setAttrs(Entity obj, ArrayList<String> properties, String row) {		
										
		String[] vals = Settings.getStorageBackend().getRowValues(row);		
					
		String id = vals[0];
		obj.setId(Integer.parseInt(id));
		
		for (int i = 1; i < vals.length; i++) {
			
			Field field;
			try {
				field = obj.getClass().getDeclaredField(properties.get(i));				
				field.setAccessible(true);
				
				String value = vals[i];
				Object typedValue = value;
										
				if (field.getType().getName().equals("java.lang.Integer") ) {
					typedValue = Integer.parseInt(value);
				}
				
				field.set(obj, typedValue);
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}				
		}
				
	}
}

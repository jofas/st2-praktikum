package ms3restspringdemo.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ms3restspringdemo.entities.Gericht;

public class CustomSetSerializer extends StdSerializer<Set<Gericht>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3747678169478189800L;

	public CustomSetSerializer() {
        this(null);
    }
 
    public CustomSetSerializer(Class<Set<Gericht>> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Set<Gericht> gerichte, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
         
        List<Integer> ids = new ArrayList<>();
        for (Gericht g : gerichte) {
            ids.add(g.getId());
        }
        generator.writeObject(ids);
    }

}

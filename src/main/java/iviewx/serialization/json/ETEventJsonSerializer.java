package iviewx.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import core.chronologic.ETChronologicCollection;
import iviewx.data.ETEvent;

import java.lang.reflect.Type;

public class ETEventJsonSerializer {

    public static String serialize(ETEvent event, ETJsonOutputType outputType) {
        GsonBuilder builder = new GsonBuilder();

        if(outputType == ETJsonOutputType.PRETTY) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();

        return gson.toJson(event);
    }

    public static ETEvent deserialize(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, ETEvent.class);
    }

    public static String serializeCollection(ETChronologicCollection<ETEvent> events, ETJsonOutputType outputType) {
        GsonBuilder builder = new GsonBuilder();

        if(outputType == ETJsonOutputType.PRETTY) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();

        return gson.toJson(events);
    }

    public static ETChronologicCollection<ETEvent> deserializeCollection(String json) {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<ETChronologicCollection<ETEvent>>(){}.getType();

        return gson.fromJson(json, collectionType);
    }

}

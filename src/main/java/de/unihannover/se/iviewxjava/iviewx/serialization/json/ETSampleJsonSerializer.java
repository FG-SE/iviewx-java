package de.unihannover.se.iviewxjava.iviewx.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.unihannover.se.iviewxjava.core.chronologic.ETChronologicCollection;
import de.unihannover.se.iviewxjava.iviewx.data.ETSample;

import java.lang.reflect.Type;

public class ETSampleJsonSerializer {

    public static String serialize(ETSample sample, ETJsonOutputType outputType) {
        GsonBuilder builder = new GsonBuilder();

        if(outputType == ETJsonOutputType.PRETTY) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();

        return gson.toJson(sample);
    }

    public static ETSample deserialize(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, ETSample.class);
    }

    public static String serializeCollection(ETChronologicCollection<ETSample> samples, ETJsonOutputType outputType) {
        GsonBuilder builder = new GsonBuilder();

        if(outputType == ETJsonOutputType.PRETTY) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();

        return gson.toJson(samples);
    }

    public static ETChronologicCollection<ETSample> deserializeCollection(String json) {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<ETChronologicCollection<ETSample>>(){}.getType();

        return gson.fromJson(json, collectionType);
    }
}

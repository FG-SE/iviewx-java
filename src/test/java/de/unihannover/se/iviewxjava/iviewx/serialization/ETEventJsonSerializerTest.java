package de.unihannover.se.iviewxjava.iviewx.serialization;

import de.unihannover.se.iviewxjava.core.chronologic.ETChronologicCollection;
import de.unihannover.se.iviewxjava.iviewx.data.ETEvent;
import de.unihannover.se.iviewxjava.iviewx.data.ETEye;
import de.unihannover.se.iviewxjava.iviewx.serialization.json.ETEventJsonSerializer;
import de.unihannover.se.iviewxjava.iviewx.serialization.json.ETJsonOutputType;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class ETEventJsonSerializerTest {

    @Test
    public void serialize_compact_serializesToCompactFormat() {
        ETEvent event = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);

        String serialized = ETEventJsonSerializer.serialize(event, ETJsonOutputType.COMPACT);

        assertEquals("{\"startTime\":50,\"endTime\":100,\"eye\":\"LEFT\",\"posX\":1.0,\"posY\":1.0}",
                     serialized);
    }

    @Test
    public void serialize_pretty_serializesToPrettyPrintFormat() {
        ETEvent event = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);

        String serialized = ETEventJsonSerializer.serialize(event, ETJsonOutputType.PRETTY);

        assertEquals("{\n"                      +
                     "  \"startTime\": 50,\n"   +
                     "  \"endTime\": 100,\n"    +
                     "  \"eye\": \"LEFT\",\n"   +
                     "  \"posX\": 1.0,\n"       +
                     "  \"posY\": 1.0\n"        +
                     "}",
                     serialized);
    }

    @Test
    public void serialize_deserialize_producesIdenticalEvent() {
        ETEvent event = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);

        String serialized = ETEventJsonSerializer.serialize(event, ETJsonOutputType.COMPACT);
        ETEvent deserialized = ETEventJsonSerializer.deserialize(serialized);

        assertEventEquals(event, deserialized);
    }

    @Test
    public void serializeCollection_compact_serializesToCompactFormat() {
        ETEvent event0 = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);
        ETEvent event1 = new ETEvent(150, 200, ETEye.RIGHT, 2.0, 2.0);

        ETChronologicCollection<ETEvent> events = new ETChronologicCollection<>();
        events.add(event0);
        events.add(event1);

        String serialized = ETEventJsonSerializer.serializeCollection(events, ETJsonOutputType.COMPACT);

        assertEquals("[{\"startTime\":50,\"endTime\":100,\"eye\":\"LEFT\",\"posX\":1.0,\"posY\":1.0}," +
                     "{\"startTime\":150,\"endTime\":200,\"eye\":\"RIGHT\",\"posX\":2.0,\"posY\":2.0}]",
                     serialized);
    }

    @Test
    public void serializeCollection_pretty_serializesToPrettyPrintFormat() {
        ETEvent event0 = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);
        ETEvent event1 = new ETEvent(150, 200, ETEye.RIGHT, 2.0, 2.0);

        ETChronologicCollection<ETEvent> events = new ETChronologicCollection<>();
        events.add(event0);
        events.add(event1);

        String serialized = ETEventJsonSerializer.serializeCollection(events, ETJsonOutputType.PRETTY);

        assertEquals("[\n"                           +
                     "  {\n"                         +
                     "    \"startTime\": 50,\n"      +
                     "    \"endTime\": 100,\n"       +
                     "    \"eye\": \"LEFT\",\n"      +
                     "    \"posX\": 1.0,\n"          +
                     "    \"posY\": 1.0\n"           +
                     "  },\n"                        +
                     "  {\n"                         +
                     "    \"startTime\": 150,\n"     +
                     "    \"endTime\": 200,\n"       +
                     "    \"eye\": \"RIGHT\",\n"     +
                     "    \"posX\": 2.0,\n"          +
                     "    \"posY\": 2.0\n"           +
                     "  }\n"                         +
                     "]",
                     serialized);
    }

    @Test
    public void serializeCollection_deserializeCollection_producesIdenticalCollections() {
        ETEvent event0 = new ETEvent(50, 100, ETEye.LEFT, 1.0, 1.0);
        ETEvent event1 = new ETEvent(150, 200, ETEye.RIGHT, 2.0, 2.0);

        ETChronologicCollection<ETEvent> events = new ETChronologicCollection<>();
        events.add(event0);
        events.add(event1);

        String serialized = ETEventJsonSerializer.serializeCollection(events, ETJsonOutputType.COMPACT);
        ETChronologicCollection<ETEvent> deserialized = ETEventJsonSerializer.deserializeCollection(serialized);

        List<ETEvent> deserializedEvents = new ArrayList<>(deserialized);

        assertEventEquals(event0, deserializedEvents.get(0));
        assertEventEquals(event1, deserializedEvents.get(1));
    }

    private void assertEventEquals(ETEvent expected, ETEvent actual) {
        final double DELTA = 1e-15;

        assertEquals(expected.getStartTime(), actual.getStartTime());
        assertEquals(expected.getEndTime(),   actual.getEndTime());
        assertEquals(expected.getEye(),       actual.getEye());
        assertEquals(expected.getPositionX(), actual.getPositionX(), DELTA);
        assertEquals(expected.getPositionY(), actual.getPositionY(), DELTA);
    }
}
package de.unihannover.se.iviewxjava.iviewx.serialization;

import de.unihannover.se.iviewxjava.core.chronologic.ETChronologicCollection;
import de.unihannover.se.iviewxjava.iviewx.data.ETEyeData;
import de.unihannover.se.iviewxjava.iviewx.data.ETSample;
import de.unihannover.se.iviewxjava.iviewx.serialization.json.ETJsonOutputType;
import de.unihannover.se.iviewxjava.iviewx.serialization.json.ETSampleJsonSerializer;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class ETSampleJsonSerializerTest {

    @Test
    public void serialize_compact_serializesToCompactFormat() {
        ETSample sample = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       100);

        String serialized = ETSampleJsonSerializer.serialize(sample, ETJsonOutputType.COMPACT);

        assertEquals("{\"leftEye\":{\"diameter\":1.0,\"eyePosX\":1.0,\"eyePosY\":1.0,\"eyePosZ\":1.0,\"gazeX\":1.0,\"gazeY\":1.0}," +
                     "\"rightEye\":{\"diameter\":1.0,\"eyePosX\":1.0,\"eyePosY\":1.0,\"eyePosZ\":1.0,\"gazeX\":1.0,\"gazeY\":1.0}," +
                     "\"timestamp\":100}",
                     serialized);
    }

    @Test
    public void serialize_pretty_serializedToPrettyPrintFormat() {
        ETSample sample = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       100);

        String serialized = ETSampleJsonSerializer.serialize(sample, ETJsonOutputType.PRETTY);

        assertEquals("{\n"                      +
                     "  \"leftEye\": {\n"       +
                     "    \"diameter\": 1.0,\n" +
                     "    \"eyePosX\": 1.0,\n"  +
                     "    \"eyePosY\": 1.0,\n"  +
                     "    \"eyePosZ\": 1.0,\n"  +
                     "    \"gazeX\": 1.0,\n"    +
                     "    \"gazeY\": 1.0\n"     +
                     "  },\n"                   +
                     "  \"rightEye\": {\n"      +
                     "    \"diameter\": 1.0,\n" +
                     "    \"eyePosX\": 1.0,\n"  +
                     "    \"eyePosY\": 1.0,\n"  +
                     "    \"eyePosZ\": 1.0,\n"  +
                     "    \"gazeX\": 1.0,\n"    +
                     "    \"gazeY\": 1.0\n"     +
                     "  },\n"                   +
                     "  \"timestamp\": 100\n"   +
                     "}",
                     serialized);
    }

    @Test
    public void serialize_deserialize_producesIdenticalSamples() {
        ETSample sample = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                       100);

        String serialized = ETSampleJsonSerializer.serialize(sample, ETJsonOutputType.COMPACT);
        ETSample deserialized = ETSampleJsonSerializer.deserialize(serialized);

        assertSampleEquals(sample, deserialized);
    }

    @Test
    public void serializeCollection_compact_serializesToCompactFormat() {
        ETSample sample0 = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                        new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                        100);
        ETSample sample1 = new ETSample(new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                                        new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                                        200);

        ETChronologicCollection<ETSample> samples = new ETChronologicCollection<>();
        samples.add(sample0);
        samples.add(sample1);

        String serialized = ETSampleJsonSerializer.serializeCollection(samples, ETJsonOutputType.COMPACT);

        assertEquals("[{\"leftEye\":{\"diameter\":1.0,\"eyePosX\":1.0,\"eyePosY\":1.0,\"eyePosZ\":1.0,\"gazeX\":1.0,\"gazeY\":1.0}," +
                     "\"rightEye\":{\"diameter\":1.0,\"eyePosX\":1.0,\"eyePosY\":1.0,\"eyePosZ\":1.0,\"gazeX\":1.0,\"gazeY\":1.0}," +
                     "\"timestamp\":100}," +
                     "{\"leftEye\":{\"diameter\":2.0,\"eyePosX\":2.0,\"eyePosY\":2.0,\"eyePosZ\":2.0,\"gazeX\":2.0,\"gazeY\":2.0}," +
                     "\"rightEye\":{\"diameter\":2.0,\"eyePosX\":2.0,\"eyePosY\":2.0,\"eyePosZ\":2.0,\"gazeX\":2.0,\"gazeY\":2.0}," +
                     "\"timestamp\":200}]",
                     serialized);
    }

    @Test
    public void serializeCollection_pretty_serializesToPrettyPrintFormat() {
        ETSample sample0 = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                        new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                                        100);
        ETSample sample1 = new ETSample(new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                                        new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                                        200);

        ETChronologicCollection<ETSample> samples = new ETChronologicCollection<>();
        samples.add(sample0);
        samples.add(sample1);

        String serialized = ETSampleJsonSerializer.serializeCollection(samples, ETJsonOutputType.PRETTY);

        assertEquals("[\n"                          +
                     "  {\n"                        +
                     "    \"leftEye\": {\n"         +
                     "      \"diameter\": 1.0,\n"   +
                     "      \"eyePosX\": 1.0,\n"    +
                     "      \"eyePosY\": 1.0,\n"    +
                     "      \"eyePosZ\": 1.0,\n"    +
                     "      \"gazeX\": 1.0,\n"      +
                     "      \"gazeY\": 1.0\n"       +
                     "    },\n"                     +
                     "    \"rightEye\": {\n"        +
                     "      \"diameter\": 1.0,\n"   +
                     "      \"eyePosX\": 1.0,\n"    +
                     "      \"eyePosY\": 1.0,\n"    +
                     "      \"eyePosZ\": 1.0,\n"    +
                     "      \"gazeX\": 1.0,\n"      +
                     "      \"gazeY\": 1.0\n"       +
                     "    },\n"                     +
                     "    \"timestamp\": 100\n"     +
                     "  },\n"                       +
                     "  {\n"                        +
                     "    \"leftEye\": {\n"         +
                     "      \"diameter\": 2.0,\n"   +
                     "      \"eyePosX\": 2.0,\n"    +
                     "      \"eyePosY\": 2.0,\n"    +
                     "      \"eyePosZ\": 2.0,\n"    +
                     "      \"gazeX\": 2.0,\n"      +
                     "      \"gazeY\": 2.0\n"       +
                     "    },\n"                     +
                     "    \"rightEye\": {\n"        +
                     "      \"diameter\": 2.0,\n"   +
                     "      \"eyePosX\": 2.0,\n"    +
                     "      \"eyePosY\": 2.0,\n"    +
                     "      \"eyePosZ\": 2.0,\n"    +
                     "      \"gazeX\": 2.0,\n"      +
                     "      \"gazeY\": 2.0\n"       +
                     "    },\n"                     +
                     "    \"timestamp\": 200\n"     +
                     "  }\n"                        +
                     "]",
                     serialized);
    }

    @Test
    public void serializeCollection_deserializeCollection_producesIdenticalCollections() {
        ETSample sample0 = new ETSample(new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                new ETEyeData(1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                100);
        ETSample sample1 = new ETSample(new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                new ETEyeData(2.0, 2.0, 2.0, 2.0, 2.0, 2.0),
                200);

        ETChronologicCollection<ETSample> samples = new ETChronologicCollection<>();
        samples.add(sample0);
        samples.add(sample1);

        String serialized = ETSampleJsonSerializer.serializeCollection(samples, ETJsonOutputType.PRETTY);
        ETChronologicCollection<ETSample> deserialized = ETSampleJsonSerializer.deserializeCollection(serialized);

        List<ETSample> deserializedSamples = new ArrayList<>(deserialized);

        assertSampleEquals(sample0, deserializedSamples.get(0));
        assertSampleEquals(sample1, deserializedSamples.get(1));
    }

    private void assertSampleEquals(ETSample expected, ETSample actual) {
        final double DELTA = 1e-15;

        assertEquals(expected.getLeftEye().getDiameter(),
                     actual.getLeftEye().getDiameter(),
                     DELTA);
        assertEquals(expected.getLeftEye().getEyePositionX(),
                     actual.getLeftEye().getEyePositionX(),
                     DELTA);
        assertEquals(expected.getLeftEye().getEyePositionY(),
                     actual.getLeftEye().getEyePositionY(),
                     DELTA);
        assertEquals(expected.getLeftEye().getEyePositionZ(),
                     actual.getLeftEye().getEyePositionZ(),
                     DELTA);
        assertEquals(expected.getLeftEye().getGazeX(),
                     actual.getLeftEye().getGazeX(),
                     DELTA);
        assertEquals(expected.getLeftEye().getGazeY(),
                     actual.getLeftEye().getGazeY(),
                     DELTA);

        assertEquals(expected.getRightEye().getDiameter(),
                     actual.getRightEye().getDiameter(),
                     DELTA);
        assertEquals(expected.getRightEye().getEyePositionX(),
                     actual.getRightEye().getEyePositionX(),
                     DELTA);
        assertEquals(expected.getRightEye().getEyePositionY(),
                     actual.getRightEye().getEyePositionY(),
                     DELTA);
        assertEquals(expected.getRightEye().getEyePositionZ(),
                     actual.getRightEye().getEyePositionZ(),
                     DELTA);
        assertEquals(expected.getRightEye().getGazeX(),
                     actual.getRightEye().getGazeX(),
                     DELTA);
        assertEquals(expected.getRightEye().getGazeY(),
                     actual.getRightEye().getGazeY(),
                     DELTA);

        assertEquals(expected.getTimestamp(), actual.getTimestamp());
    }
}

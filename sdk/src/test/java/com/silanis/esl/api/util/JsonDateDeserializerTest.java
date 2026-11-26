package com.silanis.esl.api.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonDateDeserializerTest {

    private TimeZone defaultTimeZone;

    @Before
    public void setup() {
        defaultTimeZone = TimeZone.getDefault();
        // A JVM time zone different from the offsets below, so ignoring the offset would be caught
        TimeZone.setDefault(TimeZone.getTimeZone("America/Toronto"));
    }

    @After
    public void tearDown() {
        TimeZone.setDefault(defaultTimeZone);
    }

    @Test
    public void extractIso8061DateHonoursOffset() throws Exception {
        Date date = JsonDateDeserializer.extractIso8061Date("2026-10-10T19:28:47+03:30");

        assertEquals(Instant.parse("2026-10-10T15:58:47Z"), date.toInstant());
    }

    @Test
    public void extractIso8061DateReadsUtcValues() throws Exception {
        Date date = JsonDateDeserializer.extractIso8061Date("2026-10-10T15:58:47Z");

        assertEquals(Instant.parse("2026-10-10T15:58:47Z"), date.toInstant());
    }

    @Test
    public void extractIso8061OffsetDateTimeKeepsOffset() {
        OffsetDateTime date = JsonDateDeserializer.extractIso8061OffsetDateTime("2026-10-10T19:28:47+03:30");

        assertEquals(OffsetDateTime.of(2026, 10, 10, 19, 28, 47, 0, ZoneOffset.ofHoursMinutes(3, 30)), date);
    }

    @Test
    public void extractIso8061OffsetDateTimeReturnsNullWithoutOffset() {
        assertNull(JsonDateDeserializer.extractIso8061OffsetDateTime("2026-10-10T15:58:47Z"));
        assertNull(JsonDateDeserializer.extractIso8061OffsetDateTime("2026-10-10T15:58:47"));
        assertNull(JsonDateDeserializer.extractIso8061OffsetDateTime(null));
    }
}

package com.silanis.esl.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.silanis.esl.sdk.internal.Serialization;

/**
 * The subclasses override setDue, so these tests make sure each of them still reads "due" through
 * BasePackage.setDueFromJson and keeps the offset sent by the server.
 */
public class PackageDueDeserializationTest {

    private static final List<Class<? extends BasePackage>> PACKAGE_CLASSES =
            Arrays.asList(Package.class, BaseTemplate.class, Template.class, Layout.class);

    @Test
    public void dueWithOffsetIsKeptForEveryPackageClass() {
        for (Class<? extends BasePackage> packageClass : PACKAGE_CLASSES) {
            BasePackage apiPackage = Serialization.fromJson(jsonWithDue("2026-10-10T19:28:47+03:30"), packageClass);

            assertEquals(packageClass.getSimpleName(), Instant.parse("2026-10-10T15:58:47Z"), apiPackage.getDue().toInstant());
            assertEquals(packageClass.getSimpleName(),
                    OffsetDateTime.of(2026, 10, 10, 19, 28, 47, 0, ZoneOffset.ofHoursMinutes(3, 30)),
                    apiPackage.getDueWithOffset());
        }
    }

    @Test
    public void dueInUtcHasNoOffsetForEveryPackageClass() {
        for (Class<? extends BasePackage> packageClass : PACKAGE_CLASSES) {
            BasePackage apiPackage = Serialization.fromJson(jsonWithDue("2026-10-10T15:58:47Z"), packageClass);

            assertEquals(packageClass.getSimpleName(), Instant.parse("2026-10-10T15:58:47Z"), apiPackage.getDue().toInstant());
            assertNull(packageClass.getSimpleName(), apiPackage.getDueWithOffset());
        }
    }

    private String jsonWithDue(String due) {
        return "{\"id\":\"pkg1\",\"name\":\"Test Package\",\"due\":\"" + due + "\"}";
    }
}

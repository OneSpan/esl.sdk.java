package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationMethods extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_PRIMARY_SET = "primary";

    private Set<NotificationMethod> primary;

    public NotificationMethods() {}

    public NotificationMethods setPrimary(Set<NotificationMethod> collection) {
        throwOnNull(FIELD_PRIMARY_SET, collection);
        setDirty(FIELD_PRIMARY_SET);
        this.primary = sort(collection);
        return this;
    }

    public NotificationMethods(Set<NotificationMethod> primary) {
        this.setPrimary(primary);
    }

    @JsonProperty("primary")
    public Set<NotificationMethod> getPrimary() {
        return primary;
    }

    private Set<NotificationMethod> sort(Set<NotificationMethod> collection) {
        return collection == null ? null : collection.stream()
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}


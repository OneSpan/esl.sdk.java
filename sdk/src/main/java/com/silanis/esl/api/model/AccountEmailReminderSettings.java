package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountEmailReminderSettings extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_START_IN_DAYS_DELAY = "startInDaysDelay";
    @JsonIgnore
    public static final String FIELD_INTERVAL_IN_DAYS = "intervalInDays";
    @JsonIgnore
    public static final String FIELD_REPETITIONS_COUNT = "repetitionsCount";

    protected Integer _startInDaysDelay ;
    protected Integer _intervalInDays ;
    protected Integer _repetitionsCount ;

    public AccountEmailReminderSettings() {

            //Empty Constructor

    }


    public AccountEmailReminderSettings setStartInDaysDelay(Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_START_IN_DAYS_DELAY, value);
        this._startInDaysDelay = value;
        setDirty(FIELD_START_IN_DAYS_DELAY);
        return this;
    }

    @JsonIgnore
    public AccountEmailReminderSettings safeSetStartInDaysDelay(Integer value ){
        if ( value != null ) {
            this.setStartInDaysDelay(value);
        }
        return this;
    }

    public Integer getStartInDaysDelay(){
        return _startInDaysDelay;
    }

    @JsonIgnore
    public Integer evalStartInDaysDelay(){
        return _startInDaysDelay == null ? 0 : _startInDaysDelay.intValue();
    }

    public AccountEmailReminderSettings setIntervalInDays(Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_INTERVAL_IN_DAYS, value);
        this._intervalInDays = value;
        setDirty(FIELD_INTERVAL_IN_DAYS);
        return this;
    }

    @JsonIgnore
    public AccountEmailReminderSettings safeSetIntervalInDays(Integer value ){
        if ( value != null ) {
            this.setIntervalInDays(value);
        }
        return this;
    }

    public Integer getIntervalInDays(){
        return _intervalInDays;
    }

    @JsonIgnore
    public Integer evalIntervalInDays(){
        return _intervalInDays == null ? 0 : _intervalInDays.intValue();
    }

    public AccountEmailReminderSettings setRepetitionsCount(Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_REPETITIONS_COUNT, value);
        this._repetitionsCount = value;
        setDirty(FIELD_REPETITIONS_COUNT);
        return this;
    }

    @JsonIgnore
    public AccountEmailReminderSettings safeSetRepetitionsCount(Integer value ){
        if ( value != null ) {
            this.setRepetitionsCount(value);
        }
        return this;
    }

    public Integer getRepetitionsCount(){
        return _repetitionsCount;
    }

    @JsonIgnore
    public Integer evalRepetitionsCount(){
        return _repetitionsCount == null ? 0 : _repetitionsCount.intValue();
    }
}

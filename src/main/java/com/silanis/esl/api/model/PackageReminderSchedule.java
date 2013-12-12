package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageReminderSchedule extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_INTERVALINDAYS = "intervalInDays";
    @JsonIgnore
    public static final String FIELD_PACKAGEID = "packageId";
    @JsonIgnore
    public static final String FIELD_REMINDERS = "reminders";
    @JsonIgnore
    public static final String FIELD_REPETITIONSCOUNT = "repetitionsCount";
    @JsonIgnore
    public static final String FIELD_STARTINDAYSDELAY = "startInDaysDelay";
    
    // Empty Constructor
    public PackageReminderSchedule ( ) {}
    
    // Fields
    protected Integer _intervalInDays;
    protected String _packageId = "";
    protected List<PackageReminder> _reminders = new ArrayList<PackageReminder>();
    protected Integer _repetitionsCount;
    protected Integer _startInDaysDelay;
    
    // Accessors
        
    
    public PackageReminderSchedule setIntervalInDays( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_INTERVALINDAYS,value);
        // TODO With proper compare
        // if ( this._intervalInDays == value ) return this;
        this._intervalInDays = value;
        setDirty(FIELD_INTERVALINDAYS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminderSchedule safeSetIntervalInDays( Integer value ){
        if ( value != null ) { this.setIntervalInDays( value ); }
        return this;
    }
    public Integer getIntervalInDays(){
        return _intervalInDays;
    }
    
        
    
    public PackageReminderSchedule setPackageId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGEID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._packageId == value ) return this;
        this._packageId = value;
        setDirty(FIELD_PACKAGEID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminderSchedule safeSetPackageId( String value ){
        if ( value != null ) { this.setPackageId( value ); }
        return this;
    }
    public String getPackageId(){
        return _packageId;
    }
    
        
    
    public PackageReminderSchedule setReminders( List<PackageReminder> value ){
        SchemaSanitizer.throwOnNull(FIELD_REMINDERS,value);
        // TODO With proper compare
        // if ( this._reminders == value ) return this;
        this._reminders = value;
        setDirty(FIELD_REMINDERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminderSchedule safeSetReminders( List<PackageReminder> value ){
        if ( value != null ) { this.setReminders( value ); }
        return this;
    }
    public List<PackageReminder> getReminders(){
        return _reminders;
    }
    // List adder
    public PackageReminderSchedule addReminder( PackageReminder value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._reminders.add(value);
        setDirty(FIELD_REMINDERS);
        return this;
    }
    
        
    
    public PackageReminderSchedule setRepetitionsCount( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_REPETITIONSCOUNT,value);
        // TODO With proper compare
        // if ( this._repetitionsCount == value ) return this;
        this._repetitionsCount = value;
        setDirty(FIELD_REPETITIONSCOUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminderSchedule safeSetRepetitionsCount( Integer value ){
        if ( value != null ) { this.setRepetitionsCount( value ); }
        return this;
    }
    public Integer getRepetitionsCount(){
        return _repetitionsCount;
    }
    
        
    
    public PackageReminderSchedule setStartInDaysDelay( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_STARTINDAYSDELAY,value);
        // TODO With proper compare
        // if ( this._startInDaysDelay == value ) return this;
        this._startInDaysDelay = value;
        setDirty(FIELD_STARTINDAYSDELAY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminderSchedule safeSetStartInDaysDelay( Integer value ){
        if ( value != null ) { this.setStartInDaysDelay( value ); }
        return this;
    }
    public Integer getStartInDaysDelay(){
        return _startInDaysDelay;
    }
    
    
}
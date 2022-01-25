package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountPackageSettings extends Model
        implements java.io.Serializable
{

    @JsonIgnore
    public static final String FIELD_ADA = "ada";
    @JsonIgnore
    public static final String FIELD_DECLINEBUTTON = "declineButton";
    @JsonIgnore
    public static final String FIELD_DEFAULTTIMEBASEDEXPIRY = "defaultTimeBasedExpiry";
    @JsonIgnore
    public static final String FIELD_DISABLEDECLINEOTHER = "disableDeclineOther";
    @JsonIgnore
    public static final String FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE = "disableDownloadForUncompletedPackage";
    @JsonIgnore
    public static final String FIELD_DISABLEFIRSTINPERSONAFFIDAVIT = "disableFirstInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_DISABLEINPERSONAFFIDAVIT = "disableInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_DISABLESECONDINPERSONAFFIDAVIT = "disableSecondInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_ENFORCECAPTURESIGNATURE = "enforceCaptureSignature";
    @JsonIgnore
    public static final String FIELD_EXTRACTACROFIELDS = "extractAcroFields";
    @JsonIgnore
    public static final String FIELD_EXTRACTTEXTTAGS = "extractTextTags";
    @JsonIgnore
    public static final String FIELD_GLOBALACTIONSDOWNLOAD = "globalActionsDownload";
    @JsonIgnore
    public static final String FIELD_GLOBALACTIONSHIDEEVIDENCESUMMARY = "globalActionsHideEvidenceSummary";
    @JsonIgnore
    public static final String FIELD_GLOBALACTIONSSAVEASLAYOUT = "globalActionsSaveAsLayout";
    @JsonIgnore
    public static final String FIELD_HIDECAPTURETEXT = "hideCaptureText";
    @JsonIgnore
    public static final String FIELD_HIDELANGUAGEDROPDOWN = "hideLanguageDropdown";
    @JsonIgnore
    public static final String FIELD_HIDEPACKAGEOWNERINPERSON = "hidePackageOwnerInPerson";
    @JsonIgnore
    public static final String FIELD_HIDEWATERMARK = "hideWatermark";
    @JsonIgnore
    public static final String FIELD_INPERSON = "inPerson";
    @JsonIgnore
    public static final String FIELD_LEFTMENUEXPAND = "leftMenuExpand";
    @JsonIgnore
    public static final String FIELD_OPTIONALNAVIGATION = "optionalNavigation";
    @JsonIgnore
    public static final String FIELD_SHOWNSEHELP = "showNseHelp";
    @JsonIgnore
    public static final String FIELD_SHOWNSELOGOINIFRAME = "showNseLogoInIframe";
    @JsonIgnore
    public static final String FIELD_SHOWNSEOVERVIEW = "showNseOverview";

    protected Boolean _ada = false;
    protected Boolean _declineButton = true;
    protected Boolean _defaultTimeBasedExpiry = false;
    protected Boolean _disableDeclineOther = false;
    protected Boolean _disableDownloadForUncompletedPackage = false;
    protected Boolean _disableFirstInPersonAffidavit = false;
    protected Boolean _disableInPersonAffidavit = false;
    protected Boolean _disableSecondInPersonAffidavit = false;
    protected Boolean _enforceCaptureSignature = false;
    protected Boolean _extractAcroFields = false;
    protected Boolean _extractTextTags = false;
    protected Boolean _globalActionsDownload = true;
    protected Boolean _globalActionsHideEvidenceSummary = false;
    protected Boolean _globalActionsSaveAsLayout = true;
    protected Boolean _hideCaptureText = false;
    protected Boolean _hideLanguageDropdown = false;
    protected Boolean _hidePackageOwnerInPerson = false;
    protected Boolean _hideWatermark = false;
    protected Boolean _inPerson = false;
    protected Boolean _leftMenuExpand = false;
    protected Boolean _optionalNavigation = false;
    protected Boolean _showNseHelp = false;
    protected Boolean _showNseLogoInIframe = false;
    protected Boolean _showNseOverview = false;

    // Empty Constructor
    public AccountPackageSettings() {}

    public AccountPackageSettings setAda(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ADA, value);
        this._ada = value;
        setDirty(FIELD_ADA);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetAda(Boolean value ){
        if ( value != null ) { this.setAda( value ); }
        return this;
    }
    public Boolean getAda(){
        return _ada;
    }
    @JsonIgnore
    public boolean evalAda(){
        return _ada == null ? true : _ada.booleanValue();
    }

    public AccountPackageSettings setDeclineButton(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DECLINEBUTTON, value);
        this._declineButton = value;
        setDirty(FIELD_DECLINEBUTTON);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDeclineButton(Boolean value ){
        if ( value != null ) { this.setDeclineButton( value ); }
        return this;
    }
    public Boolean getDeclineButton(){
        return _declineButton;
    }
    @JsonIgnore
    public boolean evalDeclineButton(){
        return _declineButton == null ? true : _declineButton.booleanValue();
    }

    public AccountPackageSettings setDefaultTimeBasedExpiry(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DEFAULTTIMEBASEDEXPIRY, value);
        this._defaultTimeBasedExpiry = value;
        setDirty(FIELD_DEFAULTTIMEBASEDEXPIRY);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDefaultTimeBasedExpiry(Boolean value ){
        if ( value != null ) { this.setDefaultTimeBasedExpiry( value ); }
        return this;
    }
    public Boolean getDefaultTimeBasedExpiry(){
        return _defaultTimeBasedExpiry;
    }
    @JsonIgnore
    public boolean evalDefaultTimeBasedExpiry(){
        return _defaultTimeBasedExpiry == null ? true : _defaultTimeBasedExpiry.booleanValue();
    }

    public AccountPackageSettings setDisableDeclineOther(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEDECLINEOTHER, value);
        this._disableDeclineOther = value;
        setDirty(FIELD_DISABLEDECLINEOTHER);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDisableDeclineOther(Boolean value ){
        if ( value != null ) { this.setDisableDeclineOther( value ); }
        return this;
    }
    public Boolean getDisableDeclineOther(){
        return _disableDeclineOther;
    }
    @JsonIgnore
    public boolean evalDisableDeclineOther(){
        return _disableDeclineOther == null ? true : _disableDeclineOther.booleanValue();
    }

    public AccountPackageSettings setDisableDownloadForUncompletedPackage(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE, value);
        this._disableDownloadForUncompletedPackage = value;
        setDirty(FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDisableDownloadForUncompletedPackage(Boolean value ){
        if ( value != null ) { this.setDisableDownloadForUncompletedPackage( value ); }
        return this;
    }
    public Boolean getDisableDownloadForUncompletedPackage(){
        return _disableDownloadForUncompletedPackage;
    }
    @JsonIgnore
    public boolean evalDisableDownloadForUncompletedPackage(){
        return _disableDownloadForUncompletedPackage == null ? true : _disableDownloadForUncompletedPackage.booleanValue();
    }

    public AccountPackageSettings setDisableFirstInPersonAffidavit(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEFIRSTINPERSONAFFIDAVIT, value);
        this._disableFirstInPersonAffidavit = value;
        setDirty(FIELD_DISABLEFIRSTINPERSONAFFIDAVIT);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDisableFirstInPersonAffidavit(Boolean value ){
        if ( value != null ) { this.setDisableFirstInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableFirstInPersonAffidavit(){
        return _disableFirstInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableFirstInPersonAffidavit(){
        return _disableFirstInPersonAffidavit == null ? true : _disableFirstInPersonAffidavit.booleanValue();
    }

    public AccountPackageSettings setDisableInPersonAffidavit(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEINPERSONAFFIDAVIT, value);
        this._disableInPersonAffidavit = value;
        setDirty(FIELD_DISABLEINPERSONAFFIDAVIT);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDisableInPersonAffidavit(Boolean value ){
        if ( value != null ) { this.setDisableInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableInPersonAffidavit(){
        return _disableInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableInPersonAffidavit(){
        return _disableInPersonAffidavit == null ? true : _disableInPersonAffidavit.booleanValue();
    }

    public AccountPackageSettings setDisableSecondInPersonAffidavit(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLESECONDINPERSONAFFIDAVIT, value);
        this._disableSecondInPersonAffidavit = value;
        setDirty(FIELD_DISABLESECONDINPERSONAFFIDAVIT);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetDisableSecondInPersonAffidavit(Boolean value ){
        if ( value != null ) { this.setDisableSecondInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableSecondInPersonAffidavit(){
        return _disableSecondInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableSecondInPersonAffidavit(){
        return _disableSecondInPersonAffidavit == null ? true : _disableSecondInPersonAffidavit.booleanValue();
    }

    public AccountPackageSettings setEnforceCaptureSignature(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ENFORCECAPTURESIGNATURE, value);
        this._enforceCaptureSignature = value;
        setDirty(FIELD_ENFORCECAPTURESIGNATURE);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetEnforceCaptureSignature(Boolean value ){
        if ( value != null ) { this.setEnforceCaptureSignature( value ); }
        return this;
    }
    public Boolean getEnforceCaptureSignature(){
        return _enforceCaptureSignature;
    }
    @JsonIgnore
    public boolean evalEnforceCaptureSignature(){
        return _enforceCaptureSignature == null ? true : _enforceCaptureSignature.booleanValue();
    }

    public AccountPackageSettings setExtractAcroFields(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EXTRACTACROFIELDS, value);
        this._extractAcroFields = value;
        setDirty(FIELD_EXTRACTACROFIELDS);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetExtractAcroFields(Boolean value ){
        if ( value != null ) { this.setExtractAcroFields( value ); }
        return this;
    }
    public Boolean getExtractAcroFields(){
        return _extractAcroFields;
    }
    @JsonIgnore
    public boolean evalExtractAcroFields(){
        return _extractAcroFields == null ? true : _extractAcroFields.booleanValue();
    }

    public AccountPackageSettings setExtractTextTags(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EXTRACTTEXTTAGS, value);
        this._extractTextTags = value;
        setDirty(FIELD_EXTRACTTEXTTAGS);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetExtractTextTags(Boolean value ){
        if ( value != null ) { this.setExtractTextTags( value ); }
        return this;
    }
    public Boolean getExtractTextTags(){
        return _extractTextTags;
    }
    @JsonIgnore
    public boolean evalExtractTextTags(){
        return _extractTextTags == null ? true : _extractTextTags.booleanValue();
    }

    public AccountPackageSettings setGlobalActionsDownload(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_GLOBALACTIONSDOWNLOAD, value);
        this._globalActionsDownload = value;
        setDirty(FIELD_GLOBALACTIONSDOWNLOAD);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetGlobalActionsDownload(Boolean value ){
        if ( value != null ) { this.setGlobalActionsDownload( value ); }
        return this;
    }
    public Boolean getGlobalActionsDownload(){
        return _globalActionsDownload;
    }
    @JsonIgnore
    public boolean evalGlobalActionsDownload(){
        return _globalActionsDownload == null ? true : _globalActionsDownload.booleanValue();
    }

    public AccountPackageSettings setGlobalActionsHideEvidenceSummary(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_GLOBALACTIONSHIDEEVIDENCESUMMARY, value);
        this._globalActionsHideEvidenceSummary = value;
        setDirty(FIELD_GLOBALACTIONSHIDEEVIDENCESUMMARY);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetGlobalActionsHideEvidenceSummary(Boolean value ){
        if ( value != null ) { this.setGlobalActionsHideEvidenceSummary( value ); }
        return this;
    }
    public Boolean getGlobalActionsHideEvidenceSummary(){
        return _globalActionsHideEvidenceSummary;
    }
    @JsonIgnore
    public boolean evalGlobalActionsHideEvidenceSummary(){
        return _globalActionsHideEvidenceSummary == null ? true : _globalActionsHideEvidenceSummary.booleanValue();
    }

    public AccountPackageSettings setGlobalActionsSaveAsLayout(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_GLOBALACTIONSSAVEASLAYOUT, value);
        this._globalActionsSaveAsLayout = value;
        setDirty(FIELD_GLOBALACTIONSSAVEASLAYOUT);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetGlobalActionsSaveAsLayout(Boolean value ){
        if ( value != null ) { this.setGlobalActionsSaveAsLayout( value ); }
        return this;
    }
    public Boolean getGlobalActionsSaveAsLayout(){
        return _globalActionsSaveAsLayout;
    }
    @JsonIgnore
    public boolean evalGlobalActionsSaveAsLayout(){
        return _globalActionsSaveAsLayout == null ? true : _globalActionsSaveAsLayout.booleanValue();
    }

    public AccountPackageSettings setHideCaptureText(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDECAPTURETEXT, value);
        this._hideCaptureText = value;
        setDirty(FIELD_HIDECAPTURETEXT);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetHideCaptureText(Boolean value ){
        if ( value != null ) { this.setHideCaptureText( value ); }
        return this;
    }
    public Boolean getHideCaptureText(){
        return _hideCaptureText;
    }
    @JsonIgnore
    public boolean evalHideCaptureText(){
        return _hideCaptureText == null ? true : _hideCaptureText.booleanValue();
    }

    public AccountPackageSettings setHideLanguageDropdown(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDELANGUAGEDROPDOWN, value);
        this._hideLanguageDropdown = value;
        setDirty(FIELD_HIDELANGUAGEDROPDOWN);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetHideLanguageDropdown(Boolean value ){
        if ( value != null ) { this.setHideLanguageDropdown( value ); }
        return this;
    }
    public Boolean getHideLanguageDropdown(){
        return _hideLanguageDropdown;
    }
    @JsonIgnore
    public boolean evalHideLanguageDropdown(){
        return _hideLanguageDropdown == null ? true : _hideLanguageDropdown.booleanValue();
    }

    public AccountPackageSettings setHidePackageOwnerInPerson(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDEPACKAGEOWNERINPERSON, value);
        this._hidePackageOwnerInPerson = value;
        setDirty(FIELD_HIDEPACKAGEOWNERINPERSON);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetHidePackageOwnerInPerson(Boolean value ){
        if ( value != null ) { this.setHidePackageOwnerInPerson( value ); }
        return this;
    }
    public Boolean getHidePackageOwnerInPerson(){
        return _hidePackageOwnerInPerson;
    }
    @JsonIgnore
    public boolean evalHidePackageOwnerInPerson(){
        return _hidePackageOwnerInPerson == null ? true : _hidePackageOwnerInPerson.booleanValue();
    }

    public AccountPackageSettings setHideWatermark(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDEWATERMARK, value);
        this._hideWatermark = value;
        setDirty(FIELD_HIDEWATERMARK);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetHideWatermark(Boolean value ){
        if ( value != null ) { this.setHideWatermark( value ); }
        return this;
    }
    public Boolean getHideWatermark(){
        return _hideWatermark;
    }
    @JsonIgnore
    public boolean evalHideWatermark(){
        return _hideWatermark == null ? true : _hideWatermark.booleanValue();
    }

    public AccountPackageSettings setInPerson(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_INPERSON, value);
        this._inPerson = value;
        setDirty(FIELD_INPERSON);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetInPerson(Boolean value ){
        if ( value != null ) { this.setInPerson( value ); }
        return this;
    }
    public Boolean getInPerson(){
        return _inPerson;
    }
    @JsonIgnore
    public boolean evalInPerson(){
        return _inPerson == null ? true : _inPerson.booleanValue();
    }

    public AccountPackageSettings setLeftMenuExpand(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_LEFTMENUEXPAND, value);
        this._leftMenuExpand = value;
        setDirty(FIELD_LEFTMENUEXPAND);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetLeftMenuExpand(Boolean value ){
        if ( value != null ) { this.setLeftMenuExpand( value ); }
        return this;
    }
    public Boolean getLeftMenuExpand(){
        return _leftMenuExpand;
    }
    @JsonIgnore
    public boolean evalLeftMenuExpand(){
        return _leftMenuExpand == null ? true : _leftMenuExpand.booleanValue();
    }

    public AccountPackageSettings setOptionalNavigation(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_OPTIONALNAVIGATION, value);
        this._optionalNavigation = value;
        setDirty(FIELD_OPTIONALNAVIGATION);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetOptionalNavigation(Boolean value ){
        if ( value != null ) { this.setOptionalNavigation( value ); }
        return this;
    }
    public Boolean getOptionalNavigation(){
        return _optionalNavigation;
    }
    @JsonIgnore
    public boolean evalOptionalNavigation(){
        return _optionalNavigation == null ? true : _optionalNavigation.booleanValue();
    }

    public AccountPackageSettings setShowNseHelp(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SHOWNSEHELP, value);
        this._showNseHelp = value;
        setDirty(FIELD_SHOWNSEHELP);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetShowNseHelp(Boolean value ){
        if ( value != null ) { this.setShowNseHelp( value ); }
        return this;
    }
    public Boolean getShowNseHelp(){
        return _showNseHelp;
    }
    @JsonIgnore
    public boolean evalShowNseHelp(){
        return _showNseHelp == null ? true : _showNseHelp.booleanValue();
    }

    public AccountPackageSettings setShowNseLogoInIframe(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SHOWNSELOGOINIFRAME, value);
        this._showNseLogoInIframe = value;
        setDirty(FIELD_SHOWNSELOGOINIFRAME);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetShowNseLogoInIframe(Boolean value ){
        if ( value != null ) { this.setShowNseLogoInIframe( value ); }
        return this;
    }
    public Boolean getShowNseLogoInIframe(){
        return _showNseLogoInIframe;
    }
    @JsonIgnore
    public boolean evalShowNseLogoInIframe(){
        return _showNseLogoInIframe == null ? true : _showNseLogoInIframe.booleanValue();
    }

    public AccountPackageSettings setShowNseOverview(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SHOWNSEOVERVIEW, value);
        this._showNseOverview = value;
        setDirty(FIELD_SHOWNSEOVERVIEW);
        return this;
    }
    @JsonIgnore
    public AccountPackageSettings safeSetShowNseOverview(Boolean value ){
        if ( value != null ) { this.setShowNseOverview( value ); }
        return this;
    }
    public Boolean getShowNseOverview(){
        return _showNseOverview;
    }
    @JsonIgnore
    public boolean evalShowNseOverview(){
        return _showNseOverview == null ? true : _showNseOverview.booleanValue();
    }

}
package com.silanis.esl.api.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
import com.silanis.esl.sdk.ChooseSignatureStyleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChooseSignatureOptions extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_ALLOWSTYLING = "allowStyling";
    @JsonIgnore
    public static final String FIELD_ALLOWDRAWING = "allowDrawing";
    @JsonIgnore
    public static final String FIELD_ALLOWUPLOADING = "allowUploading";
    @JsonIgnore
    public static final String FIELD_ALLOWMOBILESIGNING = "allowMobileSigning";
    @JsonIgnore
    public static final String FIELD_FONTSPERWRITINGSYSTEM = "fontsPerWritingSystem";
    @JsonIgnore
    public static final String FIELD_DEFAULT_SIGNATURE_TYPE = "defaultSignatureType";

    protected Boolean allowStyling = true;
    protected Boolean allowDrawing = true;
    protected Boolean allowUploading = true;
    protected Boolean allowMobileSigning = true;
    protected String defaultSignatureType = ChooseSignatureStyleType.STYLE.name();
    protected Map<String, List<String>> fontsPerWritingSystem = getDefaultFonts();

    public ChooseSignatureOptions() {}

    public ChooseSignatureOptions setAllowStyling(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWSTYLING, value);
        this.allowStyling = value;
        setDirty(FIELD_ALLOWSTYLING);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetAllowStyling(Boolean value) {
        if (value != null) {
            this.setAllowStyling(value);
        }
        return this;
    }

    public Boolean getAllowStyling() {
        return allowStyling;
    }

    @JsonIgnore
    public boolean evalAllowStyling() {
        return allowStyling == null || allowStyling;
    }

    public ChooseSignatureOptions setAllowDrawing(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWDRAWING, value);
        this.allowDrawing = value;
        setDirty(FIELD_ALLOWDRAWING);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetAllowDrawing(Boolean value) {
        if (value != null) {
            this.setAllowDrawing(value);
        }
        return this;
    }

    public Boolean getAllowDrawing() {
        return allowDrawing;
    }

    @JsonIgnore
    public boolean evalAllowDrawing() {
        return allowDrawing == null || allowDrawing;
    }

    public ChooseSignatureOptions setAllowUploading(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWUPLOADING, value);
        this.allowUploading = value;
        setDirty(FIELD_ALLOWUPLOADING);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetAllowUploading(Boolean value) {
        if (value != null) {
            this.setAllowUploading(value);
        }
        return this;
    }

    public Boolean getAllowUploading() {
        return allowUploading;
    }

    @JsonIgnore
    public boolean evalAllowUploading() {
        return allowUploading != null && allowUploading;
    }

    public ChooseSignatureOptions setAllowMobileSigning(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWMOBILESIGNING, value);
        this.allowMobileSigning = value;
        setDirty(FIELD_ALLOWMOBILESIGNING);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetAllowMobileSigning(Boolean value) {
        if (value != null) {
            this.setAllowMobileSigning(value);
        }
        return this;
    }

    public Boolean getAllowMobileSigning() {
        return allowMobileSigning;
    }

    @JsonIgnore
    public boolean evalAllowMobileSigning() {
        return allowMobileSigning == null || allowMobileSigning;
    }

    public ChooseSignatureOptions setFontsPerWritingSystem(Map<String, List<String>> value) {
        this.fontsPerWritingSystem = value;
        setDirty(FIELD_FONTSPERWRITINGSYSTEM);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetFontsPerWritingSystem(Map<String, List<String>> value) {
        if (value != null) {
            this.setFontsPerWritingSystem(value);
        }
        return this;
    }

    public Map<String, List<String>> getFontsPerWritingSystem() {
        return fontsPerWritingSystem;
    }

    public ChooseSignatureOptions setDefaultSignatureType(String defaultSignatureType) {
        SchemaSanitizer.throwOnNull(FIELD_DEFAULT_SIGNATURE_TYPE, defaultSignatureType);
        this.defaultSignatureType = defaultSignatureType;
        setDirty(FIELD_DEFAULT_SIGNATURE_TYPE);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureOptions safeSetDefaultSignatureType(String defaultSignatureType) {
        if (defaultSignatureType != null) {
            this.setDefaultSignatureType(defaultSignatureType);
        }
        return this;
    }

    public String getDefaultSignatureType() {
        return defaultSignatureType;
    }

    public static Map<String, List<String>> getDefaultFonts() {
        Map<String, List<String>> fonts = new HashMap<>();

        fonts.put("japanese",
                Arrays.asList("Hina Mincho", "Kaisei Opti", "Yuji Mai", "Mochiy Pop One",
                        "Yusei Magic", "Stick", "Zen Antique Soft", "Reggae One"));

        fonts.put("latin",
                Arrays.asList("Kanit", "Licorice", "Meow Script", "Allison", "Bonheur Royale",
                        "Dancing Script", "Pattaya", "Atma", "Caveat", "Damion",
                        "Shantell Sans", "Nerko One", "Grenze Gotisch"));

        fonts.put("korean",
                Arrays.asList("Chiron Hei HK", "Sunflower", "Diphylleia", "Jua",
                        "Kirang Haerang", "Gamja Flower", "Do Hyeon",
                        "Stylish", "Single Day"));

        fonts.put("cyrillic",
                Arrays.asList("Shantell Sans", "Bad Script", "Playpen Sans",
                        "Bellota", "Sansation", "Philosopher", "Jura",
                        "Didact Gothic", "Playfair"));

        fonts.put("chinese-traditional",
                Arrays.asList("Cactus Classical Serif", "LXGW WenKai TC", "Chiron Hei HK"));

        fonts.put("chinese-simplified",
                Arrays.asList("Noto Sans SC", "WDXL Lubrifont SC", "Ma Shan Zheng",
                        "Long Cang", "Zhi Mang Xing", "Liu Jian Mao Cao"));

        fonts.put("arabic",
                Arrays.asList("Beiruti", "Playpen Sans Arabic", "Amiri",
                        "Aref Ruqaa", "Reem Kufi", "Mirza", "Katibeh"));

        fonts.put("greek",
                Arrays.asList("Gidole", "Vollkorn", "Mansalva", "Brygada 1918",
                        "Ubuntu Condensed", "Zen Maru Gothic",
                        "Playpen Sans", "Jura"));

        return fonts;
    }
}
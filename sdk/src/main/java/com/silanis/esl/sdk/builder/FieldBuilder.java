package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.FieldValidator;
import com.silanis.esl.sdk.TextAnchor;

/**
 * FieldBuilder is a convenient class used to create fields.
 */
public class FieldBuilder {

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 50;
    public static final FieldStyle DEFAULT_STYLE = FieldStyle.UNBOUND_TEXT_FIELD;
    public static final String SELECTED_VALUE = "X";
    public static final String CHECKBOX_CHECKED = SELECTED_VALUE;
    public static final String RADIO_SELECTED = SELECTED_VALUE;

    private int pageNumber;
    private double x;
    private double y;
    private double width = DEFAULT_WIDTH;
    private double height = DEFAULT_HEIGHT;
    private FieldStyle style = DEFAULT_STYLE;
    private String name;
    private boolean extract;
    private FieldValidator fieldValidator;
    private String value;
    private FieldId fieldId;
    private TextAnchor textAnchor;
    private Integer fontSize;
    private String tooltip;

    /**
     * Creates a field builder
     *
     * @return a field builder
     */
    public static FieldBuilder newField() {
        return new FieldBuilder();
    }

    /**
     * Creates a field builder having set the style to BOUND_DATE. A date string
     * will be displayed at the location defined for the field when the signer
     * signs the associated signature field.
     *
     * @return a BOUND_DATE styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder signatureDate() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_DATE);
    }

    /**
     * Creates a filed builder having set the style to BOUND_NAME. The signer's name
     * will be displayed at the location defined for the field when the signer
     * signs the associated signature field.
     *
     * @return a BOUND_NAME styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder signerName() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_NAME);
    }

    /**
     * Creates a field builder having set the style to BOUND_TITLE. The signer's title string
     * will be displayed at the location defined for the field when the signer
     * signs the associated signature field.
     *
     * @return a BOUND_TITLE styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder signerTitle() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_TITLE);
    }

    /**
     * Creates a field builder having set the style to BOUND_COMPANY. The signer's company string
     * will be displayed at the location defined for the field when the signer
     * signs the associated signature field.
     *
     * @return a BOUND_COMPANY styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder signerCompany() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_COMPANY);
    }

    /**
     * Creates a field builder having set the style to UNBOUND_TEXT_FIELD. It
     * defines a text field at the location defined that the signer may be
     * required to fill prior to signing the its associated signature.
     *
     * @return a UNBOUND_TEXT_FIELD styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder textField() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_TEXT_FIELD);
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CUSTOM_FIELD
     * It defines a user defined form field that may be stamped on  the document.
     * <p>
     * E.g.: user's pharmacist license number.
     *
     * @return a UNBOUND_CUSTOM_FIELD styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder customField(String name) {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CUSTOM_FIELD).withName(name);
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CHECK_BOX. It
     * defines a checkbox field at the location defined that the signer may be
     * required to check prior to signing the its associated signature.
     *
     * @return a UNBOUND_CHECK_BOX styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder checkBox() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CHECK_BOX);
    }

    /**
     * Creates a field builder having set the style to UNBOUND_RADIO_BUTTON. It defines
     * a radio button field in a group specified by the user. Only one radio button from
     * a group can be selected at a time.
     *
     * @param group the radio button group
     * @return a UNBOUND_RADIO_BUTTON styled field builder
     * @see FieldStyle
     */

    public static FieldBuilder radioButton(String group) {

        if (group == null) {
            throw new BuilderException("Radio button must have a group.");
        }

        return new FieldBuilder()
                .withStyle(FieldStyle.UNBOUND_RADIO_BUTTON)
                .withValidation(FieldValidatorBuilder.basic()
                        .withOption(group));
    }

    /**
     * Creates a field builder having set the style to DROP_LIST. It defines
     * a drop list field at the location defined that the signer may be
     * required to select prior to signing the its associated signature
     *
     * @return a DROP_LIST styled field builder
     * @see FieldStyle
     */

    public static FieldBuilder dropList() {
        return new FieldBuilder().withStyle(FieldStyle.DROP_LIST);
    }

    /**
     * Creates a field builder having set the style to TEXT_AREA. It defines
     * a text area field at the location defined that the signer may be
     * required to fill prior to signing the its associated signature
     *
     * @return a TEXT_AREA styled field builder
     * @see FieldStyle
     */

    public static FieldBuilder textArea() {
        return new FieldBuilder().withStyle(FieldStyle.TEXT_AREA);
    }

    /**
     * Every bound field (Date, Title, Name) is a label.
     * The user should not have to set a field style to label.
     *
     * @return a LABEL styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder label() {
        return new FieldBuilder().withStyle(FieldStyle.LABEL);
    }

    /**
     * Creates a field builder having set the style to DATEPICKER. It defines
     * a datepicker field at the location defined that the signer may be
     * required to fill prior to signing the its associated signature
     *
     * @return a DATEPICKER styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder datepicker() {
        return new FieldBuilder().withStyle(FieldStyle.DATEPICKER);
    }

    /**
     * Creates a field builder having set the style to BOUND_QRCODE.
     * A QR code field will be displayed at the location defined with a
     * default size of width=77 and height=77.
     *
     * @return a BOUND_QRCODE styled field builder
     * @see FieldStyle
     */
    public static FieldBuilder qrCode() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_QRCODE)
                .withSize(77.0, 77.0);
    }

    /**
     * Sets the page on which the field is located.
     *
     * @param pageNumber the page number
     * @return the field builder itself
     */
    public FieldBuilder onPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the field at the position in pixel relative to the original document, specified by x and y coordinates
     *
     * @param x the x coordinate of the top-left corner min="0"
     * @param y the y coordinate of the top-left corner min="0"
     * @return the field builder itself
     */
    public FieldBuilder atPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size, in pixel, of the field
     *
     * @param width  the width of the field min="0"
     * @param height the height of the field min="0"
     * @return the field builder itself
     */
    public FieldBuilder withSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the style of the field
     *
     * @param style the style of the field
     * @return the field builder itself
     * @see FieldStyle
     */
    public FieldBuilder withStyle(FieldStyle style) {
        this.style = style;
        return this;
    }

    /**
     * Sets the name of the field. The name corresponds to the id of the
     * corresponding acrobat form field. This name is used when positioning form
     * fields based on their original position on the PDF document.
     *
     * @param name the name of the field size(max="255")
     * @return the field builder itself
     */
    public FieldBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Informs OneSpan Sign document engine that the position of the field
     * must be inferred from the position of an acrobat form field with a
     * specified name (@see #withName(String)).
     * <p>
     * When using {@link #withPositionExtracted()} you must not use
     * {@link #withSize(double, double)} and {@link #atPosition(double, double)}.
     *
     * @return the field builder itself
     */
    public FieldBuilder withPositionExtracted() {
        this.extract = true;
        return this;
    }

    /**
     * Informs OneSpan Sign document engine that the position of the field
     * must be defined relative to the position of a certain text string on the document.
     * <p>
     * When using {@link #withPositionAnchor(TextAnchorBuilder)} you must not use
     * {@link #withSize(double, double)}.
     *
     * @param builder
     * @return the field builder itself
     */
    public FieldBuilder withPositionAnchor(TextAnchorBuilder builder) {
        return withPositionAnchor(builder.build());
    }

    /**
     * Informs OneSpan Sign document engine that the position of the field
     * must be defined relative to the position of a certain text string on the document.
     * <p>
     * When using {@link #withPositionAnchor(TextAnchorBuilder)} you must not use
     * {@link #withSize(double, double)}.
     *
     * @param textAnchor
     * @return the field builder itself
     * @see #withPositionAnchor(TextAnchorBuilder)
     */
    public FieldBuilder withPositionAnchor(TextAnchor textAnchor) {
        this.textAnchor = textAnchor;
        return this;
    }

    /**
     * Sets a validator on the field. So, the values of the field should be matched by the provided validator.
     * It is necessary only for the radio button field to have a FieldValidator that defines its group.
     * It is up to the user to decide which validators they want to set to put constrains on the field.
     * Leave blank otherwise.
     *
     * @param fieldValidator the field validator
     * @return the field builder itself
     */
    public FieldBuilder withValidation(FieldValidator fieldValidator) {
        mergeValidation(fieldValidator);
        return this;
    }

    /**
     * Sets a validator on the field that could be easily customized by the field validator builder provided as parameter.
     *
     * @param builder the field validator builder
     * @return the field builder itself
     */
    public FieldBuilder withValidation(FieldValidatorBuilder builder) {
        return withValidation(builder.build());
    }

    private void mergeValidation(FieldValidator fieldValidator) {
        if (null != this.fieldValidator) {
            if (null != fieldValidator.getErrorMessage())
                this.fieldValidator.setErrorMessage(fieldValidator.getErrorMessage());
            if (null != fieldValidator.getMaxLength())
                this.fieldValidator.setMaxLength(fieldValidator.getMaxLength());
            if (null != fieldValidator.getMinLength())
                this.fieldValidator.setMinLength(fieldValidator.getMinLength());
            if (null != fieldValidator.getRegex())
                this.fieldValidator.setRegex(fieldValidator.getRegex());
            if (null != fieldValidator.getOptions() && fieldValidator.getOptions().size() > 0)
                this.fieldValidator.setOptions(fieldValidator.getOptions());
            if (null != fieldValidator.getGroup())
                this.fieldValidator.setGroup(fieldValidator.getGroup());
            if (null != fieldValidator.getMinimumRequired())
                this.fieldValidator.setMinimumRequired(fieldValidator.getMinimumRequired());
            if (null != fieldValidator.getMaximumRequired())
                this.fieldValidator.setMaximumRequired(fieldValidator.getMaximumRequired());
            this.fieldValidator.setRequired(fieldValidator.isRequired());
            this.fieldValidator.setGroupTooltip(fieldValidator.getGroupTooltip());
            this.fieldValidator.setDisabled(fieldValidator.isDisabled());
        } else {
            this.fieldValidator = fieldValidator;
        }
    }

    /**
     * Set a field's value for a radio button or a checkbox
     *
     * @param value true for checked and false for unchecked
     * @return the field builder itself
     */
    public FieldBuilder withValue(boolean value) {
        if (style == FieldStyle.UNBOUND_CHECK_BOX || style == FieldStyle.UNBOUND_RADIO_BUTTON) {
            this.value = value ? SELECTED_VALUE : "";
        }
        return this;
    }

    /**
     * Set a field's value.
     *
     * @param value String value of the field size(max="255")
     * @return the field builder itself
     */

    public FieldBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Set a field's fontSize.
     *
     * @param fontSize Integer fontSize of the field)
     * @return the field builder itself
     */

    public FieldBuilder withFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }
    /**
     * Set a field's tooltip.
     *
     * @param tooltip String tooltip of the field)
     * @return the field builder itself
     */

    public FieldBuilder withTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }
    /**
     * Set a field's unique ID. This id allows the field value to be retrieved
     * when querying a package for information.
     *
     * @param fieldId
     * @return the field builder itself
     */
    public FieldBuilder withId(FieldId fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built Field
     */
    public Field build() {
        Field field = new Field();
        field.setPage(pageNumber);
        field.setX(x);
        field.setY(y);
        field.setStyle(style);
        field.setWidth(width);
        field.setHeight(height);
        field.setName(name);
        field.setExtraction(extract);
        field.setFieldValidator(fieldValidator);
        field.setValue(value);
        field.setFontSize(fontSize);
        field.setTooltip(tooltip);
        if (fieldId != null) {
            field.setId(fieldId);
        }
        if (textAnchor != null) {
            field.setTextAnchor(textAnchor);
        }

        return field;
    }
}
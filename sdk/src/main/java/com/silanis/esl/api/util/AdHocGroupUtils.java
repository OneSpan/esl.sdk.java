package com.silanis.esl.api.util;

import static org.apache.commons.lang3.StringUtils.stripToEmpty;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class AdHocGroupUtils {

  public static final String AD_HOC_GROUP_EMAIL_SUFFIX = "@adhoc.groups.e-signlive.com";
  public static final String AD_HOC_GROUP_SIGNER_TYPE = "AD_HOC_GROUP_SIGNER";
  public static final String EXTERNAL_SIGNER_TYPE = "EXTERNAL_SIGNER";
  public static final String AD_HOC_GROUP_MEMBER_TYPE = "AD_HOC_GROUP_MEMBER";
  public static final String SIGNER_TYPE = "SIGNER";

  private AdHocGroupUtils() {
    //Compliant java:S1118
  }

  /**
   * Determines if the given signer is an ad hoc group signer. Checks if the signer's type is
   * "AD_HOC_GROUP_SIGNER" or if their email matches the ad hoc group email pattern.
   *
   * @param signer the Signer object to check
   * @return true if the signer is an ad hoc group signer, false otherwise
   */
  public static boolean isAdHocGroupSigner(final Signer signer) {
    return AD_HOC_GROUP_SIGNER_TYPE.equals(signer.getSignerType()) || isAdHocGroup(signer.getEmail());
  }

  /**
   * Checks if the provided email belongs to an ad hoc group. Returns true if the email matches the
   * ad hoc group email pattern.
   *
   * @param email the email address to check
   * @return true if the email is an ad hoc group email, false otherwise
   */
  public static boolean isAdHocGroup(final String email) {
    if (StringUtils.isNotBlank(email)) {
      return isAdHocGroupEmail(email);
    } else {
      return false;
    }
  }

  public static boolean isAdHocGroupEmail(final String email) {
    return stripToEmpty(email).endsWith(AD_HOC_GROUP_EMAIL_SUFFIX);
  }

  /**
   * Checks if the provided role is an ad hoc group. A role is considered an ad hoc group if it has
   * signers and the first signer is an ad hoc group signer.
   *
   * @param role the Role object to check
   * @return true if the role is an ad hoc group, false otherwise
   */
  public static boolean isAdHocGroup(final Role role) {
    return CollectionUtils.isNotEmpty(role.getSigners())
        && isAdHocGroupSigner(role.getSigners().get(0));
  }

  /**
   * Converts a list of objects to a list of their string representations. Each object is converted
   * using ReflectionToStringBuilder with MULTI_LINE_STYLE.
   * <p>
   * This method based on Java Reflection, and it is purpose is only to use in Examples classes.
   *
   * @param list the list of objects to convert to strings
   * @return a list of string representations, or an empty list if the input is null or empty
   */
  public static List<String> toString(final List<?> list) {
    if (CollectionUtils.isNotEmpty(list)) {
      return list.stream()
          .map(item -> ReflectionToStringBuilder.toString(item, ToStringStyle.MULTI_LINE_STYLE))
          .collect(Collectors.toList());
    } else {
      return Collections.emptyList();
    }
  }

}

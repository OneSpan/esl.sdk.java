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

  private AdHocGroupUtils() {
    //Compliant java:S1118
  }

  public static boolean isAdHocGroupSigner(final Signer signer) {
    return "AD_HOC_GROUP_SIGNER".equals(signer.getSignerType()) || isAdHocGroup(signer.getEmail());
  }

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

  public static boolean isAdhocGroup(final Role role) {
    return CollectionUtils.isNotEmpty(role.getSigners())
        && isAdHocGroupSigner(role.getSigners().get(0));
  }

  /**
   * Converts a list of objects to a list of their string representations. Each object is converted
   * using ReflectionToStringBuilder with MULTI_LINE_STYLE.
   *
   * This method based on Java Reflection and it is purpose is only to use in Examples classes.
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

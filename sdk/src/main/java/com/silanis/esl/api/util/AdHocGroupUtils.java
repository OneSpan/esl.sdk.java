package com.silanis.esl.api.util;

import static org.apache.commons.lang3.StringUtils.stripToEmpty;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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

}

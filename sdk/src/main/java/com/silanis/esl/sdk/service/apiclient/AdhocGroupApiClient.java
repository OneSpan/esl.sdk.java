package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.service.EslComponent;
import java.util.List;

public final class AdhocGroupApiClient extends EslComponent {

  public AdhocGroupApiClient(final RestClient restClient,
      final String apiUrl) {
    super(restClient, apiUrl);
  }

  /**
   * Creates an adhoc group for the specified package using AdhocGroupResource API
   *
   * @param packageId the ID of the package to add the adhoc group to
   * @param roles the list of roles to include in the adhoc group
   * @return the list of roles added to the adhoc group
   * @throws EslServerException if the server cannot add the adhoc group
   * @throws EslException for other errors during group creation
   */
  public List<Role> createAdhocGroup(final String packageId,
      final List<Role> roles) {
    final String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ADHOC_GROUPS_PATH)
        .replace("{packageId}", packageId)
        .build();

    final String roleJson = JacksonUtil.serialize(roles);
    try {
      this.getClient().post(path, roleJson);
    } catch (final RequestException re) {
      throw new EslServerException("Could not add adhoc group.", re);
    } catch (final Exception ex) {
      throw new EslException("Could not add adhoc group.", ex);
    }
    return roles;
  }

  /**
   * Updates an existing adhoc group for the specified package and group role using AdhocGroupResource API.
   *
   * @param packageId the ID of the package containing the adhoc group
   * @param groupRoleId the ID of the group role to update
   * @param roles the list of roles to update in the adhoc group
   * @return the list of roles after update
   * @throws EslServerException if the server cannot update the adhoc group
   * @throws EslException for other errors during group update
   */
  public List<Role> updateAdhocGroup(final String packageId,
      final String groupRoleId,
      final List<Role> roles) {
    final String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ADHOC_GROUPS_ROLE_PATH)
        .replace("{packageId}", packageId)
        .replace("{groupRoleId}", groupRoleId)
        .build();

    final String roleJson = JacksonUtil.serialize(roles);

    try {
      this.getClient().put(path, roleJson);
    } catch (final RequestException re) {
      throw new EslServerException("Could not update adhoc group.", re);
    } catch (final Exception ex) {
      throw new EslException("Could not update adhoc group.", ex);
    }
    return roles;
  }

  /**
   * Deletes an adhoc group for the specified package and group role using AdhocGroupResource API.
   *
   * @param packageId the ID of the package containing the adhoc group
   * @param groupRoleId the ID of the group role to delete
   * @throws EslServerException if the server cannot delete the adhoc group
   * @throws EslException for other errors during group deletion
   */
  public void deleteAdhocGroup(final String packageId,
      final String groupRoleId) {
    final String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ADHOC_GROUPS_ROLE_PATH)
        .replace("{packageId}", packageId)
        .replace("{groupRoleId}", groupRoleId)
        .build();
    try {
      this.getClient().delete(path);
    } catch (final RequestException re) {
      throw new EslServerException("Could not delete adhoc group.", re);
    } catch (final Exception ex) {
      throw new EslException("Could not delete adhoc group.", ex);
    }
  }
}

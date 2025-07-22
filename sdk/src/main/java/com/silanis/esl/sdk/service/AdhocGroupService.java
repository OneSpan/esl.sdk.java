package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.service.apiclient.AdhocGroupApiClient;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public final class AdhocGroupService {

  private final AdhocGroupApiClient adhocGroupApiClient;
  private final PackageService packageService;

  public AdhocGroupService(final AdhocGroupApiClient adhocGroupApiClient,
      final PackageService packageService) {
    this.adhocGroupApiClient = adhocGroupApiClient;
    this.packageService = packageService;
  }

  /**
   * create adhoc group
   *
   * @param packageId
   * @param adhocGroup
   * @return created adhoc group
   */
  public Role createAdhocGroup(final String packageId,
      final Role adhocGroup) {
    //RoleResources.addRole
    return this.packageService.addAdhocRole(new PackageId(packageId), adhocGroup);
  }

  public Role createAdhocGroupWithAdhoc(final String packageId,
      final Role adhocGroup) {
    //AdhocResources.addRole
    return this.adhocGroupApiClient.createAdhocGroup(packageId,
        Collections.singletonList(adhocGroup)).get(0);
  }

  public void deleteAdhocGroupWithAdhoc(final String packageId,
      final String groupRoleId) {
    //AdhocResources.deleteRole
    this.adhocGroupApiClient.deleteAdhocGroup(packageId, groupRoleId);
  }

  public void deleteAdhocGroup(final String packageId,
      final String roleId) {
    final Optional<Role> role = this.getRole(packageId, roleId);
    role.ifPresent(r -> this.packageService.deleteRole(new PackageId(packageId), r));
  }

  /**
   * update Adhoc Group Name remove group members from adhoc group
   *
   * @param packageId
   * @return
   */
  public Role updateAdhocGroup(final String packageId,
      final String roleId) {
    //RoleResources.updateRole
    final Optional<Role> role = this.getRole(packageId, roleId);

    return role.map(value -> this.packageService.updateAdhocRole(new PackageId(packageId), value))
        .orElse(null);
  }

  public Signer addAdhocGroupMember(final String packageId,
      final String roleId,
      final Signer adhocGroupMember) {
    //AdhocResource.updateRole
    final Optional<Role> role = this.getRole(packageId, roleId);
    role.ifPresent(value ->
    {
      value.addSigner(adhocGroupMember);
      this.adhocGroupApiClient.updateAdhocGroup(packageId, roleId,
          Collections.singletonList(value));
    });
    return adhocGroupMember;
  }

  public Signer deleteAdhocGroupMember(final String packageId,
      final String roleId,
      final Signer adhocGroupMember) {
    //RoleResource.updateRole
    final Optional<Role> role = this.getRole(packageId, roleId);

    role.ifPresent(value ->
    {
      final String signerId = adhocGroupMember.getId();
      value.getSigners().removeIf(signer -> StringUtils.equalsIgnoreCase(signerId, signer.getId()));
      this.packageService.updateAdhocRole(new PackageId(packageId), value);
    });

    return adhocGroupMember;
  }

  public Signer deleteAdhocGroupMemberWithAdhoc(final String packageId,
      final String roleId,
      final Signer adhocGroupMember) {
    //RoleResource.updateRole
    final Optional<Role> role = this.getRole(packageId, roleId);
    role.ifPresent(value ->
    {
      final String signerId = adhocGroupMember.getId();
      value.getSigners().removeIf(signer -> StringUtils.equalsIgnoreCase(signerId, signer.getId()));
      this.updateAdhocGroupMember(packageId, roleId, Collections.singletonList(value));
    });
    return adhocGroupMember;
  }

  /**
   * add group members to adhoc group update adhoc group members names/email
   *
   * @param packageId
   * @param roles
   * @return
   */
  public List<Role> updateAdhocGroupMember(final String packageId,
      final String groupRoleId,
      final List<Role> roles) {
    //adhocGroupResource.updateAdHocGroup()
    return this.adhocGroupApiClient.updateAdhocGroup(packageId, groupRoleId, roles);
  }


  private Optional<Role> getRole(final String packageId,
      final String roleId) {
    final List<Role> roles = this.packageService.getRoles(new PackageId(packageId));
    final Optional<Role> role = roles.stream().filter(r -> r.getId().equals(roleId)).findFirst();
    if (!role.isPresent()) {
      throw new EslException("Failed to retrieve Role with roleId=" + roleId);
    } else {
      return role;
    }
  }
}

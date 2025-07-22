package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Auth;
import com.silanis.esl.api.model.Delivery;
import com.silanis.esl.api.model.Group;
import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.service.apiclient.AdhocGroupApiClient;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public final class AdhocGroupService {
  private static final String AD_HOC_GROUP_SIGNER_TYPE = "AD_HOC_GROUP_SIGNER";
  private static final String EXTERNAL_SIGNER_TYPE = "EXTERNAL_SIGNER";
  private static final String AD_HOC_GROUP_MEMBER_TYPE = "AD_HOC_GROUP_MEMBER";

  private final AdhocGroupApiClient adhocGroupApiClient;
  private final PackageService packageService;

  public AdhocGroupService(final AdhocGroupApiClient adhocGroupApiClient,
      final PackageService packageService) {
    this.adhocGroupApiClient = adhocGroupApiClient;
    this.packageService = packageService;
  }

  public Role createAdhocGroup(final String packageId,
      final Role adhocGroup) {
    //AdhocResources.addRole
    return this.createAdhocGroup(packageId, Collections.singletonList(adhocGroup)).get(0);
  }

  public List<Role> createAdhocGroup(final String packageId,
      final List<Role> roles) {
    //AdhocResources.addRole
    return this.adhocGroupApiClient.createAdhocGroup(packageId, roles);
  }

  public void deleteAdhocGroup(final String packageId,
      final String groupRoleId) {
    //AdhocResources.deleteRole
    this.adhocGroupApiClient.deleteAdhocGroup(packageId, groupRoleId);
  }

  public List<Signer> addAdhocGroupMember(final String packageId,
      final String roleId,
      final List<Signer> adhocGroupMembers) {
    //AdhocResource.updateRole
    final Optional<Role> adhocGroup = this.getRole(packageId, roleId);
    adhocGroup.ifPresent(role ->
    {
      final List<Role> roles = addAdhocGroupMembersToAdhocGroup(adhocGroupMembers, role);
      this.adhocGroupApiClient.updateAdhocGroup(packageId, roleId,
          roles);
    });
    return adhocGroupMembers;
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

  /**
   * create adhoc group
   *
   * @param packageId
   * @param adhocGroup
   * @return created adhoc group
   */
  public Role createAdhocGroupAsRole(final String packageId,
      final Role adhocGroup) {
    //RoleResources.addRole
    return this.packageService.addAdhocRole(new PackageId(packageId), adhocGroup);
  }

  public void deleteAdhocGroupAsRole(final String packageId,
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
  public Role updateAdhocGroupAsRole(final String packageId,
      final String roleId) {
    //RoleResources.updateRole
    final Optional<Role> role = this.getRole(packageId, roleId);

    return role.map(value -> this.packageService.updateAdhocRole(new PackageId(packageId), value))
        .orElse(null);
  }

  public Signer deleteAdhocGroupMemberAsRole(final String packageId,
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

  public static Role buildAdhocGroup(final String adhocGroupName) {
    final Role adhocGroup = new Role();
    adhocGroup.setId(UUID.randomUUID().toString());
    adhocGroup.getSigners().add(buildAdhocSigner(adhocGroupName));
    return adhocGroup;
  }

  public static List<Role> addAdhocGroupMembersToAdhocGroup(final List<Signer> adhocGroupMembers,
      final Role adhocGroup) {
    return Stream.concat(adhocGroupMembers.stream().map(adhocGroupMember -> {
      final Role tempRole = new Role() // Create a new role to update the adhoc group
          .setSigners(Collections.singletonList(adhocGroupMember))
          .setId(adhocGroupMember.getId());

      final GroupMember groupMember = new GroupMember();
      groupMember.setUserId(adhocGroupMember.getId());
      groupMember.setMemberType(AD_HOC_GROUP_MEMBER_TYPE);
      adhocGroup.getSigners().get(0).getGroup().addMember(groupMember);
      return tempRole;
    }), Stream.of(adhocGroup)).collect(Collectors.toList());
  }

  public static Signer buildAdhocGroupMember(final String firstName,
      final String lastName,
      final String email) {
    final Signer adhocGroupMember = new Signer();
    adhocGroupMember.setFirstName(firstName);
    adhocGroupMember.setLastName(lastName);
    adhocGroupMember.setEmail(email);

    adhocGroupMember.setId(UUID.randomUUID().toString());
    adhocGroupMember.setSignerType(EXTERNAL_SIGNER_TYPE);
    adhocGroupMember.setDelivery(new Delivery());
    adhocGroupMember.setAuth(new Auth());

    final Date currentDate = new Date();
    adhocGroupMember.setCreated(currentDate);
    adhocGroupMember.setUpdated(currentDate);
    return adhocGroupMember;
  }

  private static Signer buildAdhocSigner(final String adhocGroupName) {
    final Signer signer = new Signer();
    signer.setFirstName(adhocGroupName);
    signer.setSignerType(AD_HOC_GROUP_SIGNER_TYPE);
    signer.setDelivery(new Delivery());
    final Date currentDate = new Date();
    signer.setCreated(currentDate);
    signer.setUpdated(currentDate);
    signer.setGroup(buildGroup(adhocGroupName, currentDate));
    return signer;
  }

  private static Group buildGroup(final String adhocGroupName,
      final Date createdDate) {
    final Group group = new Group();
    group.setName(adhocGroupName);
    group.setCreated(createdDate);
    group.setUpdated(createdDate);
    return group;
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

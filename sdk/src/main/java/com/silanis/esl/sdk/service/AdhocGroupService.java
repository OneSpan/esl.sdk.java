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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.silanis.esl.api.util.AdHocGroupUtils.AD_HOC_GROUP_MEMBER_TYPE;
import static com.silanis.esl.api.util.AdHocGroupUtils.AD_HOC_GROUP_SIGNER_TYPE;
import static com.silanis.esl.api.util.AdHocGroupUtils.EXTERNAL_SIGNER_TYPE;
import static com.silanis.esl.api.util.AdHocGroupUtils.SIGNER_TYPE;

public final class AdhocGroupService {
  private static final String OWNER_ROLE_TYPE = "SENDER";
  private static final String SIGNER_TYPE_OF_OWNER_ROLE = "ACCOUNT_SENDER";

  private final AdhocGroupApiClient adhocGroupApiClient;
  private final PackageService packageService;

  /**
   * Provides services for managing ad-hoc groups within a package.
   */
  public AdhocGroupService(final AdhocGroupApiClient adhocGroupApiClient,
      final PackageService packageService) {
    this.adhocGroupApiClient = adhocGroupApiClient;
    this.packageService = packageService;
  }

  /**
   * Creates an ad-hoc group within the specified package.
   *
   * @param packageId the ID of the package to add the ad-hoc group to
   * @param adhocGroup the Role object representing the ad-hoc group
   * @return new AdhocGroup created
   */
  public Role createAdhocGroup(final String packageId,
      final Role adhocGroup) {
    return this.createAdhocGroup(packageId, Collections.singletonList(adhocGroup)).get(0);
  }

  /**
   * Creates ad-hoc groups within the specified package.
   *
   * @param packageId the ID of the package to add the ad-hoc groups to
   * @param roles the list of Role objects representing the ad-hoc groups
   * @return list of newly created AdhocGroups
   */
  public List<Role> createAdhocGroup(final String packageId,
      final List<Role> roles) {
    return this.adhocGroupApiClient.createAdhocGroup(packageId, roles);
  }

  /**
   * Deletes an ad-hoc group from the specified package.
   *
   * @param packageId the ID of the package containing the ad-hoc group
   * @param groupRoleId the ID of the ad-hoc group role to delete
   */
  public void deleteAdhocGroup(final String packageId,
      final String groupRoleId) {
    this.adhocGroupApiClient.deleteAdhocGroup(packageId, groupRoleId);
  }

  /**
   * Adds members to an ad-hoc group within the specified package.
   *
   * @param packageId the ID of the package containing the ad-hoc group
   * @param transactionOwner role of the transaction owner
   * @param roleId the ID of the ad-hoc group role
   * @param adhocGroupMembers the list of Signer objects to add as group members
   * @return the list of added Signer objects
   */
  public List<Signer> addAdhocGroupMembers(final String packageId,
      final Role transactionOwner,
      final String roleId,
      final List<Signer> adhocGroupMembers) {
    final Optional<Role> adhocGroup = this.getRole(packageId, roleId);
    adhocGroup.ifPresent(role ->
    {
      final List<Role> roles = addAdhocGroupMembersToAdhocGroup(transactionOwner, adhocGroupMembers, role);
      this.adhocGroupApiClient.updateAdhocGroup(packageId, roleId,
          roles);
    });
    return adhocGroupMembers;
  }

  /**
   * Adds members to an ad-hoc group within the specified package.
   * This method is used when linking existing roles to an ad-hoc group.
   *
   * @param packageId the ID of the package containing the ad-hoc group
   * @param roleId the ID of the ad-hoc group role
   * @param adhocGroupLinkedMembers the list of GroupMember objects to add as linked members
   * @return the list of added GroupMember objects
   */
  public List<GroupMember> addAdhocGroupLinkedMembers(final String packageId,
      final String roleId,
      final List<GroupMember> adhocGroupLinkedMembers) {
    final Optional<Role> adhocGroup = this.getRole(packageId, roleId);
    adhocGroup.ifPresent(role ->
    {
      final Role updatedAdhocGroup = addAdhocGroupLinkedMembersToAdhocGroup(adhocGroupLinkedMembers, role);
      this.adhocGroupApiClient.updateAdhocGroup(packageId, roleId,
          Collections.singletonList(updatedAdhocGroup));
    });
    return adhocGroupLinkedMembers;
  }

  /**
   * Deletes a member from an ad-hoc group within the specified package.
   *
   * @param packageId        the ID of the package containing the ad-hoc group
   * @param roleId           the ID of the ad-hoc group role
   * @param adhocGroupMember the Signer object representing the member to delete
   * @return the deleted Signer object
   */
  public Signer deleteAdhocGroupMember(final String packageId,
      final String roleId,
      final Signer adhocGroupMember) {
    final Optional<Role> role = this.getRole(packageId, roleId);
    role.ifPresent(value ->
    {
      final String signerId = adhocGroupMember.getId();
      if (CollectionUtils.isNotEmpty(value.getSigners())
          && value.getSigners().get(0).getGroup() != null) {
        value.getSigners().get(0).getGroup().getMembers()
            .removeIf(member -> StringUtils.equalsIgnoreCase(signerId, member.getUserId()));
      }
      this.updateAdhocGroupMember(packageId, roleId, Collections.singletonList(value));
    });
    return adhocGroupMember;
  }

  /**
   * Updates the members of an ad-hoc group within the specified package.
   * For example, this can be used to update Adhoc Group Name
   *
   * @param packageId    the ID of the package containing the ad-hoc group
   * @param groupRoleId  the ID of the ad-hoc group role
   * @param roles        the list of Role objects representing the updated members
   * @return the list of updated Role objects
   */
  public List<Role> updateAdhocGroupMember(final String packageId,
      final String groupRoleId,
      final List<Role> roles) {
    return this.adhocGroupApiClient.updateAdhocGroup(packageId, groupRoleId, roles);
  }

  public Role getTransactionOwner(final String packageId) {
    final List<Role> currentRoles = this.packageService.getRoles(new PackageId(packageId));
    final Optional<Role> transactionOwner = currentRoles.stream()
        .filter(AdhocGroupService::isTransactionOwner)
        .min(Comparator.comparingInt(Role::getIndex));

    return transactionOwner.orElseThrow(() ->
        new EslException("No transaction owner found in package with ID: " + packageId)
    );
  }

  /**
   * Builds a new ad-hoc group Role with the specified group name.
   * The Role will have a unique ID and a single signer representing the group.
   *
   * @param adhocGroupName the name of the ad-hoc group
   * @return a new Role object representing the ad-hoc group
   */
  public static Role buildAdhocGroup(final String adhocGroupName) {
    final Role adhocGroup = new Role();
    adhocGroup.setId(UUID.randomUUID().toString());
    adhocGroup.getSigners().add(buildAdhocSigner(adhocGroupName));
    return adhocGroup;
  }

  /**
   * Adds members to an existing ad-hoc group Role.
   * Each Signer in the provided list is added as a member to the group.
   * Returns a list containing the updated Role and new Roles for each member.
   *
   * @param transactionOwner role of transaction owner
   * @param adhocGroupMembers the list of Signer objects to add as group members
   * @param adhocGroup the Role object representing the ad-hoc group
   * @return a list of Role objects including the updated group and new member roles
   */
  public static List<Role> addAdhocGroupMembersToAdhocGroup(final Role transactionOwner,
      final List<Signer> adhocGroupMembers,
      final Role adhocGroup) {

    final String transactionOwnerEmail = transactionOwner.getSigners().get(0).getEmail();
    final String transactionOwnerId = transactionOwner.getId();

    processTransactionOwnerIfAdhocGroupMember(adhocGroupMembers, adhocGroup, transactionOwnerEmail, transactionOwnerId);

    return Stream.concat(adhocGroupMembers.stream()
            .filter(adhocGroupMember -> !StringUtils.equalsIgnoreCase(transactionOwnerEmail, adhocGroupMember.getEmail()))
        .map(adhocGroupMember -> {
      final Role tempRole = new Role() // Create a new role to update the adhoc group
          .setSigners(Collections.singletonList(adhocGroupMember))
          .setId(adhocGroupMember.getId());

      if (CollectionUtils.isNotEmpty(adhocGroup.getSigners())
          && adhocGroup.getSigners().get(0).getGroup() != null) {
        final GroupMember groupMember = buildGroupMember(adhocGroupMember.getId(), AD_HOC_GROUP_MEMBER_TYPE);
        adhocGroup.getSigners().get(0).getGroup().addMember(groupMember);
      }
      return tempRole;
    }), Stream.of(adhocGroup)).collect(Collectors.toList());
  }

  public static Role addAdhocGroupLinkedMembersToAdhocGroup(final List<GroupMember> adhocGroupMembers,
      final Role adhocGroup) {
      if (CollectionUtils.isNotEmpty(adhocGroup.getSigners())
          && adhocGroup.getSigners().get(0).getGroup() != null) {
        adhocGroup.getSigners().get(0).getGroup().getMembers().addAll(adhocGroupMembers);
      }
      return adhocGroup;
  }

  /**
   * Builds a new Signer object representing an ad-hoc group member.
   *
   * @param firstName the first name of the group member
   * @param lastName the last name of the group member
   * @param email the email address of the group member
   * @return a new Signer object configured as an external signer and group member
   */
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

  /**
   * Builds a new GroupMember object to link an existing role to an ad-hoc group.
   * This method is used when linking an existing role to an ad-hoc group.
   *
   * @param roleIdToLink the ID of the role to link
   * @return a new GroupMember object configured with the specified role ID and member type
   */
  public static GroupMember buildAdhocGroupMemberToLinkExistingRole(final String roleIdToLink) {
    return buildGroupMember(roleIdToLink, SIGNER_TYPE);
  }

  public static boolean isTransactionOwner(final Role role) {
    return CollectionUtils.isNotEmpty(role.getSigners())
            && OWNER_ROLE_TYPE.equalsIgnoreCase(role.getType())
            && SIGNER_TYPE_OF_OWNER_ROLE.equalsIgnoreCase(role.getSigners().get(0).getSignerType());
  }

  /**
   * Builds a new GroupMember object to link an existing role to an ad-hoc group.
   *
   * @param roleId user Id
   * @param memberType   the type of the member (e.g., SIGNER_TYPE)
   * @return a new GroupMember object configured with the specified role ID and member type
   */
  private static GroupMember buildGroupMember(final String roleId,
      final String memberType) {
    final GroupMember adhocGroupMember = new GroupMember();

    adhocGroupMember.setUserId(roleId);
    adhocGroupMember.setMemberType(memberType);

    return adhocGroupMember;
  }

  /**
   * Builds a new Signer object representing an ad-hoc group.
   * The signer will have the specified group name, type set to AD_HOC_GROUP_SIGNER,
   * delivery information, creation and update timestamps, and an associated group.
   *
   * @param adhocGroupName the name of the ad-hoc group
   * @return a new Signer object configured as an ad-hoc group signer
   */
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

  /**
   * Builds a new Group object for the ad-hoc group.
   *
   * @param adhocGroupName the name of the ad-hoc group
   * @param createdDate the creation date for the group
   * @return a new Group object with the specified name and creation date
   */
  private static Group buildGroup(final String adhocGroupName,
      final Date createdDate) {
    final Group group = new Group();
    group.setName(adhocGroupName);
    group.setCreated(createdDate);
    group.setUpdated(createdDate);
    return group;
  }

  /**
   * Retrieves a Role from the specified package by its role ID.
   *
   * @param packageId the ID of the package containing the roles
   * @param roleId the ID of the role to retrieve
   * @return an Optional containing the Role if found, otherwise throws EslException
   */
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

  /**
   * Processes the transaction owner if they are a member of the ad-hoc group. If the transaction
   * owner's email matches any of the ad-hoc group members, a new GroupMember is created and added
   * to the ad-hoc group.
   *
   * @param adhocGroupMembers     the list of Signer objects representing ad-hoc group members
   * @param adhocGroup            the Role object representing the ad-hoc group
   * @param transactionOwnerEmail the email address of the transaction owner
   * @param transactionOwnerId    the ID of the transaction owner
   */
  private static void processTransactionOwnerIfAdhocGroupMember(final List<Signer> adhocGroupMembers,
      final Role adhocGroup,
      final String transactionOwnerEmail,
      final String transactionOwnerId) {
    adhocGroupMembers.stream().filter(
            adhocGroupMember -> StringUtils.equalsIgnoreCase(transactionOwnerEmail,
                adhocGroupMember.getEmail()))
        .forEach(adhocGroupMember -> {
          final GroupMember groupMember = buildGroupMember(transactionOwnerId,
              AD_HOC_GROUP_MEMBER_TYPE);
          addAdhocGroupLinkedMembersToAdhocGroup(Collections.singletonList(groupMember),
              adhocGroup);
        });
  }
}

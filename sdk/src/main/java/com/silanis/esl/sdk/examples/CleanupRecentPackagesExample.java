package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.Sender;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Deletes all packages, transactions, templates and invited/created senders
 * that were created or updated within the last {@value #CLEANUP_WINDOW_MINUTES}
 * minutes.  Intended to be the final example run in a test pass so that
 * transient test data is removed from the account.
 */
public class CleanupRecentPackagesExample extends SDKSample {

    public static final int CLEANUP_WINDOW_MINUTES = 1440;
    public static final int PAGE_SIZE = PageRequest.MAX_PAGE_SIZE;

    private static final PackageStatus[] PACKAGE_STATUSES = {
            PackageStatus.DRAFT,
            PackageStatus.SENT,
            PackageStatus.COMPLETED,
            PackageStatus.ARCHIVED,
            PackageStatus.DECLINED,
            PackageStatus.OPTED_OUT,
            PackageStatus.EXPIRED
    };

    public int deletedPackagesCount = 0;
    public int deletedTemplatesCount = 0;
    public int deletedSendersCount = 0;
    public int deletedGroupsCount = 0;
    public List<PackageId> deletedPackageIds = new ArrayList<PackageId>();
    public List<PackageId> deletedTemplateIds = new ArrayList<PackageId>();
    public List<String> deletedSenderIds = new ArrayList<String>();
    public List<GroupId> deletedGroupIds = new ArrayList<GroupId>();

    public static void main(String... args) {
        new CleanupRecentPackagesExample().run();
    }

    @Override
    public void execute() {
        Date to = new Date();
//        Date from = new Date(to.getTime() - CLEANUP_WINDOW_MINUTES * 60 * 1000L);
        LocalDate localDate = LocalDate.of(2024, 8, 1);
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(instant);

        for (PackageStatus status : PACKAGE_STATUSES) {
            deletePackagesUpdatedWithinRange(status, from, to);
        }

        deleteTemplatesUpdatedWithinRange(from);
        deleteSendersCreatedWithinRange(from);
        deleteGroupsCreatedOrUpdatedWithinRange();
    }

    private void deletePackagesUpdatedWithinRange(PackageStatus status, Date from, Date to) {
        PageRequest request = new PageRequest(1, PAGE_SIZE);
        while (true) {
            Page<DocumentPackage> page = eslClient.getPackageService()
                    .getUpdatedPackagesWithinDateRange(status, request, from, to);
            for (DocumentPackage pkg : page) {
                eslClient.getPackageService().deletePackage(pkg.getId());
                deletedPackageIds.add(pkg.getId());
                deletedPackagesCount++;
                System.out.println(deletedPackagesCount + " Deleted package " + pkg.getId());
            }
            if (!page.hasNextPage()) {
                break;
            }
            request = page.getNextRequest();
        }
        System.out.println("Deleted " + deletedPackagesCount + " packages");
    }

    private void deleteTemplatesUpdatedWithinRange(Date from) {
        PageRequest request = new PageRequest(1, PAGE_SIZE);
        while (true) {
            Page<DocumentPackage> page = eslClient.getPackageService().getTemplates(request);
            for (DocumentPackage template : page) {
                Date updatedDate = template.getUpdatedDate();
                if (updatedDate != null && !updatedDate.before(from)) {
                    eslClient.getPackageService().deletePackage(template.getId());
                    deletedTemplateIds.add(template.getId());
                    deletedTemplatesCount++;
                    System.out.println(deletedTemplatesCount + " Deleted template " + template.getId());
                }
            }
            if (!page.hasNextPage()) {
                break;
            }
            request = page.getNextRequest();
        }
        System.out.println("Deleted " + deletedTemplatesCount + " templates");
    }

    private void deleteGroupsCreatedOrUpdatedWithinRange() {
        List<Group> groups = eslClient.getGroupService().getMyGroups();
        for (Group group : groups) {
            if ((group.getName() != null && group.getName().startsWith(GroupManagementExample.GROUP_NAME_PREFIX)) ||
                    (group.getEmail() != null && group.getEmail().startsWith(GroupManagementExample.EMAIL))) {
                eslClient.getGroupService().deleteGroup(group.getId());
                deletedGroupIds.add(group.getId());
                deletedGroupsCount++;
                System.out.println(deletedGroupsCount + " Deleted group " + group.getId());
            }
        }
        System.out.println("Deleted " + deletedGroupsCount + " groups");
    }

    private void deleteSendersCreatedWithinRange(Date from) {
        // First pass: collect all candidate sender IDs across every page into a Set.
        // Using a Set deduplicates IDs that re-appear on later pages because a prior
        // deletion attempt silently failed and left the sender in the account.
        Set<String> candidateIds = new HashSet<String>();
        int pageIndex = 0;
        while (true) {
            Map<String, Sender> senders = eslClient.getAccountService()
                    .getSenders(Direction.ASCENDING, new PageRequest(pageIndex * PAGE_SIZE + 1, PAGE_SIZE));
            for (Sender sender : senders.values()) {
                if (senderUID.equals(sender.getId())) {
                    continue; // never delete the account owner running the tests
                }
                Date createdDate = sender.getCreated();
                if (createdDate != null && !createdDate.before(from)) {
                    candidateIds.add(sender.getId());
                    System.out.println("Candidate sender " + sender.getId() + " in page " + pageIndex + " Added");
                }
            }
            if (senders.size() < PAGE_SIZE) {
                break;
            }
            pageIndex++;
        }

        // Second pass: attempt each deletion exactly once regardless of whether
        // a previous page already saw the same sender.
        for (String senderId : candidateIds) {
            eslClient.getAccountService().deleteSender(senderId);
            deletedSenderIds.add(senderId);
            deletedSendersCount++;
            System.out.println(deletedSendersCount + " Deleted sender " + senderId);
        }
        System.out.println("Deleted " + deletedSendersCount + " senders");
    }
}

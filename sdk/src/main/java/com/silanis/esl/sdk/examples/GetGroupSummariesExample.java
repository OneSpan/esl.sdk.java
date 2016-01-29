package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.GroupSummary;

import java.util.List;

/**
 * Created by schoi on 1/28/15.
 */
public class GetGroupSummariesExample extends SDKSample {

    public List<GroupSummary> retrievedGroupSummaries;

    public static void main( String... args ) {
        new GetGroupSummariesExample().run();
    }

    public void execute() {
        retrievedGroupSummaries = eslClient.getGroupService().getGroupSummaries();

        for(GroupSummary groupSummary : retrievedGroupSummaries) {
            System.out.format("GroupSummary id : %s, email : %s, name : %s%n", groupSummary.getId(), groupSummary.getEmail(), groupSummary.getName());
        }
        System.out.println("Total : " + retrievedGroupSummaries.size());
    }
}

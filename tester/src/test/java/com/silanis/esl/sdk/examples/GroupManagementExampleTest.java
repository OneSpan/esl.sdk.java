package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by dave on 05/05/14.
 */
public class GroupManagementExampleTest {
    @Test
    public void verifyResult() {
        GroupManagementExample example = new GroupManagementExample(Props.get());
        example.run();

        assertThat("Group 1 was not added properly", getGroupsId(example.allGroupsBeforeDelete).contains(example.createdGroup1.getId()), is(true));
        assertThat("Group 2 was not added properly", getGroupsId(example.allGroupsBeforeDelete).contains(example.createdGroup2.getId()), is(true));
        assertThat("Group 3 was not added properly", getGroupsId(example.allGroupsBeforeDelete).contains(example.createdGroup3.getId()), is(true));

        assertThat("Group 1 was not added properly", getGroupsId(example.allGroupsAfterDelete).contains(example.createdGroup1.getId()), is(true));
        assertThat("Group 2 was not deleted properly", getGroupsId(example.allGroupsAfterDelete).contains(example.createdGroup2.getId()), is(false));
        assertThat("Group 3 was not added properly", getGroupsId(example.allGroupsAfterDelete).contains(example.createdGroup3.getId()), is(true));

        assertThat("Group was not updated properly, member 1 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email2));
        assertThat("Group was not updated properly, member 1 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email3));
        assertThat("Group was not updated properly, member 1 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email4));

    }


    private List<GroupId> getGroupsId(Collection<Group> groups){
        List<GroupId> groupsId = new ArrayList<GroupId>();
        for (Group group : groups){
            groupsId.add(group.getId());
        }
        return groupsId;
    }
}

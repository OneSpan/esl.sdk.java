package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.internal.EslServerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by dave on 05/05/14.
 */
public class GroupManagementExampleTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void verifyResult() {
        GroupManagementExample example = new GroupManagementExample(Props.get());
        example.run();

        assertThat("Group 1 was not added properly", example.createdGroup1.getId(), is(example.retrievedGroup1.getId()));
        assertThat("Group 2 was not added properly", example.createdGroup2.getId(), is(example.retrievedGroup2.getId()));
        assertThat("Group 3 was not added properly", example.createdGroup3.getId(), is(example.retrievedGroup3.getId()));

        exception.expect(EslServerException.class);
        example.getEslClient().getGroupService().getGroup(example.createdGroup2.getId());

        assertThat("Group was not updated properly, member 1 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email2));
        assertThat("Group was not updated properly, member 2 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email3));
        assertThat("Group was not updated properly, member 3 is missing", example.groupMemberEmailsAfterUpdate.contains(example.email4));

    }


    private List<GroupId> getGroupsId(Collection<Group> groups){
        List<GroupId> groupsId = new ArrayList<GroupId>();
        for (Group group : groups){
            groupsId.add(group.getId());
        }
        return groupsId;
    }
}

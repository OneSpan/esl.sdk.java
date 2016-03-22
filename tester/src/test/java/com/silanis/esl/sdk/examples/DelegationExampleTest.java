package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DelegationUser;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by schoi on 3/23/15.
 */
public class DelegationExampleTest {

    @Test
    public void verifyResult() {
        DelegationExample example = new DelegationExample();
        example.run();

        assertThat("Sender1's email was not invited correctly.", example.retrievedSender1.getEmail(), is(example.email1));
        assertThat("Sender2's email was not invited correctly.", example.retrievedSender2.getEmail(), is(example.email2));
        assertThat("Sender3's email was not invited correctly.", example.retrievedSender3.getEmail(), is(example.email3));

        assertThat("Delegation users were not added correctly.", example.delegationUserListAfterAdding.size(), is(3));
        assertTrue("Delegation users were not added correctly.", assertContainDelegationUser(example.delegationUserListAfterAdding, example.delegationUser1));
        assertTrue("Delegation users were not added correctly.", assertContainDelegationUser(example.delegationUserListAfterAdding, example.delegationUser2));
        assertTrue("Delegation users were not added correctly.", assertContainDelegationUser(example.delegationUserListAfterAdding, example.delegationUser3));

        assertThat("Delegation user was not removed correctly.", example.delegationUserListAfterRemoving.size(), is(2));
        assertTrue("Delegation users were not removed correctly.", assertContainDelegationUser(example.delegationUserListAfterRemoving, example.delegationUser1));
        assertFalse("Delegation users were not removed correctly.", assertContainDelegationUser(example.delegationUserListAfterRemoving, example.delegationUser2));
        assertTrue("Delegation users were not removed correctly.", assertContainDelegationUser(example.delegationUserListAfterRemoving, example.delegationUser3));

        assertThat("Delegation users were not updated correctly.", example.delegationUserListAfterUpdating.size(), is(6));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser4));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser5));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser6));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser7));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser8));
        assertTrue("Delegation users were not updated correctly.", assertContainDelegationUser(example.delegationUserListAfterUpdating, example.delegationUser9));

        assertThat("Delegation users were not cleared correctly.", example.delegationUserListAfterClearing.size(), is(0));
    }

    private boolean assertContainDelegationUser(List<DelegationUser> delegationUserList, DelegationUser delegationUser) {
        for(DelegationUser delegationUserToCompare : delegationUserList) {
            if(delegationUserToCompare.getEmail().equals(delegationUser.getEmail())) {
                assertThat(delegationUserToCompare.getId(), is(delegationUser.getId()));
                assertThat(delegationUserToCompare.getFirstName(), is(delegationUser.getFirstName()));
                assertThat(delegationUserToCompare.getLastName(), is(delegationUser.getLastName()));
                return true;
            }
        }
        return false;
    }
}

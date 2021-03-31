package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 2/18/15.
 */
public class EslEnumerationTest {

    @Test
    public void testAuthenticationMethod() {
        for(AuthenticationMethod authenticationMethod : AuthenticationMethod.values()) {
            assertThat(authenticationMethod.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(AuthenticationMethod.CHALLENGE.ordinal(), is(1));
        assertThat(AuthenticationMethod.SMS.name(), is("SMS"));
    }

    @Test
    public void testBasePackageType() {
        for(BasePackageType basePackageType : BasePackageType.values()) {
            assertThat(basePackageType.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(BasePackageType.TEMPLATE.ordinal(), is(1));
        assertThat(BasePackageType.LAYOUT.name(), is("LAYOUT"));
    }

    @Test
    public void testFieldStyle() {
        for(FieldStyle fieldStyle : FieldStyle.values()) {
            assertThat(fieldStyle.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(FieldStyle.BOUND_NAME.ordinal(), is(1));
        assertThat(FieldStyle.DROP_LIST.name(), is("DROP_LIST"));
        assertThat(FieldStyle.BOUND_COMPANY.name(), is("BOUND_COMPANY"));
        assertThat(FieldStyle.BOUND_DATE.name(), is("BOUND_DATE"));
        assertThat(FieldStyle.BOUND_TITLE.name(), is("BOUND_TITLE"));
        assertThat(FieldStyle.BOUND_NAME.name(), is("BOUND_NAME"));
    }

    @Test
    public void testFieldType() {
        for(FieldType fieldType : FieldType.values()) {
            assertThat(fieldType.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(FieldType.SIGNATURE.ordinal(), is(0));
        assertThat(FieldType.INPUT.name(), is("INPUT"));
    }

    @Test
    public void testGroupMemberType() {
        for(GroupMemberType groupMemberType : GroupMemberType.values()) {
            assertThat(groupMemberType.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(GroupMemberType.REGULAR.ordinal(), is(0));
        assertThat(GroupMemberType.MANAGER.name(), is("MANAGER"));
    }

    @Test
    public void testKnowledgeBasedAuthenticationStatus() {
        for(KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus : KnowledgeBasedAuthenticationStatus.values()) {
            assertThat(knowledgeBasedAuthenticationStatus.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED.ordinal(), is(0));
        assertThat(KnowledgeBasedAuthenticationStatus.PASSED.name(), is("PASSED"));
    }

    @Test
    public void testMessageStatus() {
        for(MessageStatus messageStatus : MessageStatus.values()) {
            assertThat(messageStatus.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(MessageStatus.NEW.ordinal(), is(0));
        assertThat(MessageStatus.READ.name(), is("READ"));
    }

    @Test
    public void testNotificationEvent() {
        for(NotificationEvent notificationEvent : NotificationEvent.values()) {
            assertThat(notificationEvent.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(NotificationEvent.PACKAGE_ACTIVATE.ordinal(), is(0));
        assertThat(NotificationEvent.PACKAGE_COMPLETE.name(), is("PACKAGE_COMPLETE"));
    }

    @Test
    public void testPackageStatus() {
        for(PackageStatus packageStatus : PackageStatus.values()) {
            assertThat(packageStatus.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(PackageStatus.DRAFT.ordinal(), is(0));
        assertThat(PackageStatus.SENT.name(), is("SENT"));
    }

    @Test
    public void testRequirementStatus() {
        for(RequirementStatus requirementStatus : RequirementStatus.values()) {
            assertThat(requirementStatus.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(RequirementStatus.INCOMPLETE.ordinal(), is(0));
        assertThat(RequirementStatus.REJECTED.name(), is("REJECTED"));
    }

    @Test
    public void testSenderStatus() {
        for(SenderStatus senderStatus : SenderStatus.values()) {
            assertThat(senderStatus.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(SenderStatus.INVITED.ordinal(), is(0));
        assertThat(SenderStatus.ACTIVE.name(), is("ACTIVE"));
    }

    @Test
    public void testSenderType() {
        for(SenderType senderType : SenderType.values()) {
            assertThat(senderType.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(SenderType.REGULAR.ordinal(), is(0));
        assertThat(SenderType.MANAGER.name(), is("MANAGER"));
    }

    @Test
    public void testSignatureStyle() {
        for(SignatureStyle signatureStyle : SignatureStyle.values()) {
            assertThat(signatureStyle.toString(), is(not(isEmptyOrNullString())));
        }
        assertThat(SignatureStyle.ACCEPTANCE.ordinal(), is(0));
        assertThat(SignatureStyle.HAND_DRAWN.name(), is("HAND_DRAWN"));
    }
}

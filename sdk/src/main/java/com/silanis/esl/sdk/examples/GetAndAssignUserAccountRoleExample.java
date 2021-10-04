package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountRole;
import com.silanis.esl.sdk.UserAccountRole;

import java.util.ArrayList;
import java.util.List;

public class GetAndAssignUserAccountRoleExample extends SDKSample {
    String userId = "dummyUserId";
    List<UserAccountRole> userAccountRoleList = new ArrayList<UserAccountRole>();
    public static void main(String... args) {
        new GetAndAssignUserAccountRoleExample().run();
    }

    public void execute() {
        List<AccountRole> accountRoles = eslClient.getAccountService().getAccountRoles();
        List<UserAccountRole> userAccountRoles = eslClient.getAccountService().getAssignedAccountRoles(userId);

        for(UserAccountRole userAccountRole: userAccountRoles){
            userAccountRole.getAccountRoles().addAll(accountRoles);
            UserAccountRole assignedUserAccountRole = eslClient.getAccountService().assignAccountRoleToUser(userId, userAccountRole);
            System.out.println(assignedUserAccountRole);
            userAccountRoleList.add(assignedUserAccountRole);
        }
    }
}
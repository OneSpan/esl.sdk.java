package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.AccountMember;

public class AccountMemberConverter {

    private User apiUser = null;
    private AccountMember sdkAccountMember = null;

    public AccountMemberConverter( User apiUser ) {
        this.apiUser = apiUser;
    }

    public AccountMemberConverter( AccountMember sdkAccountMember ) {
        this.sdkAccountMember = sdkAccountMember;
    }

    public User toAPIUser() {
        if ( sdkAccountMember != null ) {
            User result = new User();

            result.setAddress( new AddressConverter( sdkAccountMember.getAddress() ).toAPIAddress() );
            result.setCompany( sdkAccountMember.getCompany() );
            result.setEmail( sdkAccountMember.getEmail() );
            result.setFirstName( sdkAccountMember.getFirstName() );
            result.setLastName( sdkAccountMember.getLastName() );
            result.setTitle( sdkAccountMember.getTitle() );
            result.setLanguage( sdkAccountMember.getLanguage() );
            result.setPhone( sdkAccountMember.getPhoneNumber() );

            return result;
        } else {
            return apiUser;
        }
    }

    public AccountMember toSDKAccountMember() {
        if ( apiUser != null ) {
            AccountMember result = new AccountMember();

            result.setAddress( new AddressConverter( apiUser.getAddress() ).toSDKAddress() );
            result.setCompany( apiUser.getCompany() );
            result.setEmail( apiUser.getEmail() );
            result.setFirstName( apiUser.getFirstName() );
            result.setLastName( apiUser.getLastName() );
            result.setTitle( apiUser.getTitle() );
            result.setLanguage( apiUser.getLanguage() );
            result.setPhoneNumber( apiUser.getPhone() );

            return result;
        } else {
            return sdkAccountMember;
        }
    }
}

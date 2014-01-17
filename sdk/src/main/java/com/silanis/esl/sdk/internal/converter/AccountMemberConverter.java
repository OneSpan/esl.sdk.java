package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.AccountMember;

public class AccountMemberConverter {

    private Optional<User> optionalUser;
    private Optional<AccountMember> optionalAccountMember;

    public AccountMemberConverter( User apiUser ) {
        optionalUser = Optional.of( apiUser );
        optionalAccountMember = Optional.absent();
    }

    public AccountMemberConverter( AccountMember sdkAccountMember ) {
        optionalUser = Optional.absent();
        optionalAccountMember = Optional.of( sdkAccountMember );
    }

    public User toAPIUser() {
        if ( optionalAccountMember.isPresent()) {

            User result = new User();

            if ( optionalAccountMember.get().getAddress() != null ) {
                result.setAddress( new AddressConverter( optionalAccountMember.get().getAddress() ).toAPIAddress() );
            }
            result.setCompany( optionalAccountMember.get().getCompany() );
            result.setEmail( optionalAccountMember.get().getEmail() );
            result.setFirstName( optionalAccountMember.get().getFirstName() );
            result.setLastName( optionalAccountMember.get().getLastName() );
            result.setTitle( optionalAccountMember.get().getTitle() );
            result.setLanguage( optionalAccountMember.get().getLanguage() );
            result.setPhone( optionalAccountMember.get().getPhoneNumber() );

            return result;
        } else {
            return optionalUser.get();
        }
    }

    public AccountMember toSDKAccountMember() {
        if ( optionalUser.isPresent() ) {
            AccountMember result = new AccountMember();

            if ( optionalUser.get().getAddress() != null ) {
                result.setAddress( new AddressConverter( optionalUser.get().getAddress() ).toSDKAddress() );
            }
            result.setCompany( optionalUser.get().getCompany() );
            result.setEmail( optionalUser.get().getEmail() );
            result.setFirstName( optionalUser.get().getFirstName() );
            result.setLastName( optionalUser.get().getLastName() );
            result.setTitle( optionalUser.get().getTitle() );
            result.setLanguage( optionalUser.get().getLanguage() );
            result.setPhoneNumber( optionalUser.get().getPhone() );

            return result;
        } else {
            return optionalAccountMember.get();
        }
    }
}

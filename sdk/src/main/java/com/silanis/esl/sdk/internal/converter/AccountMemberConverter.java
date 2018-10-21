package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.AccountMember;

public class AccountMemberConverter {

    private Optional<Sender> optionalSender;
    private Optional<AccountMember> optionalAccountMember;

    public AccountMemberConverter( Sender apiSender ) {
        optionalSender = Optional.of( apiSender );
        optionalAccountMember = Optional.absent();
    }

    public AccountMemberConverter( AccountMember sdkAccountMember ) {
        optionalSender = Optional.absent();
        optionalAccountMember = Optional.of( sdkAccountMember );
    }

    public Sender toAPISender() {
        if ( optionalAccountMember.isPresent()) {

            Sender result = new Sender();

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
            if ( optionalAccountMember.get().getTimezoneId() != null ) {
                result.setTimezoneId(optionalAccountMember.get().getTimezoneId());
            }
            if (optionalAccountMember.get().getStatus().isPresent()) {
                result.setStatus( new SenderStatusConverter(optionalAccountMember.get().getStatus().get()).toAPISenderStatus() );
            }

            return result;
        } else {
            return optionalSender.get();
        }
    }

    public AccountMember toSDKAccountMember() {
        if ( optionalSender.isPresent() ) {
            AccountMember result = new AccountMember();

            if ( optionalSender.get().getAddress() != null ) {
                result.setAddress( new AddressConverter( optionalSender.get().getAddress() ).toSDKAddress() );
            }
            result.setCompany(optionalSender.get().getCompany() );
            result.setEmail( optionalSender.get().getEmail() );
            result.setFirstName( optionalSender.get().getFirstName() );
            result.setLastName( optionalSender.get().getLastName() );
            result.setTitle( optionalSender.get().getTitle() );
            result.setLanguage( optionalSender.get().getLanguage() );
            result.setPhoneNumber( optionalSender.get().getPhone() );
            result.setTimezoneId( optionalSender.get().getTimezoneId() );
            result.setStatus( new SenderStatusConverter( optionalSender.get().getStatus() ).toSDKSenderStatus() );

            return result;
        } else {
            return optionalAccountMember.get();
        }
    }
}

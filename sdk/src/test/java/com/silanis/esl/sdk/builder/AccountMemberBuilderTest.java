package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Address;
import com.silanis.esl.sdk.SenderStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountMemberBuilderTest {
    @Test
    public void withSpecifiedValues() {
        Address address = AddressBuilder.newAddress().build();
        AccountMemberBuilder builder = AccountMemberBuilder.newAccountMember( "email" )
                .withAddress( address )
                .withTitle( "title" )
                .withFirstName( "firstName" )
                .withLastName( "lastName" )
                .withCompany( "company" )
                .withLanguage( "language" )
                .withStatus(SenderStatus.ACTIVE)
                .withPhoneNumber("phoneNumber")
                .withTimezoneId( "Canada/Mountain" );

        AccountMember result = builder.build();

        assertThat( "build returned a null object", result, is( Matchers.notNullValue() ) );
        assertThat( "email was not set correctly", result.getEmail(), is( equalTo( "email" ) ) );
        assertThat( "address was not set correctly", result.getAddress(), is( equalTo( address ) ) );
        assertThat( "title was not set correctly", result.getTitle(), is( equalTo( "title" ) ) );
        assertThat( "first name was not set correctly", result.getFirstName(), is( equalTo( "firstName" ) ) );
        assertThat( "last name was not set correctly", result.getLastName(), is( equalTo( "lastName" ) ) );
        assertThat( "company was not set correctly", result.getCompany(), is( equalTo( "company" ) ) );
        assertThat( "language was not set correctly", result.getLanguage(), is( equalTo( "language" ) ) );
        assertThat( "phoneNumber was not set correctly", result.getPhoneNumber(), is( equalTo( "phoneNumber" ) ) );
        assertThat( "sender status was not set", result.getStatus().isPresent(), is (true));
        assertThat( "sender status was not set correctly", result.getStatus().get(), is( equalTo( SenderStatus.ACTIVE)));
        assertThat( "timezone id was not set correctly", result.getTimezoneId(), is( equalTo( "Canada/Mountain" ) ) );
    }
}

package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CompanyConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Company sdkCompany = null;
    private com.silanis.esl.api.model.Company apiCompany = null;
    private CompanyConverter converter;

    private static final String CO_NAME = "company_name";
    private static final String CO_ID = "company_id";
    private static final Map<String, Object> CO_DATA = ImmutableMap.<String, Object>of("company_data_0_key", "company_data_0_value");
    private static final String CO_ADDR_ADDR1 = "company_address_address1";
    private static final String CO_ADDR_ADDR2 = "company_address_address2";
    private static final String CO_ADDR_CITY = "company_address_city";
    private static final String CO_ADDR_COUNTRY = "company_address_country";
    private static final String CO_ADDR_STATE = "company_address_state";
    private static final String CO_ADDR_ZIP = "company_address_zipcode";


    @Test
    public void convertNullSDKToAPI() {
        sdkCompany = null;
        converter = new CompanyConverter(sdkCompany);

        assertThat(converter.toAPICompany(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiCompany = null;
        converter = new CompanyConverter(apiCompany);

        assertThat( converter.toSDKCompany(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkCompany = null;
        converter = new CompanyConverter(sdkCompany);

        assertThat( converter.toSDKCompany(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiCompany = null;
        converter = new CompanyConverter(apiCompany);

        assertThat(converter.toAPICompany(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkCompany = createTypicalSDKCompany();
        converter = new CompanyConverter( sdkCompany );

        com.silanis.esl.sdk.Company company = converter.toSDKCompany();
        assertThat(company, is( notNullValue() ) );
        assertThat(company, is( equalTo( company ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiCompany = createTypicalAPICompany();
        converter = new CompanyConverter( apiCompany );

        com.silanis.esl.api.model.Company company = converter.toAPICompany();

        assertThat( company, is( notNullValue() ) );
        assertThat( company, is( equalTo( company ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiCompany = createTypicalAPICompany();
        converter = new CompanyConverter( apiCompany );

        com.silanis.esl.sdk.Company company = converter.toSDKCompany();

        assertThat(company, is( notNullValue() ) );
        assertThat(company.getId(), is(equalTo(CO_ID)));
        assertThat(company.getAddress().getAddress1(), is(equalTo(CO_ADDR_ADDR1)));

    }

    @Test
    public void convertSDKToAPI() {
        sdkCompany = createTypicalSDKCompany();
        converter = new CompanyConverter( sdkCompany );
        com.silanis.esl.api.model.Company company = converter.toAPICompany();

        assertThat(company, is( notNullValue() ) );
        assertThat(company.getId(), is(equalTo(CO_ID)));
        assertThat(company.getAddress().getAddress1(), is(equalTo(CO_ADDR_ADDR1)));
    }

    private com.silanis.esl.sdk.Company createTypicalSDKCompany() {

        com.silanis.esl.sdk.Company company = new com.silanis.esl.sdk.Company();

        company.setId(CO_ID);
        company.setName(CO_NAME);
        company.setData(CO_DATA);
        com.silanis.esl.sdk.Address address = new com.silanis.esl.sdk.Address();
        address.setAddress1(CO_ADDR_ADDR1);
        address.setAddress2(CO_ADDR_ADDR2);
        address.setCity(CO_ADDR_CITY);
        address.setCountry(CO_ADDR_COUNTRY);
        address.setState(CO_ADDR_STATE);
        address.setZipCode(CO_ADDR_ZIP);
        company.setAddress(address);

        return company;
    }

    private com.silanis.esl.api.model.Company createTypicalAPICompany() {

        com.silanis.esl.api.model.Company company = new com.silanis.esl.api.model.Company();

        company.setId(CO_ID);
        company.setName(CO_NAME);
        company.setData(CO_DATA);
        com.silanis.esl.api.model.Address address = new com.silanis.esl.api.model.Address();
        address.setAddress1(CO_ADDR_ADDR1);
        address.setAddress2(CO_ADDR_ADDR2);
        address.setCity(CO_ADDR_CITY);
        address.setCountry(CO_ADDR_COUNTRY);
        address.setState(CO_ADDR_STATE);
        address.setZipcode(CO_ADDR_ZIP);
        company.setAddress(address);

        return company;
    }
}

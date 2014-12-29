package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.internal.converter.PackageStatusConverter;
import java.util.Properties;
import org.joda.time.DateTime;

/**
 * Created by mina on 29/12/14.
 */
public class GetPackagesCompletedWithinDateRangeExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        // get the packages completed today
        String startDateRange = new DateTime().toString();
        String endDateRange = new DateTime().toString();

        Page<DocumentPackage> resultPage = eslClient.getPackageService().getPackagesUpdatedWithinDateRange(new PackageStatusConverter(PackageStatus.COMPLETED).toAPIPackageStatus(), new PageRequest(1), startDateRange, endDateRange);

        System.out.println(resultPage.getNumberOfElements());
    }
}

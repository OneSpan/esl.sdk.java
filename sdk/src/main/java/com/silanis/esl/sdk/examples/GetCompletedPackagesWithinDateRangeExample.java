package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.internal.converter.PackageStatusConverter;
import org.joda.time.DateTime;

import java.util.Properties;

/**
 * Created by mina on 29/12/14.
 */
public class GetCompletedPackagesWithinDateRangeExample extends SDKSample {

    public static void main( String... args ) {
        new GetCompletedPackagesWithinDateRangeExample( Props.get() ).run();
    }

    public GetCompletedPackagesWithinDateRangeExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
              properties.getProperty( "api.url" ),
              properties.getProperty( "1.email" ) );
    }

    public GetCompletedPackagesWithinDateRangeExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
    }

    @Override
    public void execute() {
        // get the packages completed today
        String startDateRange = new DateTime().toString();
        String endDateRange = new DateTime().toString();

        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.DRAFT, getNumberOfPackageByPackageStatus(PackageStatus.DRAFT, startDateRange, endDateRange));
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.SENT, getNumberOfPackageByPackageStatus(PackageStatus.SENT, startDateRange, endDateRange));
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.DECLINED, getNumberOfPackageByPackageStatus(PackageStatus.DECLINED, startDateRange, endDateRange));
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.ARCHIVED, getNumberOfPackageByPackageStatus(PackageStatus.ARCHIVED, startDateRange, endDateRange));
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.COMPLETED, getNumberOfPackageByPackageStatus(PackageStatus.COMPLETED, startDateRange, endDateRange));
    }

    private int getNumberOfPackageByPackageStatus(PackageStatus packageStatus, String startDateRange, String endDateRange) {
        Page<DocumentPackage> resultPage = eslClient.getPackageService().getUpdatedPackagesWithinDateRange(new PackageStatusConverter(packageStatus).toAPIPackageStatus(), new PageRequest(1), startDateRange, endDateRange);
        return resultPage.getNumberOfElements();
    }
}

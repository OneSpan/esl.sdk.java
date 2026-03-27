package com.silanis.esl.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.service.PackageService;

/**
 * @author <a href="mailto:maryna.pashkouskaya@onespan.com">Maryna Pashkouskay</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class PackageServiceTest {

    private PackageService packageService;

    @Mock
    private RestClient clientMock;

    @Before
    public void setup() {
        packageService = new PackageService(clientMock, "http://baseurl");
    }

    @Test
    public void testBuildPackagePath() throws Exception {
        String transactionId = "12345";
        String expected = new UrlTemplate("http://baseurl").urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", transactionId).build();
        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("buildPackagePath", String.class);
        buildPackagePathMethod.setAccessible(true);

        String result = (String) buildPackagePathMethod.invoke(packageService, transactionId);

        assertEquals(expected, result);
    }

    @Test
    public void testHasLanguageChangedTrue() throws Exception {
        Package existingPackage = new Package();
        existingPackage.setLanguage("en");
        Package updatedPackage = new Package();
        updatedPackage.setLanguage("fr");

        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("hasLanguageChanged", Package.class,  Package.class);
        buildPackagePathMethod.setAccessible(true);

        boolean result = (boolean) buildPackagePathMethod.invoke(packageService, existingPackage, updatedPackage);
        assertTrue(result);
    }

    @Test
    public void testHasLanguageChangedFalse() throws Exception {
        Package existingPackage = new Package();
        existingPackage.setLanguage("en");
        Package updatedPackage = new Package();
        updatedPackage.setLanguage("en");

        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("hasLanguageChanged", Package.class,  Package.class);
        buildPackagePathMethod.setAccessible(true);

        boolean result = (boolean) buildPackagePathMethod.invoke(packageService, existingPackage, updatedPackage);
        assertFalse(result);
    }

    @Test
    public void testHasLanguageChangedWhenCurrentLanguageNullThenFalse() throws Exception {
        Package existingPackage = new Package();
        existingPackage.setLanguage("en");
        Package updatedPackage = new Package();
        updatedPackage.setLanguage(null);

        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("hasLanguageChanged", Package.class,  Package.class);
        buildPackagePathMethod.setAccessible(true);

        boolean result = (boolean) buildPackagePathMethod.invoke(packageService, existingPackage, updatedPackage);
        assertFalse(result);
    }

    // @Test
    // public void testGetPackageWithoutException_success() throws Exception {
    //     Package pkg = mock(Package.class);
    //     doReturn(pkg).when(packageService).getApiPackage("txid");
    //     Optional<Package> result = packageService.getPackageWithoutException("txid");
    //     assertTrue(result.isPresent());
    //     assertEquals(pkg, result.get());
    // }
    //
    // @Test
    // public void testGetPackageWithoutException_failure() throws Exception {
    //     doThrow(new EslServerException("fail")).when(packageService).getApiPackage("txid");
    //     Optional<Package> result = packageService.getPackageWithoutException("txid");
    //     assertFalse(result.isPresent());
    // }
    //
    // @Test
    // public void testLocalizeConsent_success() {
    //     PackageId packageId = new PackageId("pid");
    //     String language = "en";
    //     PackageUpdateWorkflowResult result = new PackageUpdateWorkflowResult();
    //     ConsentLocalizationData data = mock(ConsentLocalizationData.class);
    //     doReturn(data).when(packageService).localizeDefaultConsentDocument(any(ConsentLocalizationPayload.class), eq(packageId));
    //     packageService.localizeConsent(packageId, language, result);
    //     assertNotNull(result.getConsentInfo());
    //     assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getConsentInfo().getStatus());
    // }
    //
    // @Test
    // public void testLocalizeConsent_failure() {
    //     PackageId packageId = new PackageId("pid");
    //     String language = "en";
    //     PackageUpdateWorkflowResult result = new PackageUpdateWorkflowResult();
    //     doThrow(new RuntimeException("fail")).when(packageService).localizeDefaultConsentDocument(any(ConsentLocalizationPayload.class), eq(packageId));
    //     packageService.localizeConsent(packageId, language, result);
    //     assertNotNull(result.getConsentInfo());
    //     assertEquals(PackageUpdateWorkflowResult.Status.FAILURE, result.getConsentInfo().getStatus());
    // }
    //
    // @Test
    // public void testUpdatePackageAndLocalizeConsent_successAndLanguageChanged() throws Exception {
    //     PackageId packageId = new PackageId("pid");
    //     DocumentPackage docPkg = mock(DocumentPackage.class);
    //     Package oldPkg = mock(Package.class);
    //     Package newPkg = mock(Package.class);
    //     when(oldPkg.getLanguage()).thenReturn("en");
    //     when(newPkg.getLanguage()).thenReturn("fr");
    //     doReturn(Optional.of(oldPkg)).when(packageService).getPackageWithoutException(packageId.getId());
    //     doReturn(newPkg).when(packageService).getApiPackage(packageId.getId());
    //     doReturn(Optional.of(newPkg)).when(packageService).getPackageWithoutException(packageId.getId());
    //     doNothing().when(packageService).localizeConsent(eq(packageId), eq("fr"), any(PackageUpdateWorkflowResult.class));
    //     doNothing().when(client).put(anyString(), anyString());
    //     doReturn(new com.silanis.esl.api.model.Package()).when(new DocumentPackageConverter(docPkg)).toAPIPackage();
    //     PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, docPkg);
    //     assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getPackageInfo().getStatus());
    // }
    //
    // @Test
    // public void testUpdatePackageAndLocalizeConsent_languageNotChanged() throws Exception {
    //     PackageId packageId = new PackageId("pid");
    //     DocumentPackage docPkg = mock(DocumentPackage.class);
    //     Package oldPkg = mock(Package.class);
    //     Package newPkg = mock(Package.class);
    //     when(oldPkg.getLanguage()).thenReturn("en");
    //     when(newPkg.getLanguage()).thenReturn("en");
    //     doReturn(Optional.of(oldPkg)).when(packageService).getPackageWithoutException(packageId.getId());
    //     doReturn(newPkg).when(packageService).getApiPackage(packageId.getId());
    //     doReturn(Optional.of(newPkg)).when(packageService).getPackageWithoutException(packageId.getId());
    //     doNothing().when(client).put(anyString(), anyString());
    //     doReturn(new com.silanis.esl.api.model.Package()).when(new DocumentPackageConverter(docPkg)).toAPIPackage();
    //     PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, docPkg);
    //     assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getPackageInfo().getStatus());
    //     assertEquals(PackageUpdateWorkflowResult.Status.SKIPPED, result.getConsentInfo().getStatus());
    // }
    //
    // @Test(expected = EslServerException.class)
    // public void testUpdatePackageAndLocalizeConsent_updateFails() throws Exception {
    //     PackageId packageId = new PackageId("pid");
    //     DocumentPackage docPkg = mock(DocumentPackage.class);
    //     doThrow(new com.silanis.esl.sdk.internal.RequestException("fail")).when(client).put(anyString(), anyString());
    //     packageService.updatePackageAndLocalizeConsent(packageId, docPkg);
    // }
}

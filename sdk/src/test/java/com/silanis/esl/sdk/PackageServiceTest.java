package com.silanis.esl.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
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
    public void testBuildPackagePathWhenTransactionIdProvidedThenReturnsExpectedPath() throws Exception {
        String transactionId = "12345";
        String expected = new UrlTemplate("http://baseurl").urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", transactionId).build();
        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("buildPackagePath", String.class);
        buildPackagePathMethod.setAccessible(true);

        String result = (String) buildPackagePathMethod.invoke(packageService, transactionId);

        assertEquals(expected, result);
    }

    @Test
    public void testHasLanguageChangedWhenLanguagesDifferThenReturnsTrue() throws Exception {
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
    public void testHasLanguageChangedWhenLanguagesSameThenReturnsFalse() throws Exception {
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
    public void testHasLanguageChangedWhenCurrentLanguageIsNullThenReturnsFalse() throws Exception {
        Package existingPackage = new Package();
        existingPackage.setLanguage("en");
        Package updatedPackage = new Package();
        updatedPackage.setLanguage(null);

        Method buildPackagePathMethod = PackageService.class.getDeclaredMethod("hasLanguageChanged", Package.class,  Package.class);
        buildPackagePathMethod.setAccessible(true);
        boolean result = (boolean) buildPackagePathMethod.invoke(packageService, existingPackage, updatedPackage);

        assertFalse(result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetPackageWithoutExceptionWhenPackageExistsThenReturnsPresentOptional() throws Exception {
        String language = "en";
        String packageId = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        Package existingPackage = new Package();
        existingPackage.setLanguage(language);
        existingPackage.setId(packageId);
        String existingPackageStr = Serialization.toJson(existingPackage);
        when(clientMock.get(anyString()))
                .thenReturn(existingPackageStr);

        Method getPackageWithoutExceptionMethod = PackageService.class.getDeclaredMethod("getPackageWithoutException", String.class);
        getPackageWithoutExceptionMethod.setAccessible(true);
        Optional<Package> result = (Optional<Package>) getPackageWithoutExceptionMethod.invoke(packageService, packageId);

        assertTrue(result.isPresent());
        assertEquals(packageId, result.get().getId());
        assertEquals(language, result.get().getLanguage());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetPackageWithoutExceptionWhenExceptionThrownThenReturnsEmptyOptional() throws Exception {

        String packageId = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        Method getPackageWithoutExceptionMethod = PackageService.class.getDeclaredMethod("getPackageWithoutException", String.class);
        getPackageWithoutExceptionMethod.setAccessible(true);
        when(clientMock.get(anyString()))
                .thenThrow(new RuntimeException("fail"));

        Optional<Package> result = (Optional<Package>) getPackageWithoutExceptionMethod.invoke(packageService, packageId);
        assertFalse(result.isPresent());
    }

    @Test
    public void testLocalizeConsentWhenLocalizationSucceedsThenStatusIsSuccess() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        String language = "en";
        PackageUpdateWorkflowResult result = new PackageUpdateWorkflowResult();

        ConsentLocalizationData data = createConsentLocalizationData(packageUid, language);
        String dataStr = Serialization.toJson(data);

        when(clientMock.post(anyString(), anyString()))
                .thenReturn(dataStr);

        Method localizeConsentMethod = PackageService.class.getDeclaredMethod("localizeConsent", PackageId.class, String.class, PackageUpdateWorkflowResult.class);
        localizeConsentMethod.setAccessible(true);
        localizeConsentMethod.invoke(packageService, packageId, language, result);

        assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getConsentInfo().getStatus());
        assertEquals(packageUid, result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getUid());
        assertEquals(language, result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getLanguage());
        assertEquals(language, result.getConsentInfo().getConsentData().getConsentMetadata().getProperties().getLanguage());
        assertEquals(language, result.getConsentInfo().getConsentData().getConsentMetadata().getDocument().getLanguage());
    }

    @Test
    public void testLocalizeConsentWhenLocalizationFailsThenStatusIsFailure() throws Exception {
        PackageId packageId = new PackageId("pid");
        String language = "en";
        PackageUpdateWorkflowResult result = new PackageUpdateWorkflowResult();

        when(clientMock.post(anyString(), anyString()))
                .thenThrow(new RuntimeException("fail"));

        Method localizeConsentMethod = PackageService.class.getDeclaredMethod("localizeConsent", PackageId.class, String.class, PackageUpdateWorkflowResult.class);
        localizeConsentMethod.setAccessible(true);
        localizeConsentMethod.invoke(packageService, packageId, language, result);

        assertEquals(PackageUpdateWorkflowResult.Status.FAILURE, result.getConsentInfo().getStatus());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenLanguageChangedThenStatusIsSuccess() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageA = java.util.Locale.ENGLISH;
        java.util.Locale languageB = java.util.Locale.FRENCH;

        // State A: old package (before update)
        String existingPackageStr = toApiPackageJson(packageUid, languageA.getLanguage());
        // State B: new package (after update)
        String updatedPackageStr = toApiPackageJson(packageUid, languageB.getLanguage());

        // Build DocumentPackage for update
        DocumentPackage documentPackage = buildDocumentPackage(packageId, languageB);

        // ConsentLocalizationData for post
        ConsentLocalizationData data = createConsentLocalizationData(packageUid, languageB.getLanguage());
        String dataStr = Serialization.toJson(data);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(existingPackageStr, updatedPackageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString())).thenReturn(dataStr);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        inOrder.verify(clientMock).post(eq(postPath), anyString());
        inOrder.verifyNoMoreInteractions();

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getConsentInfo().getStatus());
        assertEquals(packageUid, result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getUid());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getLanguage());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getProperties().getLanguage());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getDocument().getLanguage());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenOldPackageGetFailsThenStatusIsSuccess() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageB = java.util.Locale.FRENCH;

        // State B: new package (after update)
        String updatedPackageStr = toApiPackageJson(packageUid, languageB.getLanguage());

        // Build DocumentPackage for update
        DocumentPackage documentPackage = buildDocumentPackage(packageId, languageB);

        // ConsentLocalizationData for post
        ConsentLocalizationData data = createConsentLocalizationData(packageUid, languageB.getLanguage());
        String dataStr = Serialization.toJson(data);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath))
                .thenThrow(new RuntimeException("failed to get existing package"))
                .thenReturn(updatedPackageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString())).thenReturn(dataStr);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        inOrder.verify(clientMock).post(eq(postPath), anyString());
        inOrder.verifyNoMoreInteractions();

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.SUCCESS, result.getConsentInfo().getStatus());
        assertEquals(packageUid, result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getUid());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getLanguage());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getProperties().getLanguage());
        assertEquals(languageB.getLanguage(), result.getConsentInfo().getConsentData().getConsentMetadata().getDocument().getLanguage());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenUpdatedPackageGetFailsThenConsentSkipped() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageA = java.util.Locale.ENGLISH;
        java.util.Locale languageB = java.util.Locale.FRENCH;

        // State A: old package (before update)
        String existingPackageStr = toApiPackageJson(packageUid, languageA.getLanguage());
        // State B: new package (after update)
        String updatedPackageStr = toApiPackageJson(packageUid, languageB.getLanguage());

        // Build DocumentPackage for update
        DocumentPackage documentPackage = buildDocumentPackage(packageId, languageB);

        // ConsentLocalizationData for post
        ConsentLocalizationData data = createConsentLocalizationData(packageUid, languageB.getLanguage());
        String dataStr = Serialization.toJson(data);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath))
                .thenReturn(existingPackageStr)
                .thenThrow(new RuntimeException("failed to get updated package"));
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString())).thenReturn(dataStr);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        verify(clientMock, never()).post(eq(postPath), anyString());
        inOrder.verifyNoMoreInteractions();
        verifyNoMoreInteractions(clientMock);

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.SKIPPED, result.getConsentInfo().getStatus());
        assertNull(result.getConsentInfo().getConsentData());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenLanguageNotChangedThenConsentSkipped() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale language = Locale.FRENCH;

        // State A: old package (before update)
        String packageStr = toApiPackageJson(packageUid, language.getLanguage());

        // Build DocumentPackage for update
        DocumentPackage documentPackage = buildDocumentPackage(packageId, language);

        // ConsentLocalizationData for post
        ConsentLocalizationData data = createConsentLocalizationData(packageUid, language.getLanguage());
        String dataStr = Serialization.toJson(data);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(packageStr, packageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString())).thenReturn(dataStr);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        verify(clientMock, never()).post(eq(postPath), anyString());
        inOrder.verifyNoMoreInteractions();
        verifyNoMoreInteractions(clientMock);

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.SKIPPED, result.getConsentInfo().getStatus());
        assertNull(result.getConsentInfo().getConsentData());
    }

    @Test(expected = com.silanis.esl.sdk.EslException.class)
    public void testUpdatePackageAndLocalizeConsentWhenPackageUpdateFailsThenThrowsEslException() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale language = Locale.FRENCH;

        // State A: old package (before update)
        String packageStr = toApiPackageJson(packageUid, language.getLanguage());

        DocumentPackage documentPackage = buildDocumentPackage(packageId, language);
        String apiPath = buildPackagePath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(packageStr, packageStr);
        when(clientMock.put(eq(apiPath), anyString()))
                .thenThrow(new com.silanis.esl.sdk.EslException("failed to updated package"));

        // Call the method under test (should throw EslException)
        packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);
        // No assertions or fail() needed, exception is expected
    }

    private String toApiPackageJson(String packageUid, String language) {
        Package apiPackage = new Package();
        apiPackage.setLanguage(language);
        apiPackage.setId(packageUid);
        return Serialization.toJson(apiPackage);
    }

    private DocumentPackage buildDocumentPackage(PackageId packageId, java.util.Locale language) {
        DocumentPackage documentPackage = PackageBuilder.newPackageNamed("Test Package")
                .describedAs("A test package for consent localization")
                .withLanguage(language)
                .withSigner(SignerBuilder.newSignerWithEmail("test@example.com")
                        .withFirstName("John")
                        .withLastName("Doe"))
                .withDocument(DocumentBuilder.newDocumentWithName("Test Document")
                        .withId("doc1"))
                .build();
        documentPackage.setId(packageId);

        return documentPackage;
    }

    private String buildPackagePath(String packageUid) {
        return new UrlTemplate("http://baseurl").urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageUid).build();
    }

    private String buildLocalizeConsentPath(String packageUid) {
        return new UrlTemplate("http://baseurl").urlFor(UrlTemplate.LOCALIZE_CONSENT_PATH)
                .replace("{packageId}", packageUid).build();
    }

    private ConsentLocalizationData createConsentLocalizationData(String packageUid, String language) {
        ConsentLocalizationData data = new ConsentLocalizationData();
        data.setConsentId("default-consent");

        ConsentLocalizationData.ConsentMetadata metadata = new ConsentLocalizationData.ConsentMetadata();

        ConsentLocalizationData.PackageInfo packageInfo = new ConsentLocalizationData.PackageInfo();
        packageInfo.setUid(packageUid);
        packageInfo.setLanguage(language);
        metadata.setPackageInfo(packageInfo);

        ConsentLocalizationData.ResourceMetadata properties = new ConsentLocalizationData.ResourceMetadata();
        properties.setAccountId("sample-account-id");
        properties.setLanguage(language);
        metadata.setProperties(properties);

        ConsentLocalizationData.ResourceMetadata document = new ConsentLocalizationData.ResourceMetadata();
        document.setAccountId("sample-account-id");
        document.setLanguage(language);
        metadata.setDocument(document);

        data.setConsentMetadata(metadata);
        return data;
    }
}

package com.silanis.esl.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.service.ConsentLocalizationMessages;
import com.silanis.esl.sdk.service.DocumentAcceptanceUtil;
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
        Package existingPackage = getAPackage(packageId, language);
        String existingPackageStr = Serialization.toJson(existingPackage);
        when(clientMock.get(anyString()))
                .thenReturn(existingPackageStr);

        Method getPackageWithoutExceptionMethod = PackageService.class.getDeclaredMethod("getPackageWithoutException", String.class);
        getPackageWithoutExceptionMethod.setAccessible(true);
        Package result = (Package) getPackageWithoutExceptionMethod.invoke(packageService, packageId);

        assertNotNull(result);
        assertEquals(packageId, result.getId());
        assertEquals(language, result.getLanguage());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetPackageWithoutExceptionWhenExceptionThrownThenReturnsEmptyOptional() throws Exception {

        String packageId = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        Method getPackageWithoutExceptionMethod = PackageService.class.getDeclaredMethod("getPackageWithoutException", String.class);
        getPackageWithoutExceptionMethod.setAccessible(true);
        when(clientMock.get(anyString()))
                .thenThrow(new RuntimeException("fail"));

        Package result = (Package) getPackageWithoutExceptionMethod.invoke(packageService, packageId);
        assertNull(result);
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

    @Test(expected = EslException.class)
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
                .thenThrow(new IOException("failed to updated package"));

        // Call the method under test (should throw EslException)
        packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);
        // No assertions or fail() needed, exception is expected
    }

    @Test(expected = EslServerException.class)
    public void testUpdatePackageAndLocalizeConsentWhenPackageUpdateFailsThenThrowsEslServerException() throws Exception {
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
                .thenThrow(new RequestException("PUT", " transaction/bw1FGkfWT7tf0X4r6bHUfvIblKQ=", 400, "validation err", "{\"test\": \"RequestException\"}"));

        // Call the method under test (should throw EslException)
        packageService.updatePackageAndLocalizeConsent(packageId, documentPackage);
        // No assertions or fail() needed, exception is expected
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenConsentAcceptedThenUpdateConsentSkip() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageEn = Locale.ENGLISH;
        java.util.Locale languageFr = Locale.FRENCH;

        // State A: old package (before update)
        Package existingPackage = buildApiPackageWithDefaultConsent(packageUid, languageEn, true);
        String existingPackageStr = Serialization.toJson(existingPackage);

        // Build DocumentPackage for update
        DocumentPackage toUpdatePackage = new DocumentPackageConverter(existingPackage).toSDKPackage();
        toUpdatePackage.setLanguage(languageFr);

        // State B: updated package (tx language = FR)
        Package updatedPackage = buildApiPackageWithDefaultConsent(packageUid, languageFr, true);
        String updatedPackageStr = Serialization.toJson(updatedPackage);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(existingPackageStr, updatedPackageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, toUpdatePackage);

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
        assertEquals(ConsentLocalizationMessages.DEFAULT_DOCUMENT_CONSENT_ACCEPTED, result.getConsentInfo().getMessage());
        assertNull(result.getConsentInfo().getConsentData());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenDefaultConsentNotExistThenUpdateConsentFailed() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageEn = Locale.ENGLISH;
        java.util.Locale languageFr = Locale.FRENCH;

        int statusCode = 422;
        String reasonPhrase = "Unprocessable Entity";
        String messageKey = "error.unprocessableEntity.defaultConsent.notExist";
        String errorMessage = "The default document consent does not exist.";
        String technicalMessage = String.format("No default document consent is defined for the requested transaction with id = '%s'.", packageUid);
        RequestException requestException = getRequestExceptionStub(statusCode, reasonPhrase, messageKey, errorMessage, technicalMessage);

        // State A: old package (before update)
        Package existingPackage = buildApiPackageWithAcceptedConsent(packageUid, languageEn, "customConsent", false);
        String existingPackageStr = Serialization.toJson(existingPackage);

        // Build DocumentPackage for update
        DocumentPackage toUpdatePackage = new DocumentPackageConverter(existingPackage).toSDKPackage();
        toUpdatePackage.setLanguage(languageFr);

        // State B: updated package (tx language = FR)
        Package updatedPackage = buildApiPackageWithAcceptedConsent(packageUid, languageFr, "customConsent", false);
        String updatedPackageStr = Serialization.toJson(updatedPackage);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(existingPackageStr, updatedPackageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString()))
                .thenThrow(requestException);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, toUpdatePackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        inOrder.verify(clientMock).post(eq(postPath), anyString());

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.FAILURE, result.getConsentInfo().getStatus());
        assertEquals(ConsentLocalizationMessages.FAILED_TO_LOCALIZE_DEFAULT_CONSENT_PREFIX + errorMessage, result.getConsentInfo().getMessage());
        assertNull(result.getConsentInfo().getConsentData());
    }

    @Test
    public void testUpdatePackageAndLocalizeConsentWhenDefaultConsentConfigNotFoundThenUpdateConsentFailed() throws Exception {
        String packageUid = "bw1FGkfWT7tf0X4r6bHUfvIblKQ=";
        PackageId packageId = new PackageId(packageUid);
        java.util.Locale languageEn = Locale.ENGLISH;
        java.util.Locale languageFr = Locale.FRENCH;

        int statusCode = 422;
        String reasonPhrase = "Unprocessable Entity";
        String messageKey = "error.unprocessableEntity.defaultConsent.i18nConfiguration.notFound";
        String errorMessage = "Default document consent modification is not allowed: no localized consent template configuration found.";
        String technicalMessage = String.format("No localized consent template configuration was found for the requested transaction with id = '%s'.",
                packageUid);
        RequestException requestException = getRequestExceptionStub(statusCode, reasonPhrase, messageKey, errorMessage, technicalMessage);

        // State A: old package (before update)
        Package existingPackage = buildApiPackageWithAcceptedConsent(packageUid, languageEn, "customConsent", false);
        String existingPackageStr = Serialization.toJson(existingPackage);

        // Build DocumentPackage for update
        DocumentPackage toUpdatePackage = new DocumentPackageConverter(existingPackage).toSDKPackage();
        toUpdatePackage.setLanguage(languageFr);

        // State B: updated package (tx language = FR)
        Package updatedPackage = buildApiPackageWithAcceptedConsent(packageUid, languageFr, "customConsent", false);
        String updatedPackageStr = Serialization.toJson(updatedPackage);

        // Prepare expected URLs
        String apiPath = buildPackagePath(packageUid);
        String postPath = buildLocalizeConsentPath(packageUid);

        // Strict sequential returns for get, put, post
        when(clientMock.get(apiPath)).thenReturn(existingPackageStr, updatedPackageStr);
        when(clientMock.put(eq(apiPath), anyString())).thenReturn(null);
        when(clientMock.post(eq(postPath), anyString()))
                .thenThrow(requestException);

        // InOrder verification
        org.mockito.InOrder inOrder = inOrder(clientMock);

        // Call the method under test
        PackageUpdateWorkflowResult result = packageService.updatePackageAndLocalizeConsent(packageId, toUpdatePackage);

        // Verify strict order
        inOrder.verify(clientMock).get(apiPath); // get old
        inOrder.verify(clientMock).put(eq(apiPath), anyString());
        inOrder.verify(clientMock).get(apiPath); // get new
        inOrder.verify(clientMock).post(eq(postPath), anyString());

        // Assert result
        assertEquals(packageUid, result.getPackageUid());
        assertEquals(PackageUpdateWorkflowResult.Status.FAILURE, result.getConsentInfo().getStatus());
        assertEquals(ConsentLocalizationMessages.FAILED_TO_LOCALIZE_DEFAULT_CONSENT_PREFIX + errorMessage, result.getConsentInfo().getMessage());
        assertNull(result.getConsentInfo().getConsentData());
    }

    private String toApiPackageJson(String packageUid, String language) {
        Package apiPackage = getAPackage(packageUid, language);
        return Serialization.toJson(apiPackage);
    }

    private static Package getAPackage(String packageUid, String language) {
        Package apiPackage = new Package();
        apiPackage.setLanguage(language);
        apiPackage.setId(packageUid);
        return apiPackage;
    }

    private DocumentPackage buildDocumentPackage(PackageId packageId, Locale language) {
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

    private RequestException getRequestExceptionStub(int statusCode, String name, String messageKey, String errorMessage, String technical) {
        String method = "POST";
        String uri = "https://ossq1.rnd.esignlive.com/api/packages/R60p57FAK8IYGAae-VJRfjGbHx8=/documents/localize-consent";
        String reasonPhrase = "Conflict";
        String details = String.format("{\"name\":\"%s\",\"message\":\"%s\",\"code\":%s,\"messageKey\":\"%s\",\"technical\":\"%s\"}",
                name, errorMessage, statusCode, messageKey, technical);

        return new RequestException(method, uri, statusCode, reasonPhrase, details);

    }

    private Package buildApiPackageWithDefaultConsent(String packageUid, Locale language, boolean isAccepted) {
        return buildApiPackageWithAcceptedConsent(packageUid, language, DocumentAcceptanceUtil.DEFAULT_CONSENT_ID, isAccepted);
    }

    private Package buildApiPackageWithAcceptedConsent(String packageUid, Locale language, String consentId, boolean isAccepted) {
        Package apiPackage = new Package();
        apiPackage.setId(packageUid);
        apiPackage.setName("Test Package");
        apiPackage.setLanguage(language.getLanguage());
        apiPackage.setDescription("A test package for consent localization");
        apiPackage.setStatus(PackageStatus.DRAFT.name());
        apiPackage.setCreated(new Date());
        apiPackage.setUpdated(new Date());
        apiPackage.setEmailMessage("Test package message");
        apiPackage.setVisibility(Visibility.ACCOUNT.name());
        apiPackage.setTimezoneId("UTC");
        // Sender
        com.silanis.esl.api.model.Sender sender = new com.silanis.esl.api.model.Sender();
        sender.setEmail("sender@example.com");
        sender.setFirstName("SenderFirst");
        sender.setLastName("SenderLast");
        sender.setCompany("SenderCompany");
        sender.setTitle("SenderTitle");
        sender.setTimezoneId("UTC");
        apiPackage.setSender(sender);
        // Documents
        Document doc = new Document();
        doc.setName("Test Document");
        doc.setId(consentId);
        if (isAccepted) {
            Map<String, Object> data = new HashMap<>();
            data.put("esl_doc_accepted_by_signers", "56320d1e-4c71-4b6e-86f4-0d5c7d07d069");
            doc.setData(data);
        }
        List<Document> docs = new ArrayList<>();
        docs.add(doc);
        apiPackage.setDocuments(docs);
        // Roles (signers)
        com.silanis.esl.api.model.Role role = new com.silanis.esl.api.model.Role();
        role.setId("role1");
        role.setName("signer1");
        role.setIndex(1);
        com.silanis.esl.api.model.Signer signer = new com.silanis.esl.api.model.Signer();
        signer.setEmail("test@example.com");
        signer.setFirstName("John");
        signer.setLastName("Doe");
        role.addSigner(signer);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        apiPackage.setRoles(roles);
        return apiPackage;
    }
}

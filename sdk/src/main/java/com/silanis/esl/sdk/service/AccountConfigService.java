package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Link;
import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.internal.converter.HandoverConverter;
import com.silanis.esl.sdk.internal.converter.LocaleConverter;
import com.silanis.esl.sdk.service.apiclient.AccountConfigClient;

import java.util.Locale;

/**
 * Created by schoi on 2020-04-01.
 */
public class AccountConfigService {

    private AccountConfigClient apiClient;

    public AccountConfigService(AccountConfigClient apiClient) {
        this.apiClient = apiClient;
    }

    public Handover getHandoverUrl(Locale language) {
        Link link = apiClient.getHandoverUrl(LocaleConverter.convertToString(language));
        return new HandoverConverter(link).toSDKHandover(language);
    }

    public Handover createHandoverUrl(Handover handover) {
        Link link = new HandoverConverter(handover).toAPILink();
        link = apiClient.createHandoverUrl(LocaleConverter.convertToString(handover.getLanguage()), link);
        return new HandoverConverter(link).toSDKHandover(handover.getLanguage());
    }

    public Handover updateHandoverUrl(Handover handover) {
        Link link = new HandoverConverter(handover).toAPILink();
        link = apiClient.updateHandoverUrl(LocaleConverter.convertToString(handover.getLanguage()), link);
        return new HandoverConverter(link).toSDKHandover(handover.getLanguage());
    }

    public void deleteHandoverUrl(Locale language) {
        apiClient.deleteHandoverUrl(LocaleConverter.convertToString(language));
    }

}
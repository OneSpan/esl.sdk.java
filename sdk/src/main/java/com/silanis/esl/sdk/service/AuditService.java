package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.sdk.Audit;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.service.apiclient.AuditApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AuditService class provides a method to get the audit trail for a package.
 */
public class AuditService {

    private AuditApiClient apiClient;

    public AuditService(AuditApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Gets the audit trail for a package and returns a list of audits.
     *
     * @param packageId
     * @return A list of audits
     * @throws com.silanis.esl.sdk.EslException
     */
    public List<Audit> getAudit(PackageId packageId) throws EslException {
        String response = apiClient.getAudit(packageId.getId());
        List<Audit> audits;
        try {
            audits = mapToAudit(response);
        } catch (IOException e) {
            throw new EslException("Could not map audits", e);
        }
        return audits;
    }

    private List<Audit> mapToAudit(String stringResponse) throws IOException {
        List<Audit> auditList = new ArrayList<Audit>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(stringResponse);
        if (jsonNode.has("audit-events")) {
            for (int i = 0; jsonNode.get("audit-events").has(i); i++) {
                JsonNode event = jsonNode.get("audit-events").get(i);

                Audit audit = new Audit(event.get("type").asText(),
                        event.get("date-time").asText(),
                        event.get("target").asText(),
                        event.get("user").asText(),
                        event.get("user-email").asText(),
                        event.get("user-ip").asText(),
                        event.get("data").asText());

                auditList.add(audit);
            }
        }
        return auditList;
    }
}

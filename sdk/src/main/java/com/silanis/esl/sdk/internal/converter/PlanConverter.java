package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.CycleCount;
import com.silanis.esl.api.model.Plan;
import com.silanis.esl.api.model.Quota;

public class PlanConverter {

    private Optional<Plan> apiPlanOptional;
    private Optional<com.silanis.esl.sdk.Plan> sdkPlanOptional;

    public PlanConverter(Plan apiPlan ) {
        apiPlanOptional = Optional.of( apiPlan );
        sdkPlanOptional = Optional.absent();
    }

    public PlanConverter(com.silanis.esl.sdk.Plan sdkPlan ) {
        apiPlanOptional = Optional.absent();
        sdkPlanOptional = Optional.of( sdkPlan );
    }

    public Plan toAPIPlan() {
        if ( sdkPlanOptional.isPresent() ) {
            Plan apiPlan = new Plan();
            com.silanis.esl.sdk.Plan sdkPlan = sdkPlanOptional.get();
            apiPlan.setContract(sdkPlan.getContract());
            apiPlan.setCycle(sdkPlan.getCycle());
            apiPlan.setId(sdkPlan.getId());
            apiPlan.setName(sdkPlan.getName());
            apiPlan.setData(sdkPlan.getData());
            apiPlan.setDescription(sdkPlan.getDescription());
            apiPlan.setGroup(sdkPlan.getGroup());
            apiPlan.setOriginal(sdkPlan.getOriginal());
            apiPlan.setFeatures(sdkPlan.getFeatures());
            if (sdkPlan.getPrice() != null){
                apiPlan.setPrice(new PriceConverter(sdkPlan.getPrice()).toAPIPrice());
            }
            if (sdkPlan.getFreeCycles() != null) {
                CycleCount apiCycleCount = new CycleCount();
                apiCycleCount.setCount(sdkPlan.getFreeCycles().getCount());
                apiCycleCount.setCycle(sdkPlan.getFreeCycles().getCycle());
                apiPlan.setFreeCycles(apiCycleCount);
            }
            if (sdkPlan.getQuotas() != null) {
                for (com.silanis.esl.sdk.Quota quota : sdkPlan.getQuotas()){
                    Quota apiQuota = new Quota();
                    apiQuota.setCycle(quota.getCycle());
                    apiQuota.setLimit(quota.getLimit());
                    apiQuota.setScope(quota.getScope());
                    apiQuota.setTarget(quota.getTarget());
                    apiPlan.addQuota(apiQuota);
                }
            }
            return apiPlan;
        } else {
            return apiPlanOptional.get();
        }
    }

    public com.silanis.esl.sdk.Plan toSDKPlan() {
        if ( apiPlanOptional.isPresent() ) {
            com.silanis.esl.sdk.Plan sdkPlan = new com.silanis.esl.sdk.Plan();
            Plan apiPlan = apiPlanOptional.get();
            sdkPlan.setContract(apiPlan.getContract());
            sdkPlan.setCycle(apiPlan.getCycle());
            sdkPlan.setData(apiPlan.getData());
            sdkPlan.setDescription(apiPlan.getDescription());
            sdkPlan.setFeatures(apiPlan.getFeatures());
            sdkPlan.setId(apiPlan.getId());
            sdkPlan.setName(apiPlan.getName());
            sdkPlan.setGroup(apiPlan.getGroup());
            sdkPlan.setOriginal(apiPlan.getOriginal());
            if (apiPlan.getPrice() != null){
                sdkPlan.setPrice(new PriceConverter(apiPlan.getPrice()).toSDKPrice());
            }
            if (apiPlan.getFreeCycles() != null) {
                com.silanis.esl.sdk.CycleCount sdkCycleCount = new com.silanis.esl.sdk.CycleCount();
                sdkCycleCount.setCount(apiPlan.getFreeCycles().getCount());
                sdkCycleCount.setCycle(apiPlan.getFreeCycles().getCycle());
                sdkPlan.setFreeCycles(sdkCycleCount);
            }
            if (apiPlan.getQuotas() != null) {
                for (Quota quota : apiPlan.getQuotas()){
                    com.silanis.esl.sdk.Quota sdkQuota = new com.silanis.esl.sdk.Quota();
                    sdkQuota.setCycle(quota.getCycle());
                    sdkQuota.setLimit(quota.getLimit());
                    sdkQuota.setScope(quota.getScope());
                    sdkQuota.setTarget(quota.getTarget());
                    sdkPlan.addQuota(sdkQuota);
                }
            }
            return sdkPlan;
        } else {
            return sdkPlanOptional.get();
        }
    }
}

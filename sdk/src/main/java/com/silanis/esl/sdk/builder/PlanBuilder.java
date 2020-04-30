package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Plan;
import com.silanis.esl.sdk.Price;
import com.silanis.esl.sdk.CycleCount;
import com.silanis.esl.sdk.Quota;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanBuilder {
    private String contract;
    private String cycle;
    private Map<String, Object> data;
    private String description;
    private Map<String, Object> features;
    private CycleCount freeCycles;
    private String group;
    private String id;
    private String name;
    private String original;
    private Price price;
    private List<Quota> quotas;

    private PlanBuilder() {
        quotas = new ArrayList<Quota>();
        data = new HashMap<String, Object>();
        features = new HashMap<String, Object>();
    }

    public static PlanBuilder newPlan() {
        return new PlanBuilder();
    }

    public static PlanBuilder newPlan(String name)
    {
        return new PlanBuilder().withName(name);
    }
    public PlanBuilder withName(String value) {
        name = value;
        return this;
    }

    public PlanBuilder withId(String value) {
        id = value;
        return this;
    }

    public PlanBuilder withContract(String value) {
        contract = value;
        return this;
    }

    public PlanBuilder withCycle(String value) {
        cycle = value;
        return this;
    }

    public PlanBuilder withData(Map<String, Object> value) {
        data = value;
        return this;
    }

    public PlanBuilder withDescription(String value) {
        description = value;
        return this;
    }

    public PlanBuilder withFeatures(Map<String, Object> value) {
        features = value;
        return this;
    }

    public PlanBuilder withFreeCycles(CycleCount value) {
        freeCycles = value;
        return this;
    }

    public PlanBuilder withFreeCycles(Integer count, String cycle) {
        freeCycles = new CycleCount();
        freeCycles.setCount(count);
        freeCycles.setCycle(cycle);
        return this;
    }

    public PlanBuilder withGroup(String value) {
        group = value;
        return this;
    }

    public PlanBuilder withOriginal(String value) {
        original = value;
        return this;
    }

    public PlanBuilder withPrice(Price value) {
        price = value;
        return this;
    }

    public PlanBuilder withQuota(Quota value) {
        quotas.add(value);
        return this;
    }

    public PlanBuilder withQuota(String cycle, Integer limit, String scope, String target)
    {
        Quota quota = new Quota();
        quota.setCycle(cycle);
        quota.setLimit(limit);
        quota.setScope(scope);
        quota.setTarget(target);
        quotas.add(quota);
        return this;
    }

    public Plan build() {
        Plan plan = new Plan();
        plan.setContract(contract);
        plan.setData(data);
        plan.setName(name);
        plan.setId(id);
        plan.setCycle(cycle);
        plan.setDescription(description);
        plan.setPrice(price);
        plan.setFeatures(features);
        plan.setFreeCycles(freeCycles);
        plan.setOriginal(original);
        plan.setGroup(group);
        for (Quota quota : quotas)
        {
            plan.addQuota(quota);
        }
        return plan;
    }
}

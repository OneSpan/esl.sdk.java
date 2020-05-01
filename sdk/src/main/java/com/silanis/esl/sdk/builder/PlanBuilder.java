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

    public static PlanBuilder newPlan(String name) {
        return new PlanBuilder().withName(name);
    }

    public PlanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlanBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PlanBuilder withContract(String contract) {
        this.contract = contract;
        return this;
    }

    public PlanBuilder withCycle(String cycle) {
        this.cycle = cycle;
        return this;
    }

    public PlanBuilder withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public PlanBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PlanBuilder withFeatures(Map<String, Object> features) {
        this.features = features;
        return this;
    }

    public PlanBuilder withFreeCycles(CycleCount cycleCount) {
        this.freeCycles = cycleCount;
        return this;
    }

    public PlanBuilder withFreeCycles(Integer count, String cycle) {
        freeCycles = new CycleCount();
        freeCycles.setCount(count);
        freeCycles.setCycle(cycle);
        return this;
    }

    public PlanBuilder withGroup(String group) {
        this.group = group;
        return this;
    }

    public PlanBuilder withOriginal(String original) {
        this.original = original;
        return this;
    }

    public PlanBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public PlanBuilder withQuota(Quota quota) {
        quotas.add(quota);
        return this;
    }

    public PlanBuilder withQuota(String cycle, Integer limit, String scope, String target) {
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
        for (Quota quota : quotas) {
            plan.addQuota(quota);
        }
        return plan;
    }
}

package com.silanis.esl.api.model;
//

public interface IQuota {
    public IQuota setCycle( Cycle value);
    public Cycle getCycle();
    public IQuota setLimit( Integer value);
    public Integer getLimit();
    public IQuota setScope( Scope value);
    public Scope getScope();
    public IQuota setTarget( Target value);
    public Target getTarget();
    }
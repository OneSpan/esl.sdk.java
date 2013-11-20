package com.silanis.esl.sdk;

import com.silanis.esl.api.model.MemberType;

public enum GroupMemberType {
    REGULAR, MANAGER;

    public MemberType toAPIMemberType() {
        if ( this == REGULAR ) {
            return MemberType.REGULAR;
        } else {
            return MemberType.MANAGER;
        }
    }
}

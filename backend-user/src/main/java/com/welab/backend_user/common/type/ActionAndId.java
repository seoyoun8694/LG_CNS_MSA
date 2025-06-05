package com.welab.backend_user.common.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionAndId {
    private String action;
    private Long id;
    public static ActionAndId of(String action, Long id) {
        ActionAndId actionAndId = new ActionAndId();
        actionAndId.action = action;
        actionAndId.id = id;
        return actionAndId;
    }
}
package com.ys.refs.user.domain;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class UserId {

    @NotBlank
    String id;
}

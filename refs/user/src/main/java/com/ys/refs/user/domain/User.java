package com.ys.refs.user.domain;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class User {

    @Valid @NotNull
    UserId userId;
}

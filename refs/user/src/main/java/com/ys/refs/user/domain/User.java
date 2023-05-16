package com.ys.refs.user.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class User {

    UserId userId;
}

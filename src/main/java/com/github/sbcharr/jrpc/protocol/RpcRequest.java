package com.github.sbcharr.jrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest {
    // Unique ID for this request. Correlates request <--> response
    private String requestId;

    // service name eg "UserService"
    private String service;

    // method name to call eg "getUserById"
    private String method;

    // data type of method arguments eg ["String", "int"]
    private String[] paramTypes;

    // method arguments eg ["foo_bar", 1]
    private Object[] args;
}

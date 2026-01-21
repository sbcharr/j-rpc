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
    private String requestId; // Correlates request <--> response

    private String service; // eg "UserService"

    private String method; // eg "getUserById"

    private String[] paramTypes; // eg ["String", "int"]

    private Object[] args; // eg ["foo_bar", 1]
}

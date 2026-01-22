package com.github.sbcharr.jrpc.protocol;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse {
    // Correlates to RpcRequest
    private String requestId;

    // true if call succeeded, false if error occurred
    private boolean success;

    // Result of the RPC call if success == true
    private Object result;

    // Error details if success == false
    private RpcError error;
}

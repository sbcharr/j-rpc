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
    private String requestId; // Correlates to RpcRequest

    private boolean success; // true if call succeeded, false if error occurred

    private Object result; // Result of the RPC call if success == true

    private RpcError error; // Error details if success == false
}

package org.cc.service;

import org.charlestech.jsonrpc.RPCService;

@RPCService(serviceName = "testService")
public class TestService {
	public String getHello() {
		return "Hello, JSON-RPC";
	}

	public String sum(int a, int b) {
		int sum = a + b;
		return String.valueOf(sum);
	}
}

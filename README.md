JSONRPCAutoRegister
===================

A simple auto register for JSON-RPC, will simply the code when developed with JSON-RPC

The AutoRegisterJSONRPCServlet will init once the server is up, the initialzation logic will search all classes which is marked with RPCService annotation, and register all of them into the global JSONRPCBridge, so that the client don't need any extra work to register services

How to use
1. Config the servlet in web.xml, pass a init-param to specific which package the service classes are in.
  
  <servlet>
		<servlet-name>JSONRPCServlet</servlet-name>
		<servlet-class>
			org.charlestech.jsonrpc.AutoRegisterJSONRPCServlet
		</servlet-class>
		<init-param>
			<param-name>servicePath</param-name>
			<param-value>org.cc.service</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>JSONRPCServlet</servlet-name>
		<url-pattern>/JSON-RPC</url-pattern>
	</servlet-mapping>
	

2. Develop your service with the annotation RPCService

import org.charlestech.jsonrpc.RPCService;

@RPCService(serviceName = "testService")
public class TestService {
	public String getHello() {
		return "Hello, JSON-RPC";
	}
}

3. Use your service from js client

var jsonrpc = new JSONRpcClient("JSON-RPC");
var result = jsonrpc.testService.getHello();
alert(result);

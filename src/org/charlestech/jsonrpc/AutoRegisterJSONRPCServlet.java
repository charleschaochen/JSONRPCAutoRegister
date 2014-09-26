package org.charlestech.jsonrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.metaparadigm.jsonrpc.JSONRPCBridge;
import com.metaparadigm.jsonrpc.JSONRPCServlet;

/**
 * Register services under user specified package with annotations
 * 
 * @author Charles Chen
 * 
 */
public class AutoRegisterJSONRPCServlet extends JSONRPCServlet {
	private final String PATH_PARAM = "servicePath";
	private static final Log log = LogFactory
			.getLog(AutoRegisterJSONRPCServlet.class);

	@Override
	public void init(ServletConfig servletConfig) {
		/* BEGIN: Register services */
		String path = servletConfig.getInitParameter(PATH_PARAM);

		// If the path is not null, register all services with annotation
		if (path != null) {
			JSONRPCBridge bridge = JSONRPCBridge.getGlobalBridge();
			try {
				// Get all services under the path
				Map<String, Object> services = this.getAllServices(path);
				for (Entry<String, Object> service : services.entrySet()) {
					String serviceName = service.getKey();
					Object serviceInstance = service.getValue();
					bridge.registerObject(serviceName, serviceInstance);
					log.info("Registered Service: " + serviceName + " ("
							+ serviceInstance + ".class)");
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log
					.warn("The service package is not specified, would not reigister any RPC services");
		}
		super.init(servletConfig);
		/* END: Register services */
	}

	/**
	 * Get all event executors and add into entities map
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private Map<String, Object> getAllServices(String targetPackageName)
			throws InstantiationException, IllegalAccessException {
		Map<String, Object> services = new HashMap<String, Object>();
		Set<Class<?>> executorClassSet = ClassUtil
				.getClasses(targetPackageName);
		for (Class c : executorClassSet) {
			RPCService ann = (RPCService) c.getAnnotation(RPCService.class); // Get
			if (ann == null) {
				// If annotation is not found, ignore this class
				continue;
			}
			services.put(ann.serviceName(), c.newInstance());
		}
		return services;
	}

}

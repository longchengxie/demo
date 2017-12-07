/**
 *
 */
package com.lvmama.xcl.threadlocal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

/**
 * @author lancey
 */
public class HttpServletLocalThread {

    static class HttpContext {

        private HttpServletRequest request;
        private HttpServletResponse response;
        private Model model;
        private Map<String, Object> map = new HashMap<String, Object>();

        public HttpContext() {
        	
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }

        public void setResponse(HttpServletResponse response) {
            this.response = response;
        }

        public Model getModel() {
            return model;
        }

        public void setModel(Model model) {
            this.model = model;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }
    }






    static ThreadLocal<HttpContext> threadLocal = new ThreadLocal<HttpServletLocalThread.HttpContext>();

    public static void set(HttpServletRequest req, HttpServletResponse res) {
        initThreadLocal();
        threadLocal.get().setRequest(req);
        threadLocal.get().setResponse(res);
    }

    public static HttpServletRequest getRequest() {
        initThreadLocal();
        return threadLocal.get().getRequest();
    }

    public static HttpServletResponse getResponse() {
        initThreadLocal();
        return threadLocal.get().getResponse();
    }

    public static Model getModel() {
        initThreadLocal();
        return threadLocal.get().getModel();
    }

    public static void setModel(Model model) {
        initThreadLocal();
        threadLocal.get().setModel(model);
    }

    public static Map<String, Object> getMap() {
        initThreadLocal();
        return threadLocal.get().getMap();
    }

    public static void setMap(Map<String, Object> map) {
        initThreadLocal();
        threadLocal.get().setMap(map);
    }




    private synchronized static void initThreadLocal() {
        if (threadLocal.get() == null) {
            threadLocal.set(new HttpContext());
        }
    }



    public static void clean() {
        threadLocal.remove();
    }
}

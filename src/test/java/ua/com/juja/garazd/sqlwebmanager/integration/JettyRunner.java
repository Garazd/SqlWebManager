package ua.com.juja.garazd.sqlwebmanager.integration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JettyRunner {

    private ServletContext servletContext;
    protected WebApplicationContext applicationContext;
    private List<SpringContextInitEvent> springContextInitListeners = new LinkedList<>();
    private List<ServletContextInitEvent> servletContextInitListeners = new LinkedList<>();
    private String webApp;
    private String contextPath;
    private int port;

    private Server server;

    public JettyRunner(String webApp, String contextPath) {
        this.webApp = webApp;
        this.contextPath = contextPath;
    }

    public void addListener(SpringContextInitEvent listener) {
        this.springContextInitListeners.add(listener);
    }

    public void addListener(ServletContextInitEvent listener) {
        this.servletContextInitListeners.add(listener);
    }

    public int getPort() {
        return port;
    }

    interface SpringContextInitEvent {
        void contextInit(WebApplicationContext context);
    }

    interface ServletContextInitEvent {
        void contextInit(WebAppContext context, ServletContext servletContext);
    }

    public int start() throws Exception {
        stop();

        server = new Server(0);
        final WebAppContext context = loadWebContext();
        context.addEventListener(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                servletContext = sce.getServletContext();

                for (ServletContextInitEvent listener : servletContextInitListeners) {
                    listener.contextInit(context, servletContext);
                }

                context.addEventListener(new ServletContextListener() {
                    @Override
                    public void contextInitialized(ServletContextEvent sce) {
                        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

                        for (SpringContextInitEvent listener : springContextInitListeners) {
                            listener.contextInit(applicationContext);
                        }
                    }

                    @Override
                    public void contextDestroyed(ServletContextEvent sce) {
                    }
                });
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
            }
        });
        server.setHandler(context);
        server.start();
        port = server.getConnectors()[0].getLocalPort();

        return port;
    }

    private WebAppContext loadWebContext() throws IOException {
        WebAppContext context = new WebAppContext(webApp, contextPath);
        Resource resource = context.newResource(context.getWar());
        if (resource.exists()) {
            return context;
        }
        throw new RuntimeException("Webapp not found!");
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getUrl() {
        return "http://localhost:" + port + contextPath;
    }

    public void stop() throws Exception {
        if (server != null) {
            server.stop();
            server = null;
        }
    }
}
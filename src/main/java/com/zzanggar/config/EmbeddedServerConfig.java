package com.zzanggar.config;

import org.apache.catalina.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class EmbeddedServerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Value("${path.resources:/BOOT-INF/classes/META-INF/resources}")
    private String RESOURCES_PATH;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers(new EmbeddedTomcatContextCustomizer());
    }

    private class EmbeddedTomcatContextCustomizer implements TomcatContextCustomizer {
        @Override
        public void customize(Context context) {
            context.addLifecycleListener(new EmbeddedResourceConfigurer(context));
        }
    }

    private class EmbeddedResourceConfigurer implements LifecycleListener {
        private final Context context;

        EmbeddedResourceConfigurer(Context context) {
            this.context = context;
        }

        @Override
        public void lifecycleEvent(LifecycleEvent event) {
            if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
                ApplicationHome home = new ApplicationHome(this.getClass());
                if (home.getSource() != null) {
                    File file = new File(home.getSource().getAbsolutePath());
                    if (file.isFile()) {
                        context.getResources().createWebResourceSet(
                                WebResourceRoot.ResourceSetType.RESOURCE_JAR, "/", file.getAbsolutePath(), null, RESOURCES_PATH);
                    }
                }
            }
        }
    }
}

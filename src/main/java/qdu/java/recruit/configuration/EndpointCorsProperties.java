package qdu.java.recruit.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "endpoints.cors")
public class EndpointCorsProperties {

    /**
     * Comma-separated list of origins to allow. '*' allows all origins. When not set,
     * CORS support is disabled.
     */
    private List<String> allowedOrigins = new ArrayList<String>();

    /**
     * Comma-separated list of methods to allow. '*' allows all methods. When not set,
     * defaults to GET.
     */
    private List<String> allowedMethods = new ArrayList<String>();

    /**
     * Comma-separated list of headers to allow in a request. '*' allows all headers.
     */
    private List<String> allowedHeaders = new ArrayList<String>();

    /**
     * Comma-separated list of headers to include in a response.
     */
    private List<String> exposedHeaders = new ArrayList<String>();

    /**
     * Set whether credentials are supported. When not set, credentials are not supported.
     */
    private Boolean allowCredentials;

    /**
     * How long, in seconds, the response from a pre-flight request can be cached by
     * clients.
     */
    private Long maxAge = 1800L;

    public List<String> getAllowedOrigins() {
        return this.allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        return this.allowedMethods;
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        return this.allowedHeaders;
    }

    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public List<String> getExposedHeaders() {
        return this.exposedHeaders;
    }

    public void setExposedHeaders(List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    public Boolean getAllowCredentials() {
        return this.allowCredentials;
    }

    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public Long getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

}
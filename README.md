# Conditional on Module

Example:

```java
    public interface MyService {
        Map<String, Object> getProductDetails(String productId);
    }

    // this implementation will be used if okhttp is found on the classpath
    @Service
    @ConditionalOnModule("okhttp")
    public static class OKHttpClientImpl implements MyService {

        @Override
        public Map<String, Object> getProductDetails(String productId) {
            // TODO
            return null;
        }
    }

    // this implementation will be used if okhttp is NOT found on the classpath
    @Service
    @ConditionalOnMissingModule("okhttp")
    public static class JavaHttpClientImpl implements MyService {

        @Override
        public Map<String, Object> getProductDetails(String productId) {
            // TODO
            return null;
        }
    }
```
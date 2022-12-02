# Multiple Chains Example

This is an example of how to implement multiple chains of responsibility with [COR Bean Processor Spring Boot Starter][cor-bean-processor].

The main objective of this project is to retrieve configuration files of different types - XML, JSON or YAML - from different sources: File system, Java classpath or HTTP server.

![An Unified Modeling (UML) Diagram presenting the structure developed to create the content verifier chain links.](./documentation/content-verifier-uml.png)
![An Unified Modeling (UML) Diagram presenting the structure developed to create the configuration retriever chain links.](./documentation/configuration-retriever-uml.png)

Even though both chains extends [`ChainLink`][chain-link] interface, the library knows that there are two different chains to be assembled due to the class informed on the interface parameter.

## Highlights

### Content Verifier Chain

[`ContentVerifier`][content-verifier] interface extends [COR Bean Processor Spring Boot Starter `ChainLink<T>` interface][chain-link] passing a self-reference as parameter. It also contains the entry method used to verify the content type.
```java
public interface ContentVerifier extends ChainLink<ContentVerifier> {

  ContentType verify(String content);
}
```

[`AbstractContentVerifier`][abstract-content-verifier] is an abstract class that implements [`ContentVerifier`][content-verifier] interface. It also implements `verify` demanded by [`ContentVerifier`][content-verifier] and `setNext` demanded by [`ChainLink`][chain-link]. The latter is implemented using [Lombok `@Setter` annotation][lombok-setter].

```java
public abstract class AbstractContentVerifier implements ContentVerifier {

  @Setter
  private ContentVerifier next;

  @Override
  public ContentType verify(String content) { /* Full logic can be checked in the code implementation. */ }
}
```

[`ContentVerifierService`][content-verifier-service] encapsulates the components required to verify a content type. 
It contains a `verify` method which invokes the first link `verify` method, starting the chain analysis.

```java
public class ContentVerifierService {

  private final ContentVerifier firstLink;

  public ContentType verify(String content) {
    return contentVerifier.verify(content);
  }
}
```

[COR Bean Processor Spring Boot Starter][cor-bean-processor] will be responsible to chain all [`ContentVerifier`][content-verifier] implementations and inject the first chain link on `firstLink` field.

### Configuration Retriever Chain

[`ConfigurationRetriever`][configuration-retriever] interface extends [COR Bean Processor Spring Boot Starter `ChainLink<T>` interface][chain-link] passing a self-reference as parameter. It also contains the entry method definition to retrieve the configuration.
```java
public interface ConfigurationRetriever extends ChainLink<ConfigurationRetriever> {
  Map<String, String> retrieve(URI uri);
}
```

[`AbstractConfigurationRetriever`][abstract-configuration-retriever] is an abstract class that implements [`ConfigurationRetriever`][configuration-retriever] interface. It also implements `retrieve` demanded by [`ConfigurationRetriever`][configuration-retriever] and `setNext` demanded by [`ChainLink`][chain-link]. The latter is implemented using [Lombok `@Setter` annotation][lombok-setter].

```java
public abstract class AbstractConfigurationRetriever implements ConfigurationRetriever {

  @Setter
  private ConfigurationRetriever next;

  @Override
  public Map<String, String> retrieve(URI uri) { /* Full logic can be checked in the code implementation. */ }
}
```

[`ConfigurationService`][configuration-service] encapsulates the components required to retrieve configuration.
It contains a `retrieve` method which invokes the first link `retrieve` method, starting the chain analysis.

```java
public class ConfigurationService {

  private final ConfigurationRetriever firstLink;

  public Map<String, String> retrieve(URI uri) {
    return configurationRetriever.retrieve(uri);
  }
}
```

[COR Bean Processor Spring Boot Starter][cor-bean-processor] will be responsible to chain all [`ConfigurationRetriever`][configuration-retriever] implementations and inject the first chain link on `firstLink` field.

## Execution

The complete integration with Spring Boot can be checked on [`ContentVerifierServiceIT`][content-verifier-service-it] and [`ConfigurationServiceIT`][configuration-service-it] classes. They are integration tests and can be executed through an IDE like [IntelliJ IDEA][intellij-idea], [Eclipse][eclipse] or [Microsoft Visual Studio Code][microsoft-visual-studio-code].

Alternatively, the tests can also be executed through a terminal running the following command on this module root directory.

```bash
mvn failsafe:integration-test
```

[abstract-configuration-retriever]: ./src/main/java/com/figtreelake/multiplechains/service/configuration/retriever/AbstractConfigurationRetriever.java
[abstract-content-verifier]: ./src/main/java/com/figtreelake/multiplechains/service/contentverifier/link/AbstractContentVerifier.java
[configuration-retriever]: ./src/main/java/com/figtreelake/multiplechains/service/configuration/retriever/ConfigurationRetriever.java
[configuration-service]: ./src/main/java/com/figtreelake/multiplechains/service/configuration/ConfigurationService.java
[configuration-service-it]: ./src/test/java/com/figtreelake/multiplechains/service/configuration/ConfigurationServiceIT.java
[content-verifier]: ./src/main/java/com/figtreelake/multiplechains/service/contentverifier/link/ContentVerifier.java
[content-verifier-service]: ./src/main/java/com/figtreelake/multiplechains/service/contentverifier/ContentVerifierService.java
[content-verifier-service-it]: ./src/test/java/com/figtreelake/multiplechains/service/contentverifier/ContentVerifierServiceIT.java
[cor-bean-processor]: https://github.com/MarceloLeite2604/cor-bean-processor-spring-boot-autoconfiguration
[chain-link]: https://github.com/MarceloLeite2604/cor-bean-processor-spring-boot-autoconfiguration/blob/main/autoconfigure/src/main/java/com/figtreelake/corbeanprocessor/autoconfigure/link/ChainLink.java
[eclipse]: https://www.eclipse.org/downloads/
[intellij-idea]: https://www.jetbrains.com/idea/
[lombok-setter]: https://projectlombok.org/features/GetterSetter
[microsoft-visual-studio-code]: https://code.visualstudio.com/download
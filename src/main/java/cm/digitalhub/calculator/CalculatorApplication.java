package cm.digitalhub.calculator;

import com.hazelcast.client.config.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class CalculatorApplication {

  @Value("${hazelcast.host}")
  private String hazelcastHost;

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@Bean
  public ClientConfig hazelcastClientConfig() {
    var clientConfig = new ClientConfig();
    clientConfig.getNetworkConfig().addAddress(hazelcastHost);
    return clientConfig;
  }
}

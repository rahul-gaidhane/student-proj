package in.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("{!prod}")	//this disables the swagger in prod profile
@Configuration
@EnableSwagger2
public class SwaggerConfig {

}

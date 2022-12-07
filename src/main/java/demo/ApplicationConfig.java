package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.geolatte.geom.json.GeolatteGeomModule;

@Configuration
public class ApplicationConfig {
  
  @Bean
  GeolatteGeomModule geolatteModule(){
    return new GeolatteGeomModule();
  }
}

package com.BjitAcademy.TrainingManagementSystemServer.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info = @Info(
        title = "Training Management System",
        description = "Description For Training Management System"
))
public class OpenApiConfig {

}

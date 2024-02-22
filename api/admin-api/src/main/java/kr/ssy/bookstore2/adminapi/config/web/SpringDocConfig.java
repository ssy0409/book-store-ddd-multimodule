package kr.ssy.bookstore2.adminapi.config.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Book Store Admin Api", version = "v1"),
        security = {
                @SecurityRequirement(name = "Authorization")
        }
)
@SecuritySchemes({
        @SecurityScheme(
                name = "Authorization",
                type = SecuritySchemeType.HTTP,
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization",
                scheme = "Bearer"
        )
})
public class SpringDocConfig {

    @PostConstruct
    public void init() {
        var localTimeSchema = new Schema<LocalTime>();
        localTimeSchema.example(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).type("string");
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, localTimeSchema);

        var localDateSchema = new Schema<LocalDate>();
        localDateSchema.example(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).type("string");
        SpringDocUtils.getConfig().replaceWithSchema(LocalDate.class, localDateSchema);

        var localDateTimeSchema = new Schema<LocalDateTime>();
        localDateTimeSchema.example(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).type("string");
        SpringDocUtils.getConfig().replaceWithSchema(LocalDateTime.class, localDateTimeSchema);
    }
}
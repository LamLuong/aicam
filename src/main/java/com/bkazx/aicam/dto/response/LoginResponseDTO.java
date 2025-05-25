package com.bkazx.aicam.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class LoginResponseDTO {
   private String username;
   private String token;
   @Builder.Default
   private String type = "Bearer";

}

package com.platform.blogging.dto.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JwtAuthenticationRequest {
   private String username;
   private String password;
}

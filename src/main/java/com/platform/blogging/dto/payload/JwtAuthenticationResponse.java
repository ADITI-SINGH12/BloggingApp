package com.platform.blogging.dto.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter

public class JwtAuthenticationResponse {
   private String token;
}

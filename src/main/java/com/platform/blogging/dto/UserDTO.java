package com.platform.blogging.dto;
import com.platform.blogging.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min = 4, max = 15, message = "name size should be in between 4 and 15")
    private String name;
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 10, message = "password size should be in between 6 and 10")
    private String password;
    @NotEmpty
    @Size(min = 4, max = 15, message = "about size should be in between 4 and 15")
    private String about;

 //   private List<Post> posts = new ArrayList<>(); this is not here because client side pe ,just
    //after creating user,one cannot post at the time of registration itself

}

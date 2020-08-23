package Tests.APITests.Health_Information_User.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDoctor {

    String username;
    String password;

}
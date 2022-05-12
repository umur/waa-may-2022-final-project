package pro.manage.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String userName;
    private String passWord;


}

package mapper.Opject;


import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {
    private final ModelMapper modelMapper;


    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

public UserDTO maptoDTO (UserEntity entity){
        UserDTO dto = new UserDTO();
    dto.setUserID(entity.getUserID());
    dto.setIsAdmin(entity.getIsAdmin());
    dto.setUserName(entity.getUserName());
    dto.setPassword(entity.getPassword());
    dto.setPhone(entity.getPhone());
    dto.setEmail(entity.getEmail());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.setLastName());
    return dto;
}

    public UserEntity maptoEntity (UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setUserID(dto.getUserID());
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName();
        return entity;
    }
}

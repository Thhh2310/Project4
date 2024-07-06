package mapper.Opject;


import com.example.webbanmypham.dto.CartDTO;
import com.example.webbanmypham.entity.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CartDTO maptoDTO (CartEntity entity){
        CartDTO dto = new CartDTO();
        dto.setCartID(entity.getCartID());
        dto.setQuantity(entity.getQuantity());
        dto.setUserID(entity.getUserID().getUserID());
        dto.setProductID(entity.getProductID().getProductID());
        return dto;
    }

    public CartEntity maptoEntity (CartDTO dto){
        CartEntity entity = new CartEntity();
        entity.setCartID(dto.getCartID());
        entity.setQuantity(dto.getQuantity());
        entity.setUserID(dto.getUserID().getUserID());
        entity.setProductID(dto.getProductID().getProductID());
        return entity;
    }
}

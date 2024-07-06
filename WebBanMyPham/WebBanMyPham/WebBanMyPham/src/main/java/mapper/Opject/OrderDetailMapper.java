package mapper.Opject;


import com.example.webbanmypham.dto.OrderDetailDTO;
import com.example.webbanmypham.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {
    private final ModelMapper modelMapper;
    public OrderDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderDetailDTO maptoDTO (OrderDetailEntity entity){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setProductID(entity.getProductID() != null ? entity.getProductID().getProductID() : null);
        dto.setOrderDetailID(entity.getOrderDetailID());
        dto.setOrderID(entity.getOrderID() != null ? entity.getOrderID().getOrderID() : null);
        dto.setPrice(entity.getPrice());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setQuantity(entity.getQuantity());
        dto.setUserID(entity.getUserID().getUserID());
        return dto;
    }
    public OrderDetailEntity maptoEntity (OrderDetailDTO dto){
        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setOrderDetailID(dto.getOrderDetailID());
        entity.setPrice(dto.getPrice());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}

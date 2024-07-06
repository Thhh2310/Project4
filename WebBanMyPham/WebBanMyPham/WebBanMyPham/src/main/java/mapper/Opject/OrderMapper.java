package mapper.Opject;

import com.example.webbanmypham.dto.OrderDTO;
import com.example.webbanmypham.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDTO maptoDTO (OrderEntity entity){
        OrderDTO dto = new OrderDTO();
        dto.setOrderID(entity.getOrderID());
        dto.setUserID(entity.getUserID() != null ? entity.getUserID().getUserID() : null);
        dto.setOrderCancel(entity.getOrderCancel());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderPay(entity.getOrderPay());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setOrderStatus(entity.getOrderStatus());
        return dto;
    }
    public OrderEntity maptoEntity (OrderDTO dto){
        OrderEntity entity = new OrderEntity();
        entity.setOrderID(dto.getOrderID());
        entity.setOrderCancel(dto.getOrderCancel());
        entity.setOrderDate(dto.getOrderDate());
        entity.setOrderStatus(dto.getOrderStatus());
        entity.setOrderPay(dto.getOrderPay());
        return entity;
    }
}

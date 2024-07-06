package mapper.Opject;
import com.example.webbanmypham.dto.ProductDTO;
import com.example.webbanmypham.entity.ProductEntity;
import org.modelmapper.ModelMapper;

import java.sql.Date;

public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO maptoDTO (ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setProductID(entity.getProductID());
        dto.setCategoryID(entity.getCategoryID());
        dto.setDateAdd(entity.getDateAdd());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setPrice(entity.getPrice());
        dto.setProductName(entity.getProductName());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public ProductEntity maptoEntity (ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setProductID(dto.getProductID());
        entity.setProductName(dto.getProductName());
        entity.setDescription(dto.getDescription());
        entity.setCategoryID(dto.getCategoryID());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setDateAdd((Date) dto.getDateAdd());
        entity.setImage(dto.getImage());

        return entity;
    }
}

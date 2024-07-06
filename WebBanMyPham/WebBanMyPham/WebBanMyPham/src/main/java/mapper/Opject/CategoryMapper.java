package mapper.Opject;

import com.example.webbanmypham.dto.CategoryDTO;
import com.example.webbanmypham.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO maptoDTO (CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryID(entity.getCategoryID());
        dto.setCategoryName(entity.getCategoryName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    public CategoryEntity maptoEntity (CategoryDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryID(dto.getCategoryID());
        entity.setCategoryName(dto.getCategoryName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}

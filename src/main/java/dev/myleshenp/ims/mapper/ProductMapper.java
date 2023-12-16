package dev.myleshenp.ims.mapper;

import dev.myleshenp.ims.dto.ProductDTO;
import dev.myleshenp.ims.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "updatedDate", target = "updatedDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    ProductDTO entityToDTO(Product product);

    @InheritInverseConfiguration
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "LocalDateTimeToOffsetDateTime")
    @Mapping(source = "updatedDate", target = "updatedDate", qualifiedByName = "LocalDateTimeToOffsetDateTime")
    Product DtoToEntity(ProductDTO productDTO);
}

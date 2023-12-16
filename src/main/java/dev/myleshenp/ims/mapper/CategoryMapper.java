package dev.myleshenp.ims.mapper;

import dev.myleshenp.ims.dto.CategoryDTO;
import dev.myleshenp.ims.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "updatedDate", target = "updatedDate", qualifiedByName = "offsetDateTimeToLocalDateTime")
    CategoryDTO entityToDTO(Category category);

    @InheritInverseConfiguration
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "LocalDateTimeToOffsetDateTime")
    @Mapping(source = "updatedDate", target = "updatedDate", qualifiedByName = "LocalDateTimeToOffsetDateTime")
    Category DtoToEntity(CategoryDTO categoryDTO);
    @Named("offsetDateTimeToLocalDateTime")
    public static LocalDateTime offsetDateToLocalDate(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return LocalDateTime.now();
        }
        var zonedDateTime = ZonedDateTime.parse(offsetDateTime.toString());
        return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }


    @Named("LocalDateTimeToOffsetDateTime")
    public static  OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return OffsetDateTime.now();
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }
}

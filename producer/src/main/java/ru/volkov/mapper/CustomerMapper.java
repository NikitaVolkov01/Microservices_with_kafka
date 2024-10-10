package ru.volkov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.volkov.dto.CustomerDTO;
import ru.volkov.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer toEntity(CustomerDTO customerDTO);
    CustomerDTO toDto(Customer customer);
}

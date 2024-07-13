package com.regis.parking_service.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class Utils {
  public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    ModelMapper modelMapper = new ModelMapper();
    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
  }
}

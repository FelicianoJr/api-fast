package br.com.vivo.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MapperConverter<D,E> {

    @Autowired
    private ModelMapper modelMapper;

    protected List<D> convertToDto(List<E> entity) {
        return entity.stream()
                .map(this::convertToDTo)
                .collect(Collectors.toList());
    }

    protected D convertToDTo(E entity) {
        return modelMapper.map(entity, (Class<D>) getClassType(0));
    }

    protected Type getClassType(int index) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return type.getActualTypeArguments()[index];
    }
}

package com.readowo.api.ReadOwo.mapping;

import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.dtos.UserDto;
import com.readowo.api.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    public Page<UserDto> modelListPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserDto.class), pageable, modelList.size());
    }
    public UserDto toResource(User model){
        return mapper.map(model, UserDto.class);
    }
}

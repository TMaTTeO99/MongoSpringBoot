package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.events.OutletEvent;
import com.MongoSpringBoot.model.OutletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutletMapper {

    OutletEntity toOutletEntityFromEvent(OutletEvent event);

}

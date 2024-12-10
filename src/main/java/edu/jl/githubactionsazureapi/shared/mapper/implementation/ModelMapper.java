package edu.jl.githubactionsazureapi.shared.mapper.implementation;

import edu.jl.githubactionsazureapi.shared.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelMapper implements Mapper {
    private final org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    @Override
    public <O, D> D mapToObject(O source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

    @Override
    public <O, D> List<D> mapToList(List<O> source, Class<D> destination) {
        return source.stream().map(object -> modelMapper.map(object, destination)).toList();
    }

    @Override
    public <O, D> void mapProperties(O source, D destination) {
        modelMapper.map(source, destination);
    }
}

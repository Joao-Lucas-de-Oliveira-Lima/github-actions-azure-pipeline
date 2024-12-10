package edu.jl.githubactionsazureapi.shared.mapper;

import java.util.List;

public interface Mapper {
    <O, D> D mapToObject(O source, Class<D> destination);

    <O, D> List<D> mapToList(List<O> source, Class<D> destination);

    <O, D> void mapProperties(O source, D destination);
}

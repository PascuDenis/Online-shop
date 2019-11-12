package learning.shop.mapping;

public interface Mapper<E1, E2> {
    E1 convertToEntity(E2 dto);

    E2 convertToDto(E1 entity);


//    static <E1,E2> E1 convertToEntity(E2 dto){return null;}
//
//    static <E1,E2> E2 convertToDto(E1 entity){return null;}
}

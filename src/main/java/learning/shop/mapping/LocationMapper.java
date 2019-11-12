package learning.shop.mapping;

import learning.shop.repository.IAddressRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import learning.shop.dto.LocationDTO;
import learning.shop.model.Location;

@Component
@AllArgsConstructor
public class LocationMapper implements Mapper<Location, LocationDTO> {
    private IAddressRepository addressRepository;

    @Override
    public Location convertToEntity(LocationDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Location location = modelMapper.map(dto, Location.class);
        location.setAddress(addressRepository.findById(dto.getAddressId()).orElse(null));
        return location;
    }

    @Override
    public LocationDTO convertToDto(Location entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, LocationDTO.class);
    }
}

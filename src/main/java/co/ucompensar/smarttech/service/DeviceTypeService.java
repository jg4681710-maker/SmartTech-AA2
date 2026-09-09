package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.entity.DeviceType;
import co.ucompensar.smarttech.repository.DeviceRepository;
import co.ucompensar.smarttech.repository.DeviceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeService {

    private final DeviceTypeRepository repository;
    private final DeviceRepository deviceRepository;

    public DeviceTypeService(
            DeviceTypeRepository repository,
            DeviceRepository deviceRepository) {

        this.repository = repository;
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceType> findAll() {
        return repository.findAll();
    }

    public DeviceType findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Tipo de dispositivo no encontrado: " + id));
    }

    public DeviceType save(DeviceType deviceType) {
        return repository.save(deviceType);
    }

    public void delete(Long id) {

        if (deviceRepository.existsByTypeId(id)) {
            throw new IllegalStateException(
                    "No se puede eliminar el tipo porque tiene dispositivos asociados.");
        }

        repository.deleteById(id);
    }
}
package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.dto.DeviceRequest;
import co.ucompensar.smarttech.dto.DeviceResponse;
import co.ucompensar.smarttech.entity.Brand;
import co.ucompensar.smarttech.entity.Device;
import co.ucompensar.smarttech.entity.DeviceType;
import co.ucompensar.smarttech.exception.ResourceNotFoundException;
import co.ucompensar.smarttech.repository.BrandRepository;
import co.ucompensar.smarttech.repository.DeviceRepository;
import co.ucompensar.smarttech.repository.DeviceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;
    private final DeviceTypeRepository deviceTypeRepository;

    public DeviceService(
            DeviceRepository deviceRepository,
            BrandRepository brandRepository,
            DeviceTypeRepository deviceTypeRepository) {

        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public List<DeviceResponse> findAll() {

        return deviceRepository
                .search(null, null, null)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DeviceResponse findByIdOrThrow(Long id) {

        Device device = deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el dispositivo con ID: " + id
                        )
                );

        return toResponse(device);
    }

    public DeviceResponse create(DeviceRequest request) {

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró la marca con ID: "
                                        + request.getBrandId()
                        )
                );

        DeviceType type = deviceTypeRepository.findById(request.getTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el tipo de dispositivo con ID: "
                                        + request.getTypeId()
                        )
                );

        Device device = new Device();

        applyRequest(device, request, brand, type);

        Device saved = deviceRepository.save(device);

        return toResponse(saved);
    }

    public DeviceResponse update(Long id, DeviceRequest request) {

        Device device = deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el dispositivo con ID: " + id
                        )
                );

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró la marca con ID: "
                                        + request.getBrandId()
                        )
                );

        DeviceType type = deviceTypeRepository.findById(request.getTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el tipo de dispositivo con ID: "
                                        + request.getTypeId()
                        )
                );

        applyRequest(device, request, brand, type);

        Device updated = deviceRepository.save(device);

        return toResponse(updated);
    }

    public void delete(Long id) {

        Device device = deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el dispositivo con ID: " + id
                        )
                );

        deviceRepository.delete(device);
    }

    public Device findById(Long id) {

        return deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el dispositivo con ID: " + id
                        )
                );
    }

    public List<Device> search(
            String keyword,
            Long brandId,
            String type) {

        return deviceRepository.search(
                keyword,
                brandId,
                type
        );
    }

    public Device save(Device device) {

        return deviceRepository.save(device);
    }

    private void applyRequest(
            Device device,
            DeviceRequest request,
            Brand brand,
            DeviceType type) {

        device.setName(request.getName());
        device.setBrand(brand);
        device.setType(type);
        device.setReleaseDate(request.getReleaseDate());
        device.setProcessor(request.getProcessor());
        device.setMemory(request.getMemory());
        device.setStorage(request.getStorage());
        device.setScreen(request.getScreen());
        device.setDescription(request.getDescription());
        device.setImageUrl(request.getImageUrl());
        device.setPrice(request.getPrice());
    }

    private DeviceResponse toResponse(Device device) {

        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getBrand().getName(),
                device.getType().getName(),
                device.getReleaseDate(),
                device.getProcessor(),
                device.getMemory(),
                device.getStorage(),
                device.getScreen(),
                device.getDescription(),
                device.getImageUrl(),
                device.getPrice()
        );
    }
}
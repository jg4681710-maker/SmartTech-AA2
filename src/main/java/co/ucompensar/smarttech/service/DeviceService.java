package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.entity.Device;
import co.ucompensar.smarttech.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<Device> search(String keyword, Long brandId, String type) {
        String cleanKeyword = (keyword == null || keyword.isBlank()) ? null : keyword.trim();
        String cleanType = (type == null || type.isBlank()) ? null : type;
        return repository.search(cleanKeyword, brandId, cleanType);
    }

    public Device findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Device save(Device device) { return repository.save(device); }
    public void delete(Long id) { repository.deleteById(id); }
}

package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.dto.DeviceResponse;
import co.ucompensar.smarttech.entity.Device;
import co.ucompensar.smarttech.service.DeviceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceRestController {

    private final DeviceService deviceService;

    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(
            value = "/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<DeviceResponse> getDevicesJson() {
        return getDeviceResponses();
    }

    @GetMapping(
            value = "/xml",
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public List<DeviceResponse> getDevicesXml() {
        return getDeviceResponses();
    }

    private List<DeviceResponse> getDeviceResponses() {

        List<Device> devices =
                deviceService.search(null, null, null);

        return devices.stream()
                .map(device -> new DeviceResponse(
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
                ))
                .toList();
    }
}
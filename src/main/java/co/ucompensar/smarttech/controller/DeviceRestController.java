package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.dto.DeviceRequest;
import co.ucompensar.smarttech.dto.DeviceResponse;
import co.ucompensar.smarttech.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceRestController {

    private final DeviceService deviceService;

    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<DeviceResponse>> findAll() {
        return ResponseEntity.ok(
                deviceService.findAll()
        );
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeviceResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                deviceService.findByIdOrThrow(id)
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeviceResponse> create(
            @Valid @RequestBody DeviceRequest request) {

        DeviceResponse response =
                deviceService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeviceResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DeviceRequest request) {

        DeviceResponse response =
                deviceService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        deviceService.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*
     * Endpoint conservado de la actividad anterior.
     * Permite consultar los dispositivos en JSON.
     */
    @GetMapping(
            value = "/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<DeviceResponse>> getDevicesJson() {

        return ResponseEntity.ok(
                deviceService.findAll()
        );
    }
}
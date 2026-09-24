package co.ucompensar.smarttech.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "La marca es obligatoria")
    private Long brandId;

    @NotNull(message = "El tipo de dispositivo es obligatorio")
    private Long typeId;

    @NotNull(message = "La fecha de lanzamiento es obligatoria")
    private LocalDate releaseDate;

    @NotBlank(message = "El procesador es obligatorio")
    private String processor;

    @NotBlank(message = "La memoria es obligatoria")
    private String memory;

    @NotBlank(message = "El almacenamiento es obligatorio")
    private String storage;

    @NotBlank(message = "La pantalla es obligatoria")
    private String screen;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "La URL de imagen es obligatoria")
    private String imageUrl;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    private BigDecimal price;

    public DeviceRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
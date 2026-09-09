package co.ucompensar.smarttech.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceResponse {

    private Long id;
    private String name;
    private String brand;
    private String type;
    private LocalDate releaseDate;
    private String processor;
    private String memory;
    private String storage;
    private String screen;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    public DeviceResponse() {
    }

    public DeviceResponse(
            Long id,
            String name,
            String brand,
            String type,
            LocalDate releaseDate,
            String processor,
            String memory,
            String storage,
            String screen,
            String description,
            String imageUrl,
            BigDecimal price) {

        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.releaseDate = releaseDate;
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.screen = screen;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
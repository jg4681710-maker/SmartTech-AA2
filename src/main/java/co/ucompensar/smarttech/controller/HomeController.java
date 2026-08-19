package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.service.BrandService;
import co.ucompensar.smarttech.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final DeviceService deviceService;
    private final BrandService brandService;

    public HomeController(DeviceService deviceService, BrandService brandService) {
        this.deviceService = deviceService;
        this.brandService = brandService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Long brandId,
                       @RequestParam(required = false) String type,
                       Model model) {
        model.addAttribute("devices", deviceService.search(keyword, brandId, type));
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedBrand", brandId);
        model.addAttribute("selectedType", type);
        return "index";
    }
}

package application.controller;

import application.model.dto.ProducerDto;
import application.model.dto.ProductDto;
import application.service.ProducerService;
import application.service.converter.ProducerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import application.service.ProductService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService service;
    private final ProducerService producerService;

    @GetMapping
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("products");
        try {
            result.addObject("products", service.findAll());
            result.addObject("producers", producerService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping
    public RedirectView add(@ModelAttribute("product") ProductDto product, @ModelAttribute("producerId") UUID id) {
        try {
            ProducerDto producer = producerService.findById(id);
            product.setProducer(new ProducerConverter().toDao(producer));
            service.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO check duplicate entries
        }
        return redirectToProductsList();
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView result = new ModelAndView("productForm");
        try {
            result.addObject("producers", producerService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam(name = "id") UUID id) {
        try {
            service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToProductsList();
    }

    @GetMapping("/update")
    public ModelAndView editForm(@RequestParam(name = "id") UUID id) {
        ModelAndView result = new ModelAndView("editProductForm");
        try {
            result.addObject("product", service.findById(id));
            result.addObject("producers", producerService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("product") ProductDto product, @ModelAttribute("producerId") UUID id) {
        try {
            product.setProducer(new ProducerConverter().toDao(producerService.findById(id)));
            service.save(product.getId(), product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToProductsList();
    }


    private RedirectView redirectToProductsList() {
        return redirect("/products");
    }

    private RedirectView redirect(String path) {
        return new RedirectView(path);
    }
}

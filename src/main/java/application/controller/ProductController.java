package application.controller;

import application.model.dto.ProductDto;
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
public class ProductController implements CrudController<ProductDto> {
    private final ProductService service;
    //private final ProducerService producerService;

    @GetMapping
    @Override
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("products");
        try {
            result.addObject("products", service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping
    @Override
    public RedirectView add(@ModelAttribute("product") ProductDto product) {
        try {
            service.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO check duplicate entries
        }
        return redirectToProductsList();
    }

    @GetMapping("/delete")
    @Override
    public RedirectView delete(@RequestParam(name = "id") UUID id) {
        service.deleteById(id);
        return redirectToProductsList();
    }

    @GetMapping("/update")
    @Override
    public RedirectView update(@RequestParam(name = "id") UUID id) {
        ProductDto dto = new ProductDto();
        //TODO required update form, temporary works with static new name
        dto.setName("some changed name1");
        service.save(id, dto);
        return redirectToProductsList();

    }

    private RedirectView redirectToProductsList() {
        return new RedirectView("/products");
    }
}

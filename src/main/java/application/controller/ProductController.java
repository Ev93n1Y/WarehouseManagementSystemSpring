package application.controller;

import application.model.dao.ProducerDao;
import application.model.dto.ProducerDto;
import application.model.dto.ProductDto;
import application.service.ProducerService;
import application.service.converter.ProducerConverter;
import jakarta.validation.Valid;
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
public class ProductController implements CrudController<ProductDto> {
    private final ProductService service;
    private final ProducerService producerService;

    @GetMapping
    @Override
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

    @Override
    public RedirectView add(ProductDto productDto) {
        return null;
    }

    @PostMapping
    public RedirectView add(@ModelAttribute("product") ProductDto product, @ModelAttribute("producerId") UUID id) {
        try {
            System.err.println("UUID: " + id);
            ProducerDto producer = producerService.findById(id);
            product.setProducer(new ProducerConverter().toDao(producer));
            System.err.println("Product: " + product);
            service.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO check duplicate entries
        }
        //return redirectToProductsList();
        return redirect("/products");
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
        //return new RedirectView("/products");
        return redirect("/products");
    }

    private RedirectView redirect(String path) {
        return new RedirectView(path);
    }
}

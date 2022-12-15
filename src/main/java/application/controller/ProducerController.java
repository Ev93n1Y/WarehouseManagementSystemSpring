package application.controller;

import application.model.dto.ProducerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import application.service.ProducerService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
@RequestMapping("/producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService service;

    @GetMapping
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("producers");
        result.addObject("producers", service.findAll());
        return result;
    }

    @PostMapping
    public RedirectView add(@ModelAttribute("producer") ProducerDto producer) {
        try {
            service.save(producer);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO check duplicate entries
        }
        return redirectToProducersList();
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam(name = "id") UUID id) {
        service.deleteById(id);
        return redirectToProducersList();
    }

    @GetMapping("/update")
    public ModelAndView editForm(@RequestParam(name = "id") UUID id) {
        ModelAndView result = new ModelAndView("editProducer");
        try{
            result.addObject("producer", service.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("producer") ProducerDto producer) {
        try {
            service.save(producer.getId(), producer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToProducersList();
    }

    private RedirectView redirectToProducersList() {
        return new RedirectView("/producers");
    }
}

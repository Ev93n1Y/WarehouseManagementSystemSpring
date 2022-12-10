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
public class ProducerController implements CrudController<ProducerDto> {
    private final ProducerService service;

    @GetMapping
    @Override
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("producers");
        result.addObject("producers", service.findAll());
        return result;
    }

    @PostMapping
    @Override
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
    @Override
    public RedirectView delete(@RequestParam(name = "id") UUID id) {
        service.deleteById(id);
        return redirectToProducersList();
    }

    @GetMapping("/update")
    @Override
    public RedirectView update(@RequestParam(name = "id") UUID id) {
        ProducerDto dto = new ProducerDto();
        //TODO required update form, temporary works with static new name
        dto.setName("some changed name1");
        service.save(id, dto);
        return redirectToProducersList();

    }

    private RedirectView redirectToProducersList() {
        return new RedirectView("/producers");
    }
}

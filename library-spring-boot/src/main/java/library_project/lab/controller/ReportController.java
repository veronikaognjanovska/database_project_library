
package library_project.lab.controller;

import library_project.lab.model.forms.NajchestoIznajmuvaniKnigiPoMesecForm;
import library_project.lab.model.forms.ProsechnoDocnenjeZaVrakanjeForm;
import library_project.lab.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final NajchestoIznajmuvaniKnigiPoMesecFormRepository najchestoIznajmuvaniKnigiPoMesecFormRepository;
    private final ProsechnoDocnenjeZaVrakanjeFormRepository prosechnoDocnenjeZaVrakanjeFormRepository;

    public ReportController(NajchestoIznajmuvaniKnigiPoMesecFormRepository najchestoIznajmuvaniKnigiPoMesecFormRepository, ProsechnoDocnenjeZaVrakanjeFormRepository prosechnoDocnenjeZaVrakanjeFormRepository) {
        this.najchestoIznajmuvaniKnigiPoMesecFormRepository = najchestoIznajmuvaniKnigiPoMesecFormRepository;
        this.prosechnoDocnenjeZaVrakanjeFormRepository = prosechnoDocnenjeZaVrakanjeFormRepository;
    }

    @GetMapping
    public String getIndexPage(Model model){
        return "redirect:/";
    }

    //-- Најчесто изнајмувани книги по месец *
    @GetMapping("/najchestoIznajmuvaniKnigiPoMesec")
    public String getNajchestoIznajmuvaniKnigiPoMesec(Model model) {

        List<NajchestoIznajmuvaniKnigiPoMesecForm> bookList = najchestoIznajmuvaniKnigiPoMesecFormRepository.selectAll();
        model.addAttribute("najchestoIznajmuvaniKnigiPoMesec",bookList);

        model.addAttribute("bodyContent","najchestoIznajmuvaniKnigiPoMesec");
        return "master-template";
    }

    //-- просечно доцнење со враќање по член после 2 недели *
    @GetMapping("/prosechnoDocnenjeZaVrakanje")
    public String getProsechnoDocnenjeZaVrakanje(Model model) {

        List<ProsechnoDocnenjeZaVrakanjeForm> bookList = prosechnoDocnenjeZaVrakanjeFormRepository.selectAll();
        model.addAttribute("prosechnoDocnenjeZaVrakanje",bookList);

        model.addAttribute("bodyContent","prosechnoDocnenjeZaVrakanje");
        return "master-template";
    }


}

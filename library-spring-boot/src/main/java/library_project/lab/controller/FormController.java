
package library_project.lab.controller;

import library_project.lab.model.Chlen;
import library_project.lab.model.Pozajmica;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.model.helpers.DateCustom;
import library_project.lab.repository.MomentalniPozajmiciViewRepository;
import library_project.lab.service.*;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forms")
public class FormController {


    private final ChlenService chlenService;
    private final VrabotenService vrabotenService;
    private final PozajmicaService pozajmicaService;
    private final MomentalniPozajmiciViewRepository momentalniPozajmiciViewRepository;

    public FormController(ChlenService chlenService, VrabotenService vrabotenService, PozajmicaService pozajmicaService, MomentalniPozajmiciViewRepository momentalniPozajmiciViewRepository) {
        this.chlenService = chlenService;
        this.vrabotenService = vrabotenService;
        this.pozajmicaService = pozajmicaService;
        this.momentalniPozajmiciViewRepository = momentalniPozajmiciViewRepository;
    }
    @GetMapping
    public String getIndexPage(Model model){
        return "redirect:/";
    }

    @GetMapping("/iznajmuvanje-form")
    public String getIznajmuvanjeFormPage( Model model){
        model.addAttribute("bodyContent","iznajmuvanje-form");
        model.addAttribute("chlenovi",chlenService.findAll());
        model.addAttribute("vraboteni",vrabotenService.findAll());
        return "master-template";
    }

    @PostMapping("/iznajmuvanje")
    public String postIznajmuvanjePage(@RequestParam Long seriski_broj,@RequestParam Long inventaren_broj,
                                       @RequestParam String chlenEMBG,@RequestParam String vrabotenEMBG){
        try{
            if( seriski_broj==null ||
                    inventaren_broj==null ||
                    chlenEMBG==null ||
                    vrabotenEMBG==null){
                throw new IllegalArgumentException();
            }
            Pozajmica pozajmica = pozajmicaService.save(
                    seriski_broj,
                    inventaren_broj,
                    chlenEMBG,
                    vrabotenEMBG);
            return "redirect:/";
        }catch (IllegalArgumentException | AlreadyExistsException | NotFound ex){
            return "redirect:/?error=Pozajmicata Ne Moze Da Se Napravi";
        }
    }




    @GetMapping("/vrakanje-form")
    public String getVrakanjeFormPage( Model model){
        model.addAttribute("bodyContent","vrakanje-form");
        model.addAttribute("chlenovi",chlenService.findAll());
        model.addAttribute("vraboteni",vrabotenService.findAll());
        model.addAttribute("pozajmiciActive", momentalniPozajmiciViewRepository.selectAll());
        return "master-template";
    }

    @PostMapping("/vrakanje")
    public String getVrakanjePage(@RequestParam Long seriski_broj,@RequestParam Long inventaren_broj,
                                  @RequestParam String chlenEMBG,@RequestParam String vrabotenEMBG,
                                  @RequestParam String date){
        try{
            if( seriski_broj==null || inventaren_broj==null || chlenEMBG==null ||
                    vrabotenEMBG==null || date==null){
                throw new IllegalArgumentException();
            }
            pozajmicaService.updateToClosed(seriski_broj,
                    inventaren_broj,
                    chlenEMBG,
                    vrabotenEMBG,
                    DateCustom.getZonedDateTimeFromDateString(date));//(String.format("%02d-%02d-%d",day,month,year))
            return "redirect:/";
        }catch (Exception ex){
            return "redirect:/?error=Pozajmicata Ne Moze Da Se Zatvori";
        }
    }




    @GetMapping("/zachlenuvanje-form")
    public String getZachlenuvanjeFormPage( Model model){
        model.addAttribute("bodyContent","zachlenuvanje-form");
        return "master-template";
    }

    @PostMapping("/zachlenuvanje")
    public String getZachlenuvanjePage(@RequestParam String embg,@RequestParam String ime,@RequestParam String prezime,
                                       @RequestParam String date,
                                       @RequestParam String adresa_na_ziveenje,@RequestParam String telefonski_broj
    ){
        try{
            if( embg==null || ime==null || prezime==null ||
                    adresa_na_ziveenje==null || telefonski_broj==null ||
                    date==null){
                throw new IllegalArgumentException();
            }
            Chlen chlen  = chlenService.save(embg,ime,prezime,
                    DateCustom.getZonedDateTimeFromDateString(date),
                    adresa_na_ziveenje,telefonski_broj,
                    DateCustom.getDateNow());

            return "redirect:/";
        }catch (Exception ex){
            return "redirect:/?error=Pozajmicata Ne Moze Da Se Zatvori";
        }
    }

}

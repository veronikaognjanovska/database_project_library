
package library_project.lab.controller;

import library_project.lab.model.Chlen;
import library_project.lab.model.Pozajmica;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.model.helpers.DateCustom;
import library_project.lab.service.*;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forms")
public class FormController {


    private final ChlenService chlenService;
    private final PozajmicaService pozajmicaService;

    public FormController(ChlenService chlenService, PozajmicaService pozajmicaService) {
        this.chlenService = chlenService;
        this.pozajmicaService = pozajmicaService;
    }
    @GetMapping
    public String getIndexPage(Model model){
        return "redirect:/";
    }

    @GetMapping("/iznajmuvanje-form")
    public String getIznajmuvanjeFormPage( Model model){
        model.addAttribute("bodyContent","iznajmuvanje-form");
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
        return "master-template";
    }

    @PostMapping("/vrakanje")
    public String getVrakanjePage(@RequestParam Long seriski_broj,@RequestParam Long inventaren_broj,
                                  @RequestParam String chlenEMBG,@RequestParam String vrabotenEMBG,
                                  @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year){
        try{
            if( seriski_broj==null || inventaren_broj==null || chlenEMBG==null ||
                    vrabotenEMBG==null || day==null || month==null || year==null){
                throw new IllegalArgumentException();
            }
            pozajmicaService.updateToClosed(seriski_broj,
                    inventaren_broj,
                    chlenEMBG,
                    vrabotenEMBG,
                    DateCustom.getZonedDateTimeFromDateString(String.format("%02d-%02d-%d",day,month,year)));
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
                                       @RequestParam Integer day,@RequestParam Integer month,@RequestParam Integer year,
                                       @RequestParam String adresa_na_ziveenje,@RequestParam String telefonski_broj
    ){
        try{
            if( embg==null || ime==null || prezime==null ||
                    adresa_na_ziveenje==null || telefonski_broj==null ||
                    day==null || month==null || year==null){
                throw new IllegalArgumentException();
            }
            Chlen chlen  = chlenService.save(embg,ime,prezime,
                    DateCustom.getZonedDateTimeFromDateString(String.format("%02d-%02d-%d",day,month,year)),
                    adresa_na_ziveenje,telefonski_broj,
                    DateCustom.getDateNow());

            return "redirect:/";
        }catch (Exception ex){
            return "redirect:/?error=Pozajmicata Ne Moze Da Se Zatvori";
        }
    }

}

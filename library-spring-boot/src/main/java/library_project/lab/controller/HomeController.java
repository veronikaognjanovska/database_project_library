package library_project.lab.controller;

import library_project.lab.model.Avtor;
import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import library_project.lab.model.helpers.DateCustom;
import library_project.lab.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ChovekService chovekService;
    private final VrabotenService vrabotenService;
    private final ChlenService  chlenService;
    private final AvtorService avtorService;
    private final NastanService nastanService;
    private final KnigaService knigaService ;
    private final PrimerokService   primerokService;
    private final PozajmicaService pozajmicaService;

    public HomeController(ChovekService chovekService, VrabotenService vrabotenService, ChlenService chlenService, AvtorService avtorService, NastanService nastanService, KnigaService knigaService, PrimerokService primerokService, PozajmicaService pozajmicaService) {
        this.chovekService = chovekService;
        this.vrabotenService = vrabotenService;
        this.chlenService = chlenService;
        this.avtorService = avtorService;
        this.nastanService = nastanService;
        this.knigaService = knigaService;
        this.primerokService = primerokService;
        this.pozajmicaService = pozajmicaService;
    }


    @GetMapping
    public String getIndexPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String success,Model model){

//         try {
////             Chovek chovek  = chovekService.save("1212999000455","name","surname",
////                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999");
////             String name = chovek.getIme();
////
////             Vraboten vraboten  = vrabotenService.save("1412999000455","name","surname",
////                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999",DateCustom.getZonedDateTimeFromDateString("01-05-2019"));
////             String namev = vraboten.getIme();
////
////             Chlen chlen  = chlenService.save("1312999000455","name","surname",
////                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999",DateCustom.getZonedDateTimeFromDateString("01-05-2019"));
////             String namecl = chlen.getIme();
////
////             Avtor avtor = avtorService.save("name", "surname", 1900);
////             Nastan nastan = nastanService.save(DateCustom.getZonedDateTimeFromDateString("01-05-2020"), "1412999000455");
//
////             Kniga kniga = knigaService.save("naslov", 150, nastan);
////             String naslov=kniga.getNaslov();
////             knigaService.addAvtorToKniga(avtor.getAvtor_id(),kniga.getSeriski_broj());
//
////             Primerok primerok = primerokService.save(kniga.getSeriski_broj(), STATUS_PRIMEROK.AVAILABLE);
////             Pozajmica pozajmica = pozajmicaService.save(kniga.getSeriski_broj(),primerok.getPrimerokKey().getInventaren_broj(),
////                     chlen.getEmbg(), vraboten.getEmbg());
////             pozajmicaService.updateToClosed(pozajmica.getPozajmicaKey().getSeriski_broj(),
////                     pozajmica.getPozajmicaKey().getInventaren_broj(),
////                     pozajmica.getPozajmicaKey().getChlenEMBG().getEmbg(),
////                     pozajmica.getPozajmicaKey().getVrabotenEMBG().getEmbg(),
////                     pozajmica.getPozajmicaKey().getDatumPozajmuvanje());
//
//        }catch (Exception e){
//             String error = e.getMessage();
//        }

        if(chovekService.findAll().size()<=0){
            try {
            Vraboten vraboten  = vrabotenService.save("1412999000455","Vraboten1","VrabotenSurname",
                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999",DateCustom.getZonedDateTimeFromDateString("01-05-2019"));
            Avtor avtor = avtorService.save("Avtor1", "AvtorSurname", 1900);
            Nastan nastan = nastanService.save(DateCustom.getZonedDateTimeFromDateString("01-05-2020"), "1412999000455");
            }catch (Exception e){
                 String errorException = e.getMessage();
            }
        }

        if(success!=null && !success.isEmpty()){
            model.addAttribute("hasSuccess",true);
            model.addAttribute("success",success);
        }
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","index");
        return "master-template";
    }


}

package library_project.lab.controller;

import library_project.lab.model.*;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import library_project.lab.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.ZonedDateTime;

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

    public HomeController(ChovekService chovekService, VrabotenService vrabotenService, ChlenService chlenService,
                          AvtorService avtorService, NastanService nastanService, KnigaService knigaService, PrimerokService primerokService, PozajmicaService pozajmicaService) {
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
    public String getIndexPage(){

         try {
             Chovek chovek  = chovekService.save("1212999000455","name","surname",
                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999");
             String name = chovek.getIme();

             Vraboten vraboten  = vrabotenService.save("1412999000455","name","surname",
                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999",DateCustom.getZonedDateTimeFromDateString("01-05-2019"));
             String namev = vraboten.getIme();

             Chlen chlen  = chlenService.save("1312999000455","name","surname",
                     DateCustom.getZonedDateTimeFromDateString("01-05-1990"),"address","077888999",DateCustom.getZonedDateTimeFromDateString("01-05-2019"));
             String namecl = chlen.getIme();

             Avtor avtor = avtorService.save("name", "surname", 1900);
             Nastan nastan = nastanService.save(DateCustom.getZonedDateTimeFromDateString("01-05-2020"), "1412999000455");

             Kniga kniga = knigaService.save("naslov", 150, nastan);
             String naslov=kniga.getNaslov();
             knigaService.addAvtorToKniga(avtor.getAvtor_id(),kniga.getSeriski_broj());

             Primerok primerok = primerokService.save(kniga.getSeriski_broj(), STATUS_PRIMEROK.AVAILABLE);
             Pozajmica pozajmica = pozajmicaService.save(kniga.getSeriski_broj(),primerok.getPrimerokKey().getInventaren_broj(),
                     chlen.getEmbg(), vraboten.getEmbg());
             pozajmicaService.updateToClosed(pozajmica.getPozajmicaKey().getSeriski_broj(),
                     pozajmica.getPozajmicaKey().getInventaren_broj(),
                     pozajmica.getPozajmicaKey().getChlenEMBG().getEmbg(),
                     pozajmica.getPozajmicaKey().getVrabotenEMBG().getEmbg(),
                     pozajmica.getPozajmicaKey().getDatumPozajmuvanje());

        }catch (Exception e){
             String error = e.getMessage();
        }
        return "index";
    }

}

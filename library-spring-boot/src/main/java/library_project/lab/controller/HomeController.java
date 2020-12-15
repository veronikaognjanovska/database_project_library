package library_project.lab.controller;

import library_project.lab.model.*;
import library_project.lab.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ChovekService chovekService;
    private final VrabotenService vrabotenService;
    private final ChlenService  chlenService;
    private final AvtorService avtorService;
    private final NastanService nastanService;
    private final KnigaService knigaService ;

    public HomeController(ChovekService chovekService, VrabotenService vrabotenService, ChlenService chlenService,
                          AvtorService avtorService, NastanService nastanService, KnigaService knigaService) {
        this.chovekService = chovekService;
        this.vrabotenService = vrabotenService;
        this.chlenService = chlenService;
        this.avtorService = avtorService;
        this.nastanService = nastanService;
        this.knigaService = knigaService;
    }



    @GetMapping
    public String getIndexPage(){

//        Chovek c  = chovekService.save("1212999000455","name","surname",
//                Date.valueOf("2020-09-09"),"address","077888999");
//        String name = c.getIme();
//        Vraboten v  = vrabotenService.save("1412999000455","name","surname",
//                Date.valueOf("2020-09-09"),"address","077888999",Date.valueOf("2020-09-09"));
//        Date d = (Date) v.getDatum_na_vrabotuvanje();
//        String namev = v.getIme();


//        Chlen cl  = chlenService.save("1312999000455","name","surname",
//                Date.valueOf("2020-09-09"),"address","077888999",Date.valueOf("2020-09-09"));
//        Date dcl = (Date) cl.getDatum_na_zachlenuvanje();
//        String namecl = cl.getIme();

         try {
//            Avtor avtor = avtorService.save("name", "surname", 1900);
//            Nastan nastan = nastanService.save(Date.valueOf("2020-09-09"), vrabotenService.findByEmbg("1412999000455"));
//            Kniga kniga = knigaService.save("naslov", 150, nastan);



        }catch (Exception e){

        }
        return "index";
    }

}

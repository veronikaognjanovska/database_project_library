package library_project.lab.controller;

import library_project.lab.model.Chovek;
import library_project.lab.service.ChovekService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ChovekService chovekService;

    public HomeController(ChovekService chovekService) {
        this.chovekService = chovekService;
    }



    @GetMapping
    public String getIndexPage(){

//        Chovek c = new Chovek("1212999000455","name","surname", Date.valueOf("2020-09-09"),"address","077888999");
        Chovek c  = chovekService.save("1212999000455","name","surname",
                Date.valueOf("2020-09-09"),"address","077888999");
        String name = c.getIme();
        return "index";
    }

}

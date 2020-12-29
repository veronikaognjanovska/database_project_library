package library_project.lab.controller;


import library_project.lab.model.views.DostapnostNaKnigiView;
import library_project.lab.model.views.MomentalniPozajmiciView;
import library_project.lab.model.views.PregledNaSiteKnigiView;
import library_project.lab.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/views")
public class ViewController {

    private final PregledNaSiteKnigiViewRepository pregledNaSiteKnigiViewRepository;
    private final DostapnostNaKnigiViewRepository dostapnostNaKnigiViewRepository;
    private final MomentalniPozajmiciViewRepository momentalniPozajmiciViewRepository;

    public ViewController(PregledNaSiteKnigiViewRepository pregledNaSiteKnigiViewRepository, DostapnostNaKnigiViewRepository dostapnostNaKnigiViewRepository, MomentalniPozajmiciViewRepository momentalniPozajmiciViewRepository) {
        this.pregledNaSiteKnigiViewRepository = pregledNaSiteKnigiViewRepository;
        this.dostapnostNaKnigiViewRepository = dostapnostNaKnigiViewRepository;
        this.momentalniPozajmiciViewRepository = momentalniPozajmiciViewRepository;
    }


    @GetMapping
    public String getIndexPage(Model model){
        return "redirect:/";
    }

    //-- Прегед на сите книги *
    @GetMapping("/bookAllList")
    public String getBookAllList(Model model) {

        List<PregledNaSiteKnigiView> bookList = pregledNaSiteKnigiViewRepository.selectAll();
        model.addAttribute("bookAllList",bookList);

        model.addAttribute("bodyContent","bookAllList");
        return "master-template";
    }

    //--  книги i kolku se dostapni *
    @GetMapping("/bookNumberList")
    public String getBookNumberList(Model model) {

        List<DostapnostNaKnigiView> bookList = dostapnostNaKnigiViewRepository.selectAll();
        model.addAttribute("bookNumberList",bookList);

        model.addAttribute("bodyContent","bookNumberList");
        return "master-template";
    }

    //-- Преглед на моментални сите позајмици - koi se available *
    @GetMapping("/momentalniPozajmiciList")
    public String getMomentalniPozajmiciList(Model model) {

        List<MomentalniPozajmiciView> bookList = momentalniPozajmiciViewRepository.selectAll();
        model.addAttribute("momentalniPozajmiciList",bookList);

        model.addAttribute("bodyContent","momentalniPozajmiciList");
        return "master-template";
    }



}

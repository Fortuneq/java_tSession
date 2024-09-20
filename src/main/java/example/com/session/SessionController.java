package example.com.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SessionController {

    @Autowired
    private SessionService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Session> listSessions = service.listAll(keyword);
        model.addAttribute("listSessions", listSessions);  // Правильное имя атрибута
        model.addAttribute("keyword", keyword);
        return "index";
    }


    @RequestMapping("/new")
    public String showNewSessionForm(Model model){
        Session session = new Session();
        model.addAttribute("tSession", session);
        return "new_session";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSession(@ModelAttribute("tSession") Session session) {
            service.save(session);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditSessionForm(@PathVariable(name="id") Long id){
        ModelAndView mav = new ModelAndView("edit_session");
        Session session = service.get(id);
        mav.addObject("tSession", session);
        return mav;
    }

    // Метод для сортировки по дате
    @RequestMapping("/sortByDate")
    public String sortByDate(Model model, @Param("keyword") String keyword) {
        List<Session> sortedSessions = service.listAll(keyword)
                .stream()
                .sorted(Comparator.comparing(Session::getSessionDateTime))
                .collect(Collectors.toList());
        model.addAttribute("listSessions", sortedSessions);
        return "index";
    }


    @RequestMapping("/delete/{id}")
    public String deleteSession(@PathVariable(name="id") Long id){
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/filter")
    public String filterByDate(Model model, @RequestParam("date") String date){
        LocalDateTime filterDate = LocalDateTime.parse(date);
        List<Session> filteredSessions = service.filterByDate(filterDate);
        model.addAttribute("listSessions", filteredSessions);
        return "index";
    }
}

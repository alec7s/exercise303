package com.ae.exercise303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listTodos(Model model){
        model.addAttribute("todos", toDoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new ToDo());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid ToDo toDo, BindingResult result){
        if (result.hasErrors()){
            return "todoform";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", toDoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", toDoRepository.findById(id).get());
        return "todoform";
    }
    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id){
        toDoRepository.deleteById(id);
        return "redirect:/";
    }
}

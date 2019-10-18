package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/")
    public String Main(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("accounts", accountRepository.findAll());
        return "home";
    }
    @RequestMapping("/accountlist")
    public String transactionList(Model model){
        model.addAttribute("accounts", accountRepository.findAll());
        return "accountlist";
    }
    @GetMapping("/addaccount")
    public String accountForm(Model model){
        model.addAttribute("account", new Account());
        model.addAttribute("cutomers", customerRepository.findAll());
        return "loginform";
    }
    @PostMapping("/processaccount")
    public String processForm(@Valid Account account,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "loginform";
        }
        accountRepository.save(account);
        return "redirect:/";
    }
    @RequestMapping("customerlist")
    public String customerList(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "customerlist";
    }
    @GetMapping("addcustomer")
    public String registrationForm(Model model){
        model.addAttribute("customer", new Customer());
        return "registrationform";
    }
    @PostMapping("/processcustomer")
    public String loginForm(@Valid Customer customer,
                               BindingResult result) {
        if (result.hasErrors()){
            return "registrationform";
        }
        customerRepository.save(customer);
        return "redirect:/";
    }


}

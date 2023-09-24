package com.example.Session10.Controller;

import com.example.Session10.model.Customer;
import com.example.Session10.service.CustomerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class HomeController {
    @Autowired
    private CustomerSevice customerSevice;

    @Autowired
    private ServletContext context;

    @GetMapping()
    public String listCustomers(ModelMap modelMap) {
        modelMap.addAttribute("key", "value");
        modelMap.addAttribute("data", customerSevice.findAll());
        return "customer/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerSevice.findId(id));
        return "customer/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        customerSevice.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/create")
    public String showCreateForm(ModelMap modelMap) {
        modelMap.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @GetMapping("/search")
    public String searchCustomers(Model model,@RequestParam(name = "search",required = false) String search){
        List<Customer> data;
        if(search != null && !search.isEmpty()){
            data = customerSevice.findName(search);
        }
        else {
            data = customerSevice.findAll();
        }
        if (data.isEmpty()) {
            model.addAttribute("message", "No user found.");
            data = customerSevice.findAll();
        }
        model.addAttribute("data",data);
        return"customer/index";
    }





    @PostMapping("/create")
    public String add(@ModelAttribute Customer customer, ModelMap modelMap) {
        String pathFolder = "/uploads/";
        String path = context.getRealPath("/WEB-INF/") + pathFolder;
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
        if (!customer.getImage().isEmpty()) {
            Path fileUploaded = Paths.get(path + customer.getImage().getOriginalFilename());
            try {
                Files.write(fileUploaded, customer.getImage().getBytes());
                customer.setAvatar(pathFolder + customer.getImage().getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customerSevice.add(customer);
        return "redirect:/customer";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Customer customer, ModelMap modelMap) {
        // Chỉ định thư mục
        String pathFolder = "/uploads/";
        String path = context.getRealPath("/WEB-INF/") + pathFolder;
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
        if (!customer.getImage().isEmpty()) {
            Path fileUploaded = Paths.get(path + customer.getImage().getOriginalFilename());
            try {
                Files.write(fileUploaded, customer.getImage().getBytes());
                customer.setAvatar(pathFolder + customer.getImage().getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customerSevice.edit(customer);
        return "redirect:/customer";
    }

}
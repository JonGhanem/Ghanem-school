package com.ghanem.school.controller;

import com.ghanem.school.model.Contact;
import com.ghanem.school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping({"/contact"})
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

/*    @RequestMapping(value="/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(@RequestParam String name,
                                    @RequestParam String mobileNum,
                                    @RequestParam String email,
                                    @RequestParam String subject,
                                    @RequestParam String message){
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/contact");
    }*/

    @RequestMapping(value="/saveMsg", method = RequestMethod.POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping(value = "/displayMessages",method = RequestMethod.GET)
    public ModelAndView displayMessage(){
        List<Contact> contactMsg = contactService.findMsgWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactMsg);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }
}

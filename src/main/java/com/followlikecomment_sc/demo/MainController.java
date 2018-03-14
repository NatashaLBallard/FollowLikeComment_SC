package com.followlikecomment_sc.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;




    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }


    @RequestMapping("/user")
    public String userIndex(Model model, Authentication auth){

        User newUser = new User();
        return "user";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public class LoginServlet extends HttpServlet {
//
//        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            String roleType = request.getParameter("roleType");
//
//            System.out.println("role type: " + roleType);
//        }
//
//    }


//    @RequestMapping(value = "/register/{id}")
//    public String roleType (Model model, @PathVariable("id")) String assignedRoleType){
//
//    }





    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage( @Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {
//
//        model.addAttribute("roleType", userRepository.findOne(user.getId()));
//        User roleType = userRepository.findOne(id)
//        userRepository.save(roleType);
//        System.out.println(user.getRoleType());
//

// add if statement to set value of options "if roleType (from dropdown) equals APPLICANT, set to applicant, else.."
//        model.addAttribute("user", user);

        String thePassword = newUser.getPassword();

        if (result.hasErrors()) {
            return "registration";
        } else {
//            user.addRole(roleRepository.findRoleClassByRoleName("USER"));
//            user.setRoles(roleRepository.findByRole("USER"));


            newUser.setPassword(passwordEncoder.encode(thePassword));
            userService.saveUser(newUser);
            newUser.addRole(roleRepository.findRoleClassByRoleName("USER"));


//            model.addAttribute("user", new User());
//            model.addAttribute("message",
//                    "User Account Successfully Created");
            System.out.println("New user created.");
//            System.out.println(user.getRoleType());
        }
        return "userIndex";
//
//
//        user.setRoleType(roleType;
//        userRepository.save(roleType);

//        model.addAttribute("roleType",roleRepository);
//        String roleType=roleType.getRoleType;

    }





//    @RequestMapping("/user")
//    public String user(){
//        return "user";
//    }
//
//    @RequestMapping("/admin")
//    public String admin(){
//        return "admin";
//    }

}
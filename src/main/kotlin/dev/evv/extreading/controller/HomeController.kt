package dev.evv.extreading.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/home")
    fun home(): String =
        "welcome to home"

    @GetMapping("/home2")
    fun home2(): String {
        return "welcome to home2"
    }

}
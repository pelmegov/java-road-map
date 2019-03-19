package ru.pelmegov.javaroadmap.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.pelmegov.javaroadmap.domain.Greeting
import ru.pelmegov.javaroadmap.service.ImageCreator
import kotlin.random.Random

@RestController
class SimpleDockerRunnerController(
    private val imageCreator: ImageCreator
) {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) {
        Greeting(Random.nextLong(), name)
    }

    @GetMapping("/images/create")
    fun createImage(): String {
        return imageCreator.createDockerImage("classpath:dockerfiles/module1/lesson1/", "module1-lesson1")
    }

}
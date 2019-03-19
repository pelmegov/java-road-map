package ru.pelmegov.javaroadmap.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.pelmegov.javaroadmap.domain.Greeting
import ru.pelmegov.javaroadmap.service.ContainerRunnerService
import ru.pelmegov.javaroadmap.service.ImageCreatorService
import kotlin.random.Random


@RestController
class SimpleDockerRunnerController(
    private val imageCreatorService: ImageCreatorService,
    private val containerRunnerService: ContainerRunnerService
) {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) {
        Greeting(Random.nextLong(), name)
    }

    @GetMapping("/images/create")
    fun createImage(): String {
        return imageCreatorService.createDockerImage("classpath:dockerfiles/module1/lesson1/", "module1-lesson1")
    }

    @GetMapping("/container/run")
    fun runContainer(@RequestParam(value = "imageName") imageName: String): String {
        val createdContainerId = containerRunnerService.createContainer(imageName)
        containerRunnerService.runContainer(createdContainerId)

        // some actions

        return createdContainerId
    }

}


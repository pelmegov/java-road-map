package ru.pelmegov.javaroadmap.controller

import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.ProgressHandler
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.pelmegov.javaroadmap.domain.Greeting
import java.nio.file.Paths
import java.util.concurrent.atomic.AtomicReference
import kotlin.random.Random
import org.springframework.core.io.ResourceLoader
import org.springframework.beans.factory.annotation.Autowired


@RestController
class SimpleDockerRunnerController {

    @Autowired
    private val resourceLoader: ResourceLoader? = null

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) {
        Greeting(Random.nextLong(), name)
    }

    @GetMapping("/images/create")
    fun createImage(): String {
        return createDockerImage("classpath:dockerfiles/module1/lesson1/")
    }

    private fun createDockerImage(dockerDirectory: String): String {
        val docker = DefaultDockerClient.fromEnv().build()

        val imageIdFromMessage = AtomicReference<String>()
        return docker.build(
            Paths.get(
                resourceLoader?.getResource(dockerDirectory)?.uri
            ),
            "test",
            ProgressHandler { message ->
                val imageId = message.buildImageId()
                if (imageId != null) {
                    imageIdFromMessage.set(imageId)
                }
            })
    }
}
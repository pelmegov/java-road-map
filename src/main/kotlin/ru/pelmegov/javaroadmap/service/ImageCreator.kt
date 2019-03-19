package ru.pelmegov.javaroadmap.service

import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.ProgressHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.nio.file.Paths
import java.util.concurrent.atomic.AtomicReference

@Service
class ImageCreator {

    @Autowired
    private val resourceLoader: ResourceLoader? = null

    private val dockerClient = DefaultDockerClient.fromEnv().build()

    fun createDockerImage(dockerDirectory: String, imageName: String): String {
        val resource = resourceLoader?.getResource(dockerDirectory)

        val imageIdFromMessage = AtomicReference<String>()
        return dockerClient.build(
            Paths.get(resource?.uri), imageName,
            ProgressHandler { message ->
                val imageId = message.buildImageId()
                if (imageId != null) {
                    imageIdFromMessage.set(imageId)
                }
            })
    }

}
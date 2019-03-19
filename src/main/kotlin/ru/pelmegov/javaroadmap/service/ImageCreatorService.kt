package ru.pelmegov.javaroadmap.service

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.ProgressHandler
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.nio.file.Paths
import java.util.concurrent.atomic.AtomicReference

@Service
class ImageCreatorService(
    private val resourceLoader: ResourceLoader,
    private val dockerClient: DockerClient
) {

    fun createDockerImage(dockerDirectory: String, imageName: String): String {
        val resource = resourceLoader.getResource(dockerDirectory)

        val imageIdFromMessage = AtomicReference<String>()
        return dockerClient.build(
            Paths.get(resource.uri), imageName,
            ProgressHandler { message ->
                val imageId = message.buildImageId()
                if (imageId != null) {
                    imageIdFromMessage.set(imageId)
                }
            })
    }

}
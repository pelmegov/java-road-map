package ru.pelmegov.javaroadmap.service

import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.HostConfig
import com.spotify.docker.client.messages.PortBinding
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContainerRunnerService(private val dockerClient: DockerClient) {

    fun createContainer(imageName: String): String {
        val randomPort = ArrayList<PortBinding>()
        randomPort.add(PortBinding.randomPort("localhost"))

        val portBindings = HashMap<String, List<PortBinding>>()
        portBindings["443"] = randomPort

        val containerConfig = ContainerConfig.builder()
            .hostConfig(HostConfig.builder().build())
            .image(imageName)
            .cmd("sh", "-c", "while :; do sleep 1; done")
            .build()

        val creation = dockerClient.createContainer(containerConfig)
        return creation.id()!!
    }

    fun runContainer(containerId: String) {
        dockerClient.startContainer(containerId)
    }

}
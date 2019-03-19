package ru.pelmegov.javaroadmap.config

import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.DockerClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DockerClientConfig {

    @Bean
    open fun dockerClient(): DockerClient {
        return DefaultDockerClient.fromEnv().build()
    }

}
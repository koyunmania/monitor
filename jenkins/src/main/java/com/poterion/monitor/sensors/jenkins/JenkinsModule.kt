package com.poterion.monitor.sensors.jenkins

import com.poterion.monitor.api.controllers.ControllerInterface
import com.poterion.monitor.api.modules.ServiceModule
import com.poterion.monitor.data.Config
import com.poterion.monitor.sensors.jenkins.data.JenkinsConfig
import com.poterion.monitor.sensors.jenkins.control.JenkinsService
import kotlin.reflect.KClass

object JenkinsModule : ServiceModule<JenkinsConfig, JenkinsService> {
	override val configClass: KClass<JenkinsConfig> = JenkinsConfig::class
	override fun createControllers(controller: ControllerInterface, config: Config): Collection<JenkinsService> = config
			.services
			.filter { it is JenkinsConfig }
			.map { it as JenkinsConfig }
			.map { JenkinsService(controller, it) }
}
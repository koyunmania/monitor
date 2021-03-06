package com.poterion.monitor

import com.poterion.monitor.control.Controller
import com.poterion.monitor.notifiers.raspiw2812.RaspiW2812Module
import com.poterion.monitor.notifiers.tray.SystemTrayModule
import com.poterion.monitor.sensors.jenkins.JenkinsModule
import com.poterion.monitor.sensors.sonar.SonarModule
import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage

/*
 * @startuml
 * package Core {
 *   interface Data
 *   interface API
 *   abstract class UI
 *   class Control
 * }
 *
 * class Jenkins <<Service>>
 * class Sonar <<Service>>
 *
 * class "System Tray" as SystemTray <<Notifier>>
 * class "Raspi W2812" as RaspiW2812 <<Notifier>>
 *
 * Data <|-- API
 * API <|-left- Control
 * API <|-- RaspiW2812
 * API <|-- SystemTray
 * API <|-right- UI
 * API <|-- Jenkins
 * API <|-- Sonar
 *
 * UI <|-- SystemTray
 *
 * Control <|-- Assembly
 * Jenkins <|-- Assembly
 * Sonar <|-- Assembly
 * SystemTray <|-- Assembly
 * RaspiW2812 <|-- Assembly
 *
 * @enduml
 */
/**
 * @author Jan Kubovy <jan@kubovy.eu>
 */
class Main : Application() {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			launch(Main::class.java, *args)
		}
	}

	override fun start(primaryStage: Stage) {
		Platform.setImplicitExit(false)

		val controller = Controller(primaryStage).apply {
			registerModule(JenkinsModule)
			registerModule(SonarModule)
			registerModule(RaspiW2812Module)
			registerModule(SystemTrayModule)
		}
		controller.start()
	}
}



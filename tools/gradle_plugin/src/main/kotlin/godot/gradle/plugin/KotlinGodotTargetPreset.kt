package godot.gradle.plugin

import godot.gradle.plugin.KotlinGodotPlugin.Companion.LibrariesDependency
import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.gradle.internal.cleanup.BuildOutputCleanupRegistry
import org.jetbrains.kotlin.backend.common.onlyIf
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinTargetPreset
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetPreset
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeOutputKind
import org.jetbrains.kotlin.konan.target.HostManager
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.io.File


class KotlinGodotTargetPreset(
        private val name: String,
        val project: Project,
        val konanTarget: KonanTarget,
        kotlinPluginVersion: String,
        private val sourceSetsInformation: MutableMap<KotlinSourceSet, GodotSourceSetInformation>
) : KotlinTargetPreset<KotlinNativeTarget> {

    private val nativePreset = KotlinNativeTargetPreset(name, project, konanTarget, kotlinPluginVersion)
    private val kotlin = project.extensions.getByName("kotlin") as KotlinMultiplatformExtension


    override fun getName(): String = name


    override fun createTarget(name: String): KotlinNativeTarget {
        val target = nativePreset.createTarget(name)

        target.compilations.all { compilation ->
            compilation.apply {
                dependencies { implementation(LibrariesDependency) }
                outputKinds = mutableListOf(NativeOutputKind.DYNAMIC)
                addGeneratorTasks()
            }
        }
        return target
    }


    private fun KotlinNativeCompilation.addGeneratorTasks() {
        for (sourceSet in kotlinSourceSets) {
            val entryPath = project.buildDir.absolutePath + "/godot/entries/" + sourceSet.name
            sourceSet.kotlin.srcDir(entryPath)


            val info: GodotSourceSetInformation
            if (!sourceSetsInformation.contains(sourceSet)) {
                info = GodotSourceSetInformation(project.projectDir.absolutePath + File.separator, sourceSet)
                sourceSetsInformation[sourceSet] = info
            } else
                info = sourceSetsInformation[sourceSet]!!


            val generateTask = project.tasks.create(sourceSet.name + "GenerateEntry") {
                it.inputs.files(info.configs)
                it.outputs.dir(entryPath)

                it.doFirst {
                    if (info.configs.isEmpty())
                        project.logger.warn("$sourceSet has no configs for Godot registration - you may not access Kotlin classes from Godot.")
                    else {
                        var libPath = if (info.libraryPath == "") "${sourceSet.name}.gdnlib" else info.libraryPath
                        if (!libPath.startsWith("res://"))
                            libPath = "res://$libPath"

                        try {
                            generateEntry(info.configs, entryPath + File.separator + "Entry.kt", info.gdnsPath, libPath)
                            project.logger.info("Generated entry file for godot $sourceSet.")
                        } catch (e: Exception) {
                            val capture = "Failed to generate entry file for godot $sourceSet ($e)"
                            throw InvalidUserDataException(capture, e)
                        }
                    }
                }
            }
            project.getTasksByName(compileKotlinTaskName, false).forEach {
                it.dependsOn(generateTask.apply {
                    onlyIf {
                        HostManager().isEnabled(konanTarget) && !info.skipEntryGeneration
                    }
                })
            }
        }
    }
}

/*

internal fun lowerCamelCaseName(vararg nameParts: String?): String {
    val nonEmptyParts = nameParts.mapNotNull { it?.takeIf(String::isNotEmpty) }
    return nonEmptyParts.drop(1).joinToString(
            separator = "",
            prefix = nonEmptyParts.firstOrNull().orEmpty(),
            transform = String::capitalize
    )
}
internal val KotlinCompilation.defaultSourceSetName: String
    get() = lowerCamelCaseName(target.disambiguationClassifier, compilationName)*/
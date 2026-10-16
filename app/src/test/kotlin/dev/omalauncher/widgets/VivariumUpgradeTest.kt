package dev.omalauncher.widgets

import java.io.File
import java.nio.file.Files
import org.junit.Assert.*
import org.junit.Test

class VivariumUpgradeTest {
    private fun original() = javaClass.getResourceAsStream("/widgets/vivarium-before-pinch.owl")!!.use { it.readBytes() }

    @Test fun upgradesOnlyKnownPrototypeAndPreservesBackupAndState() {
        val dir = Files.createTempDirectory("vivarium-test").toFile()
        try {
            val file = File(dir, "52-vivarium.owl").apply { writeBytes(original()) }
            val state = File(dir, "residents.json").apply { writeText("saved inhabitants") }
            val replacement = "pinch enabled".toByteArray()
            VivariumUpgrade.install(dir, replacement)
            assertArrayEquals(replacement, file.readBytes())
            assertArrayEquals(original(), File(dir, ".backups/52-vivarium-before-pinch.owl").readBytes())
            assertEquals("saved inhabitants", state.readText())
            VivariumUpgrade.install(dir, "different replacement".toByteArray())
            assertArrayEquals(replacement, file.readBytes())
        } finally { dir.deleteRecursively() }
    }

    @Test fun leavesEditedAndDeletedWidgetsAlone() {
        val dir = Files.createTempDirectory("vivarium-test").toFile()
        try {
            val file = File(dir, "52-vivarium.owl")
            VivariumUpgrade.install(dir, "new".toByteArray())
            assertFalse(file.exists())
            val edited = original() + "\n// further edits".toByteArray()
            file.writeBytes(edited)
            VivariumUpgrade.install(dir, "new".toByteArray())
            assertArrayEquals(edited, file.readBytes())
            assertFalse(File(dir, ".backups").exists())
        } finally { dir.deleteRecursively() }
    }
}

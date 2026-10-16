package dev.omalauncher.widgets

import dev.omalauncher.upgrade.writeAtomically
import java.io.File
import java.security.MessageDigest

/** Upgrade only the exact Vivarium prototype this build replaces; leave later edits alone. */
internal object VivariumUpgrade {
    private const val BEFORE = "d149e074b93daf06e7691c9299046f09713a021fd2784e05ebfa85526abbd25c"

    fun install(dir: File, replacement: ByteArray) {
        val file = File(dir, "52-vivarium.owl")
        if (!file.isFile) return
        val original = file.readBytes()
        val hash = MessageDigest.getInstance("SHA-256").digest(original).joinToString("") { "%02x".format(it) }
        if (hash != BEFORE) return
        val backup = File(dir, ".backups/52-vivarium-before-pinch.owl")
        if (!backup.exists()) writeAtomically(backup, original)
        writeAtomically(file, replacement)
    }
}

package de.asdfghasdfgh.kotozute.signalstore

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The pinned Signal trust store has to be where the code looks for it.
 *
 * Sending, typing indicators and every other request to Signal build their TLS connection
 * through [SignalNetworkConfig]'s [org.signal.network.config.TrustStore]. When the store's
 * path was an absolute package name, moving the app to a new package moved the file but left
 * the string behind; the app carried on connecting to nothing and every send failed with
 * "whisper.store is missing from the APK" while the file was in the APK.
 *
 * The lookup here is the same package-relative one the code makes, so a future move that
 * forgets the resource fails a test instead of a person's message.
 */
class SignalNetworkConfigTest {

    @Test
    fun `the pinned trust store is packaged beside the configuration`() {
        val store = SignalNetworkConfig::class.java.getResourceAsStream("whisper.store")
        assertNotNull("whisper.store is missing from the APK", store)
        store!!.use { assertTrue("whisper.store is empty", it.read() >= 0) }
    }
}

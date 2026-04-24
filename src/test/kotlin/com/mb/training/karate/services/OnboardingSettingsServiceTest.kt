package com.mb.training.karate.services

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OnboardingSettingsServiceTest {

    @Test
    fun `active pack id is normalized on write and read`() {
        val service = OnboardingSettingsService()

        assertNull(service.getActivePackId())

        service.setActivePackId("  karate-pack  ")
        assertEquals("karate-pack", service.getActivePackId())

        service.setActivePackId("   ")
        assertNull(service.getActivePackId())
    }

    @Test
    fun `pack onboarding suppression can be toggled`() {
        val service = OnboardingSettingsService()
        val packId = "karate-pack"

        assertFalse(service.isPackOnboardingSuppressed(packId))
        service.setPackOnboardingSuppressed(packId, suppressed = true)
        assertTrue(service.isPackOnboardingSuppressed(packId))
        service.setPackOnboardingSuppressed(packId, suppressed = false)
        assertFalse(service.isPackOnboardingSuppressed(packId))
    }

    @Test
    fun `session flags are one-time until app restart`() {
        val service = OnboardingSettingsService()

        assertTrue(service.canShowPackSelectorThisSession())
        service.markPackSelectorShownThisSession()
        assertFalse(service.canShowPackSelectorThisSession())

        val packId = "karate-pack"
        assertTrue(service.canShowPackOnboardingThisSession(packId))
        service.markPackOnboardingShownThisSession(packId)
        assertFalse(service.canShowPackOnboardingThisSession(packId))
    }
}

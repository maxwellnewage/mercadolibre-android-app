package com.maxwell.mercadolibredemo.network.search

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchUnitTest {

    @Test
    fun validateTerm_ReturnsTrue() {
        assertTrue(SearchTest.validateTerm("motorola"))
    }

    @Test
    fun validateTerm_empty_ReturnsFalse() {
        assertFalse(SearchTest.validateTerm(""))
    }

    @Test
    fun validateTerm_null_ReturnsFalse() {
        assertFalse(SearchTest.validateTerm(null))
    }

    @Test
    fun validateTerm_length_ReturnsFalse() {
        assertFalse(SearchTest.validateTerm("mo"))
    }

    @Test
    fun validateResponseCode_200_ReturnsTrue() {
        assertTrue(SearchTest.validateResponseCode("motorola"))
    }

    @Test
    fun validateProducts_empty_ReturnsFalse() {
        assertFalse(SearchTest.validateProducts("???"))
    }

    @Test
    fun validateProducts_ReturnsTrue() {
        assertTrue(SearchTest.validateProducts("motorola"))
    }
}
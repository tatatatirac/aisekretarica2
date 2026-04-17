package com.aisecretary.app

import com.aisecretary.app.nlp.MessageCategorizer
import org.junit.Assert.assertEquals
import org.junit.Test

class MessageCategorizerTest {
    @Test fun testUrgent() { assertEquals("urgent", MessageCategorizer.categorize("This is urgent please call")) }
    @Test fun testSales() { assertEquals("sales", MessageCategorizer.categorize("Need price quote")) }
}

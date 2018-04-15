package io.xinkle.gettyviewer.core

import org.junit.Test

class GettyParserTest {
    @Test
    fun request_must_return_url_successful() {
        val result = GettyParser.parse()
        assert(result.isNotEmpty())
        println(result)
    }
}
package eu.dubedout.blanktemplate.hmtlparsing

import eu.dubedout.blanktemplate.hmtlparsing.duproprio.DuProprio1
import eu.dubedout.blanktemplate.processor.CentrisHtmlProcessor
import org.hamcrest.Matcher
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.File

class HtmlParsingTest {

    @Test
    fun testDuProprio1_expectCorrectPrice(){
        val file = TestFileLoader().getFileFromResources("/htmlexamples/duproprio1.html")
        val extracted = CentrisHtmlProcessor().extract(file)
        assert(extracted.price == DuProprio1().extractedData.price)
    }
}
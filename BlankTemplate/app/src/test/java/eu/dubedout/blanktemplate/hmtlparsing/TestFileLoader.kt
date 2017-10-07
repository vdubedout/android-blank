package eu.dubedout.blanktemplate.hmtlparsing

import java.io.File

class TestFileLoader {
    fun getFileFromResources(resourceUrl: String): File{
        val url = this.javaClass.getResource(resourceUrl)
        return File(url.file)
    }
}

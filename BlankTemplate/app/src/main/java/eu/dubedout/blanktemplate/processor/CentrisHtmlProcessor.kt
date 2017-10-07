package eu.dubedout.blanktemplate.processor

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class CentrisHtmlProcessor {

    fun extract(html: String): PhysicalBuildingCaracteristics = extract(Jsoup.parse(html))

    fun extract(file: File): PhysicalBuildingCaracteristics = extract(Jsoup.parse(file, null))

    private fun extract(document: Document): PhysicalBuildingCaracteristics {
        val price = document.select("meta[property$=price]").first().attr("content").toFloat()

        return PhysicalBuildingCaracteristics(
                title = "WeirdHome",
                address = "Some address",
                price = price)
    }

}
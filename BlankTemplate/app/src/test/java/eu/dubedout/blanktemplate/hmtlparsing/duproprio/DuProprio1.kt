package eu.dubedout.blanktemplate.hmtlparsing.duproprio

import eu.dubedout.blanktemplate.processor.PhysicalBuildingCaracteristics

class DuProprio1 {
    val url = "https://duproprio.com/fr/montreal/rosemont-la-petite-patrie/condo-a-vendre/hab-3231-avenue-du-mont-royal-est-766954"
    val extractedData = PhysicalBuildingCaracteristics(
            title = "Condo à vendre",
            address = "3231, Avenue du Mont-Royal Est\n" +
                    "Rosemont / La Petite PatrieMontréal / l'Île\n",
            price = 399900f
    )
}
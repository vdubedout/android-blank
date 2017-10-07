package eu.dubedout.blanktemplate.processor

data class PhysicalBuildingCaracteristics
    constructor(
            val title: String,
            val address: String,
//            val rooms: Int,
//            val bathroom: Int,
//            val yearBuilt: String,
//            val lotAreaInSqFeet: Float,
            val price: Float
    )
{
//    val lotAreaInSqMeter = lotAreaInSqFeet / 10.764
//    val lotAreaPriceBySquareFeet = price / lotAreaInSqFeet
//    val lotAreaPriceBySquareMeter = price / lotAreaInSqMeter
}
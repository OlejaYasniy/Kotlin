import java.util.*

class OutlayManager {
    val outlays = mutableListOf<Outlay>()
    fun addOutlay(date: Date, category: String, summ: Double){
        outlays.add(Outlay(date, category, summ))
    }

    fun coutOutlays (){
        for (outlay in outlays) {
            outlay.displayInfo()
        }
    }

    fun calculateOutlays (){
        val categorySumms = mutableMapOf<String, Double>()
        for (outlay in outlays) {
            val currentTotal = categorySumms.getOrDefault(outlay.category, 0.0)
            categorySumms[outlay.category] = currentTotal + outlay.summ
        }
        println(categorySumms)
    }
}
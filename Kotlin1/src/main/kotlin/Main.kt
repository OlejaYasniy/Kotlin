import java.util.*


fun main() {
    val myOutlay = Outlay (
        Date(122,2,23, 12, 0, 0),"Shop", 187.5)

    myOutlay.displayInfo()

    println("\nВсе расходы:\n")

    val myOutlays = OutlayManager()

    myOutlays.addOutlay(
        Date(122,2,2, 13, 0, 0),"Shop", 187.5)
    myOutlays.addOutlay(
        Date(123,5,26, 14, 0, 0),"Transport", 77.0)
    myOutlays.addOutlay(
        Date(124,6,7, 15, 0, 0),"Shop", 100.9)
    myOutlays.addOutlay(
        Date(120,7,15, 16, 0, 0),"Transport", 88.9)

    myOutlays.coutOutlays()

    println("\nРасходы по категориям:\n")

    myOutlays.calculateOutlays()
}




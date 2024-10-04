import java.util.Date

class Outlay(var date: Date, var category: String, var summ: Double) {

    fun displayInfo() {
        println("Дата: $date, Категория: $category, Сумма: $summ")
    }

}
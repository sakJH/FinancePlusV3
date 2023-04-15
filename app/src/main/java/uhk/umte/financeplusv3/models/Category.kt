package uhk.umte.financeplusv3.models

enum class Category(val id: Int, val description: String) {
    GROCERIES(1, "Potraviny"),
    UTILITIES(2, "Služby"),
    WORKS(3, "Práce"),
    TRANSPORTATION(4, "Doprava"),
    ENTERTAINMENT(5, "Zábava"),
    HEALTH(6, "Zdraví"),
    CLOTHING(7, "Oblečení"),
    HOUSING(8, "Domácnost"),
    SAVINGS(9, "Úspory a investice"),
    EDUCATION(10, "Vzdělání"),
    CAR(11, "Auto"),
    HOLIDAY(12, "Dovolená"),
    OTHER(13, "Ostatní");
}
package uhk.umte.financeplusv3.models

enum class Category(val id: Int, val description: String) {
    GROCERIES(1, "Potraviny"),
    UTILITIES(2, "Služby"),
    TRANSPORTATION(3, "Doprava"),
    ENTERTAINMENT(4, "Zábava"),
    HEALTH(5, "Zdraví"),
    CLOTHING(6, "Oblečení"),
    HOUSING(7, "Domácnost"),
    SAVINGS(8, "Úspory a onvestice"),
    EDUCATION(9, "Vzdělání"),
    OTHER(10, "Ostatní");
}
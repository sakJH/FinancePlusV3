package uhk.umte.financeplusv3.models.categoryitems

import uhk.umte.financeplusv3.models.Transaction

sealed class CategoryItem {
    data class CategoryTitle(val title: String) : CategoryItem()
    data class CategoryTransaction(val transaction: Transaction) : CategoryItem()
}
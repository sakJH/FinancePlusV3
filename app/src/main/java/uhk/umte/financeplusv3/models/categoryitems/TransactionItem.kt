package uhk.umte.financeplusv3.models.categoryitems

import uhk.umte.financeplusv3.models.Transaction

data class TransactionItem(
    val transaction: Transaction
) : CategoryItem()
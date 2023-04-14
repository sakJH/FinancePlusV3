package uhk.umte.financeplusv3.models.categoryitems

import uhk.umte.financeplusv3.models.Category

data class CategoryHeader(
    val category: Category
) : CategoryItem()
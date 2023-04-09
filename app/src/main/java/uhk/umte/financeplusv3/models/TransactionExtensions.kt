package uhk.umte.financeplusv3.models

class TransactionExtensions {

    //TODO - tohle opravit "transactionType"

    fun Transaction.toIncome(): Income? {
        return if (transactionType == "income") {
            Income(id, amount, category, date, description, "income")
        } else {
            null
        }
    }

    fun Transaction.toExpense(): Expense? {
        return if (transactionType == "expense") {
            Expense(id, amount, category, date, description, "expense")
        } else {
            null
        }
    }
}
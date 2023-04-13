package uhk.umte.financeplusv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import uhk.umte.financeplusv3.fragments.*
import uhk.umte.financeplusv3.fragments.add.AddExpenseFragment
import uhk.umte.financeplusv3.fragments.add.AddIncomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var budgetFragment: BudgetFragment
    private lateinit var incomeFragment: IncomeFragment
    private lateinit var expenseFragment: ExpenseFragment
    private lateinit var addIncomeFragment: AddIncomeFragment
    private lateinit var addExpenseFragment: AddExpenseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = MainFragment()
        budgetFragment = BudgetFragment()
        incomeFragment = IncomeFragment()
        expenseFragment = ExpenseFragment()
        addIncomeFragment = AddIncomeFragment()
        addExpenseFragment = AddExpenseFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, mainFragment)
            commit()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, mainFragment)
                        commit()
                    }
                }
                R.id.budgetFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, budgetFragment)
                        commit()
                    }
                }
                R.id.incomeFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, incomeFragment)
                        commit()
                    }
                }
                R.id.expensesFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, expenseFragment)
                        commit()
                    }
                }

                R.id.addIncomeFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, addIncomeFragment)
                        commit()
                    }
                }
                R.id.addExpenseFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, addExpenseFragment)
                        commit()
                    }
                }
            }
            true
        }
    }
}
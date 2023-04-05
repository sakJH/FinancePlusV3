package uhk.umte.financeplusv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import uhk.umte.financeplusv3.fragments.BudgetFragment
import uhk.umte.financeplusv3.fragments.ExpenseFragment
import uhk.umte.financeplusv3.fragments.IncomeFragment
import uhk.umte.financeplusv3.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var budgetFragment: BudgetFragment
    private lateinit var incomeFragment: IncomeFragment
    private lateinit var expenseFragment: ExpenseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = MainFragment()
        budgetFragment = BudgetFragment()
        incomeFragment = IncomeFragment()
        expenseFragment = ExpenseFragment()

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
            }
            true
        }
    }
}
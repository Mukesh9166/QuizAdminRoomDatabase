package com.ms.quizapp.ui.homePage.view


import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.quizapp.R
import com.ms.quizapp.databinding.ActivityMainBinding
import com.ms.quizapp.ui.addQuestion.view.AddQuestionFragment
import com.ms.quizapp.ui.viewQuestions.view.ViewQuestionsFragment
import com.ms.quizapp.utils.*


class HomePageActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var selectedId: Int = R.id.navigation_create

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment

        navController = navHostFragment.navController
        setStatusBar(getColor(R.color.snow))
        setSupportActionBar(binding?.toolbar)
        setupNavigation()
        setToolbarWithBottomNavStyle()
        clickHandlers()

    }


    private fun clickHandlers() {
        binding?.ivUsers?.onSafeClick {
            setToolbarBackStyle()
            navController?.navigate(
                R.id.userListFragment
            )
        }
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding?.navView!!
        navView.itemIconTintList = null

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_create,
                R.id.navigation_library,
            )
        )

        navController?.let { setupActionBarWithNavController(it, appBarConfiguration) }
        navController?.let { navView.setupWithNavController(it) }

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.userListFragment -> {
                    hideBottomBarAndUser()
                    binding?.toolbar?.title = getString(R.string.users)
                    setToolbarBackStyle()
                }
                R.id.addQuestionFragment -> {
                    visibleBottomBarAndUser()
                    selectedPosition(0)
                    binding?.toolbar?.title = getString(R.string.add_question)
                    setToolbarWithBottomNavStyle()

                }
                R.id.viewQuestionsFragment -> {
                    visibleBottomBarAndUser()
                    selectedPosition(1)
                    setToolbarWithBottomNavStyle()
                    binding?.toolbar?.title = getString(R.string.questions)
                }
                R.id.updateQuestionFragment -> {
                    hideBottomBarAndUser()
                    setToolbarBackStyle()
                    binding?.toolbar?.title = getString(R.string.update_question)
                }
            }
        }

        binding?.navView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_create -> {
                    if (supportFragmentManager.currentNavigationFragment != AddQuestionFragment()) {
                        navController?.navigate(R.id.addQuestionFragment)
                    }
                }
                R.id.navigation_library -> {
                    if (supportFragmentManager.currentNavigationFragment != ViewQuestionsFragment()) {
                        navController?.navigate(R.id.viewQuestionsFragment)
                    }
                }
                else -> {

                }

            }
            true
        }

        /*binding?.navView?.setOnItemReselectedListener {
            if (it.itemId != selectedId) {
                if (it.itemId == R.id.navigation_create) {
                    return@setOnItemReselectedListener
                }
                val navOptions = navOptions {
                    popUpTo(it.itemId) {
                        inclusive = true
                    }
                }
                navController?.navigate(it.itemId, null, navOptions)
            } else selectedId = it.itemId
        }*/


    }

    private fun hideBottomBarAndUser() {
        binding?.ivUsers?.gone()
        binding?.navView?.gone()
    }

    private fun visibleBottomBarAndUser() {
        binding?.ivUsers?.visible()
        binding?.navView?.visible()
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp(appBarConfiguration)!! || super.onSupportNavigateUp()
    }

    private fun setToolbarBackStyle() {
        binding?.toolbar?.menu?.clear()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.toolbar?.setNavigationIcon(R.drawable.ic_icon_back)
    }

    private fun setToolbarWithBottomNavStyle() {
        binding?.toolbar?.menu?.clear()
        binding?.toolbar?.navigationIcon = null

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    private fun setStatusBar(statusBarColor: Int?) {
        statusBarColor?.let {
            window?.statusBarColor = statusBarColor

            when {
                statusBarColor.isColorDark() -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        window?.insetsController?.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        window?.decorView?.systemUiVisibility =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                0
                            } else {
                                0
                            }
                    }
                }
                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        window?.insetsController?.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        window?.decorView?.systemUiVisibility =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                            } else {
                                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            }
                    }
                }
            }

        }
    }

    private fun selectedPosition(pos: Int) {
        var i = 0
        while (i >= binding?.navView?.menu?.size()!!) {
            binding?.navView?.menu?.getItem(pos)?.isChecked = false
            i++
        }
        binding?.navView?.menu?.getItem(pos)?.isChecked = true
    }

    override fun onBackPressed() {

        when (supportFragmentManager.currentNavigationFragment) {
            is AddQuestionFragment -> {

                if (doubleBackToExitPressedOnce) {
                    finish()
                    return
                }

                doubleBackToExitPressedOnce = true
                Toast.makeText(
                    this,
                    getString(R.string.press_back_again_to_exit),
                    Toast.LENGTH_SHORT
                ).show()

                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

            }
            is ViewQuestionsFragment -> {
                navController?.clearBackStack(R.id.addQuestionFragment)
                binding?.navView?.selectedItemId = R.id.navigation_create
            }
            else -> {

                super.onBackPressed()
            }
        }


    }

}


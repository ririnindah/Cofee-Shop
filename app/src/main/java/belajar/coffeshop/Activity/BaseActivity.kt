package belajar.coffeshop.Activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Digunakan untuk membuat layout aplikasi mengabaikan batas normal dari area tampilan,
//        sehingga konten tampilan dapat meluas hingga ke area status bar, navigation bar, atau bahkan di luar layar.

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}